class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            int val = num % k;
            long[] nextDp = new long[k];

            
            nextDp[val]++;

            for (int rem = 0; rem < k; rem++) {
                if (dp[rem] > 0) {
                    nextDp[(rem * val) % k] += dp[rem];
                }
            }

            
            for (int rem = 0; rem < k; rem++) {
                result[rem] += nextDp[rem];
            }

            dp = nextDp;
        }

        return result;
    }
}