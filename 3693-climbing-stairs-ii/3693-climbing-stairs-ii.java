import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= Math.min(n, i + 3); j++) {
                long cost = dp[i] + costs[j - 1] + (long) (j - i) * (j - i);
                if (cost < dp[j]) {
                    dp[j] = cost;
                }
            }
        }

        return (int) dp[n];
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna