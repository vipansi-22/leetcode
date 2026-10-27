class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        
        StringBuilder firstRow = new StringBuilder();
        for (int j = 0; j < n; j++) {
            firstRow.append('.');
        }
        grid[0] = firstRow.toString();
        
        if (m > 1) {
            StringBuilder otherRow = new StringBuilder();
            for (int j = 0; j < n - 1; j++) {
                otherRow.append('#');
            }
            otherRow.append('.');
            String otherRowStr = otherRow.toString();
            
            for (int i = 1; i < m; i++) {
                grid[i] = otherRowStr;
            }
        }
        
        return grid;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna