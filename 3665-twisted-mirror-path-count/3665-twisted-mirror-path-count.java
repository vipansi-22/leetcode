class Solution {
    int[][][] memo;
    int[][] grid;
    int m, n;

    int solve(int i, int j, int dir) {
        if (memo[i][j][dir] != -2) return memo[i][j][dir];

        int x = i + (dir == 0 ? 1 : 0);
        int y = j + (dir == 1 ? 1 : 0);
        int nd = 1 - dir;

        if (x >= m || y >= n) return memo[i][j][dir] = -1;

        if (grid[x][y] == 0) {
            return memo[i][j][dir] = x * n + y;
        }

        return memo[i][j][dir] = solve(x, y, nd);
    }

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        int mod = 1_000_000_007;

        memo = new int[m][n][2];

        for (int[][] row : memo) {
            for (int[] cell : row) {
                java.util.Arrays.fill(cell, -2);
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0 || grid[i][j] == 1) continue;

                for (int dir = 0; dir < 2; dir++) {
                    int x = i + (dir == 0 ? 0 : 1);
                    int y = j + (dir == 0 ? 1 : 0);

                    if (x >= m || y >= n) continue;

                    int dest;

                    if (grid[x][y] == 0) {
                        dest = x * n + y;
                    } else {
                        dest = solve(x, y, dir);
                    }

                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;

                        dp[r][c] = (dp[r][c] + dp[i][j]) % mod;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna