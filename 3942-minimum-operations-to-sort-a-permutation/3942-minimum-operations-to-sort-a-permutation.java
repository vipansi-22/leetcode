class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int zeroIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }

        boolean isAscending = true;
        for (int j = 0; j < n; j++) {
            if (nums[(zeroIdx + j) % n] != j) {
                isAscending = false;
                break;
            }
        }

        if (isAscending) {
            if (zeroIdx == 0) return 0;
            return Math.min(zeroIdx, n - zeroIdx + 2);
        }

        boolean isDescending = true;
        for (int j = 0; j < n; j++) {
            if (nums[(zeroIdx - j + n) % n] != j) {
                isDescending = false;
                break;
            }
        }

        if (isDescending) {
            return Math.min(n - zeroIdx, zeroIdx + 2);
        }

        return -1;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna