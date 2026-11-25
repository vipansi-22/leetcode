import java.util.HashSet;
import java.util.Set;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 1_000_003L;
    private static final long BASE2 = 1_000_033L;
    private static final long OFFSET = 1_000_000_001L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1;
        int high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        long pow1 = 1;
        long pow2 = 1;
        for (int i = 0; i < len - 1; i++) {
            pow1 = (pow1 * BASE1) % MOD1;
            pow2 = (pow2 * BASE2) % MOD2;
        }

        long hash1 = 0;
        long hash2 = 0;
        for (int i = 0; i < len; i++) {
            long val = nums[i] + OFFSET;
            hash1 = (hash1 * BASE1 + val) % MOD1;
            hash2 = (hash2 * BASE2 + val) % MOD2;
        }

        Set<Long> seen = new HashSet<>();
        Set<Long> duplicates = new HashSet<>();

        long combined = (hash1 << 32) ^ hash2;
        seen.add(combined);

        for (int i = len; i < n; i++) {
            long leftVal = nums[i - len] + OFFSET;
            long rightVal = nums[i] + OFFSET;

            hash1 = (hash1 - (leftVal * pow1) % MOD1 + MOD1) % MOD1;
            hash1 = (hash1 * BASE1 + rightVal) % MOD1;

            hash2 = (hash2 - (leftVal * pow2) % MOD2 + MOD2) % MOD2;
            hash2 = (hash2 * BASE2 + rightVal) % MOD2;

            combined = (hash1 << 32) ^ hash2;
            if (!seen.add(combined)) {
                duplicates.add(combined);
            }
        }

        return seen.size() > duplicates.size();
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna