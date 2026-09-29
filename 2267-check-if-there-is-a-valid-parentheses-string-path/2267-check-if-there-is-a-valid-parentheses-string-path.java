class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        int maxLen = m + n - 1;
        boolean[][][] visited = new boolean[m][n][maxLen + 1];

        return dfs(0, 0, 0, grid, m, n, visited);
    }

    private boolean dfs(int r, int c, int open, char[][] grid, int m, int n, boolean[][][] visited) {
        if (grid[r][c] == '(') {
            open++;
        } else {
            open--;
        }

        if (open < 0) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }

        if (visited[r][c][open]) {
            return false;
        }
        visited[r][c][open] = true;

        if (r + 1 < m && dfs(r + 1, c, open, grid, m, n, visited)) {
            return true;
        }

        if (c + 1 < n && dfs(r, c + 1, open, grid, m, n, visited)) {
            return true;
        }

        return false;
    }
}