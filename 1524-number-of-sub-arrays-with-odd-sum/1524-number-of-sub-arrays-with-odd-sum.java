class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        long ans = 0;
        int prefixSum = 0;
        int even = 1;
        int odd = 0;

        for (int num : arr) {
            prefixSum += num;
            if (prefixSum % 2 == 0) {
                ans += odd;
                even++;
            } else {
                ans += even;
                odd++;
            }
        }

        return (int) (ans % MOD);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna