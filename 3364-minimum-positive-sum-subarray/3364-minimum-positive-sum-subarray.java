import java.util.List;
import java.util.TreeMap;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int minSum = Integer.MAX_VALUE;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = l; i <= n; i++) {
            int addVal = prefix[i - l];
            map.put(addVal, map.getOrDefault(addVal, 0) + 1);

            Integer lower = map.lowerKey(prefix[i]);
            if (lower != null) {
                minSum = Math.min(minSum, prefix[i] - lower);
            }

            if (i >= r) {
                int remVal = prefix[i - r];
                int count = map.get(remVal);
                if (count == 1) {
                    map.remove(remVal);
                } else {
                    map.put(remVal, count - 1);
                }
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna