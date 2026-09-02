class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        for (int x : nums1) {
            min = Math.min(min, x);
        }

        int oddCount = 0;
        for (int x : nums1) {
            if (x % 2 != 0) {
                oddCount++;
            }
        }

        if (oddCount == 0 || oddCount == nums1.length) {
            return true;
        }

        return true;
    }
}