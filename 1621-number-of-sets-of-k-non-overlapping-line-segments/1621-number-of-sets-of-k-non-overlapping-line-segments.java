class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int[][] dp = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= k; j++) {
            long prefixSum = 0;
            for (int i = 1; i < n; i++) {
                prefixSum = (prefixSum + dp[i - 1][j - 1]) % MOD;
                dp[i][j] = (int) ((dp[i - 1][j] + prefixSum) % MOD);
            }
        }

        return dp[n - 1][k];
    }
}