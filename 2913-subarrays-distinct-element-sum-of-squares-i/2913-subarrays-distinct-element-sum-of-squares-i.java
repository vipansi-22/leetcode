import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int sumCounts(List nums) {
        int n = nums.size();
        int totalSum = 0;

        for (int i = 0; i < n; i++) {
            Set seen = new HashSet();
            for (int j = i; j < n; j++) {
                seen.add(nums.get(j));
                int distinctCount = seen.size();
                totalSum += distinctCount * distinctCount;
            }
        }

        return totalSum;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna