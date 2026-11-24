class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1;
        int left = 0;

        long prod = 1;
        long lcmVal = 1;
        int gcdVal = 0;

        for (int right = 0; right < n; right++) {
            int x = nums[right];
            gcdVal = (right == left) ? x : gcd(gcdVal, x);
            lcmVal = lcm(lcmVal, x);

            if (prod > Long.MAX_VALUE / x) {
                while (left <= right) {
                    left++;
                    prod = 1;
                    lcmVal = 1;
                    gcdVal = 0;
                    for (int k = left; k <= right; k++) {
                        prod *= nums[k];
                        gcdVal = (k == left) ? nums[k] : gcd(gcdVal, nums[k]);
                        lcmVal = lcm(lcmVal, nums[k]);
                    }
                    if (prod <= Long.MAX_VALUE / 1 && (lcmVal <= Long.MAX_VALUE / gcdVal) && prod == lcmVal * gcdVal) {
                        break;
                    }
                }
            } else {
                prod *= x;
            }

            while (left < right && (lcmVal > Long.MAX_VALUE / gcdVal || prod != lcmVal * gcdVal)) {
                left++;
                prod = 1;
                lcmVal = 1;
                gcdVal = 0;
                for (int k = left; k <= right; k++) {
                    prod *= nums[k];
                    gcdVal = (k == left) ? nums[k] : gcd(gcdVal, nums[k]);
                    lcmVal = lcm(lcmVal, nums[k]);
                }
            }

            if (lcmVal <= Long.MAX_VALUE / gcdVal && prod == lcmVal * gcdVal) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private long gcdLong(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcdLong(a, b)) * b;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna