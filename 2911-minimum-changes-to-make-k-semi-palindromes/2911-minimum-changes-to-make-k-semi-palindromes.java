import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = computeCost(s, i, j);
            }
        }

        int INF = 1_000_000;
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        for (int j = 2; j <= n; j++) {
            dp[1][j] = cost[0][j - 1];
        }

        for (int p = 2; p <= k; p++) {
            for (int j = 2 * p; j <= n; j++) {
                for (int i = 2 * (p - 1); i <= j - 2; i++) {
                    dp[p][j] = Math.min(dp[p][j], dp[p - 1][i] + cost[i][j - 1]);
                }
            }
        }

        return dp[k][n];
    }

    private int computeCost(String s, int start, int end) {
        int len = end - start + 1;
        int minChanges = Integer.MAX_VALUE;

        for (int d = 1; d * 2 <= len; d++) {
            if (len % d != 0) {
                continue;
            }
            int changes = 0;
            int groupLen = len / d;
            for (int rem = 0; rem < d; rem++) {
                int left = 0;
                int right = groupLen - 1;
                while (left < right) {
                    if (s.charAt(start + rem + left * d) != s.charAt(start + rem + right * d)) {
                        changes++;
                    }
                    left++;
                    right--;
                }
            }
            minChanges = Math.min(minChanges, changes);
        }

        return minChanges;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna