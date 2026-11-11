class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = Math.min((long) cost1, (long) costBoth);
        long c2 = Math.min((long) cost2, (long) costBoth);
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;

        long option1 = n1 * c1 + n2 * c2;
        long minNeed = Math.min(n1, n2);
        long option2 = minNeed * cb + (n1 - minNeed) * c1 + (n2 - minNeed) * c2;
        long option3 = Math.max(n1, n2) * cb;

        return Math.min(option1, Math.min(option2, option3));
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna