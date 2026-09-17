import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLeft = new int[n];
        Arrays.fill(minLeft, Integer.MAX_VALUE);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (map.containsKey(sum - target)) {
                int start = map.get(sum - target);
                int currentLen = i - start;

                if (start >= 0 && minLeft[start] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, minLeft[start] + currentLen);
                }

                minLen = Math.min(minLen, currentLen);
            }

            minLeft[i] = minLen;
            map.put(sum, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}