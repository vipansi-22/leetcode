class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum < 1 || sum > 9 * num) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int remainingSum = sum;

        for (int i = 0; i < num; i++) {
            int remainingDigits = num - 1 - i;
            int minDigit = (i == 0 && num > 1) ? 1 : 0;
            
            int startDigit = Math.min(9, remainingSum);

            for (int d = startDigit; d >= minDigit; d--) {
                int minPossibleRest = 0;
                int maxPossibleRest = 9 * remainingDigits;

                int rem = remainingSum - d;
                if (rem >= minPossibleRest && rem <= maxPossibleRest) {
                    sb.append(d);
                    remainingSum -= d;
                    break;
                }
            }
        }

        return sb.toString();
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna