class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) dfs(grid, i, 0, m, n);
            if (grid[i][n - 1] == 0) dfs(grid, i, n - 1, m, n);
        }

        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0) dfs(grid, 0, j, m, n);
            if (grid[m - 1][j] == 0) dfs(grid, m - 1, j, m, n);
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    count++;
                    dfs(grid, i, j, m, n);
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 1) {
            return;
        }

        grid[r][c] = 1;

        dfs(grid, r + 1, c, m, n);
        dfs(grid, r - 1, c, m, n);
        dfs(grid, r, c + 1, m, n);
        dfs(grid, r, c - 1, m, n);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna