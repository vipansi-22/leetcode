import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            int current = nums[right];
            freqMap.put(current, freqMap.getOrDefault(current, 0) + 1);

            
            while (freqMap.get(current) > k) {
                int leftVal = nums[left];
                freqMap.put(leftVal, freqMap.get(leftVal) - 1);
                left++;
            }

            
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}