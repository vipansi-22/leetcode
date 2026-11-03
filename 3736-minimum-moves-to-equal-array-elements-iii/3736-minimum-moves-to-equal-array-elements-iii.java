class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];
        int moves = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        for (int num : nums) {
            moves += max - num;
        }

        return moves;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna