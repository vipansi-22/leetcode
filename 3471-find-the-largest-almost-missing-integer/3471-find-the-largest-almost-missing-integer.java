import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int largestInteger(int[] nums, int k) {
        Map<Integer, Integer> subarrayCount = new HashMap<>();
        int n = nums.length;

        for (int i = 0; i <= n - k; i++) {
            Set<Integer> uniqueInSubarray = new HashSet<>();
            for (int j = i; j < i + k; j++) {
                uniqueInSubarray.add(nums[j]);
            }
            for (int num : uniqueInSubarray) {
                subarrayCount.put(num, subarrayCount.getOrDefault(num, 0) + 1);
            }
        }

        int maxVal = -1;
        for (Map.Entry<Integer, Integer> entry : subarrayCount.entrySet()) {
            if (entry.getValue() == 1) {
                maxVal = Math.max(maxVal, entry.getKey());
            }
        }

        return maxVal;
    }
}