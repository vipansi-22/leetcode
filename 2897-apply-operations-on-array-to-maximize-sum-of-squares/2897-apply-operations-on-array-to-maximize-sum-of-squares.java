import java.util.List;

class Solution {
    public int maxSum(List nums, int k) {
        int[] bitCounts = new int[31];
        int n = nums.size();

        for (int i = 0; i < n; i++) {
            int num = (Integer) nums.get(i);
            for (int bit = 0; bit < 31; bit++) {
                if (((num >> bit) & 1) == 1) {
                    bitCounts[bit]++;
                }
            }
        }

        long ans = 0;
        long MOD = 1000000007;

        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int bit = 0; bit < 31; bit++) {
                if (bitCounts[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCounts[bit]--;
                }
            }
            ans = (ans + (currentNum % MOD) * (currentNum % MOD)) % MOD;
        }

        return (int) ans;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna