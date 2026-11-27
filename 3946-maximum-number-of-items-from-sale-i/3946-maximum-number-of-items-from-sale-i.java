class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] free = new int[n];
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    free[i]++;
                }
            }
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int value = 1 + free[i];
            for (int w = budget; w >= price; w--) {
                dp[w] = Math.max(dp[w], dp[w - price] + value);
            }
        }

        for (int w = minPrice; w <= budget; w++) {
            dp[w] = Math.max(dp[w], dp[w - minPrice] + 1);
        }

        int ans = 0;
        for (int w = 0; w <= budget; w++) {
            ans = Math.max(ans, dp[w]);
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna