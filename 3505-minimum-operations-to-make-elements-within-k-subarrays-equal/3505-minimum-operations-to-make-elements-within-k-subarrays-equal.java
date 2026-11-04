import java.util.*;

class Solution {
    class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;
        int target;

        MedianWindow(int x) {
            target = (x + 1) / 2;
        }

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                left.merge(val, 1, Integer::sum);
                leftSize++;
                leftSum += val;
            } else {
                right.merge(val, 1, Integer::sum);
                rightSize++;
                rightSum += val;
            }
            balance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                removeFrom(left, val);
                leftSize--;
                leftSum -= val;
            } else {
                removeFrom(right, val);
                rightSize--;
                rightSum -= val;
            }
            balance();
        }

        void removeFrom(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) map.remove(val);
            else map.put(val, count - 1);
        }

        void balance() {
            while (leftSize > target) {
                int val = left.lastKey();
                removeFrom(left, val);
                leftSize--;
                leftSum -= val;

                right.merge(val, 1, Integer::sum);
                rightSize++;
                rightSum += val;
            }

            while (leftSize < target && !right.isEmpty()) {
                int val = right.firstKey();
                removeFrom(right, val);
                rightSize--;
                rightSum -= val;

                left.merge(val, 1, Integer::sum);
                leftSize++;
                leftSum += val;
            }
        }

        long getCost() {
            long median = left.lastKey();
            return median * leftSize - leftSum
                    + rightSum - median * rightSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow(x);

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[0] = window.getCost();

        for (int i = x; i < n; i++) {
            window.remove(nums[i - x]);
            window.add(nums[i]);
            cost[i - x + 1] = window.getCost();
        }

        long INF = Long.MAX_VALUE / 2;
        long[] prev = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);

            for (int i = j * x; i <= n; i++) {
                curr[i] = Math.min(
                    curr[i - 1],
                    prev[i - x] + cost[i - x]
                );
            }

            prev = curr;
        }

        return prev[n];
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna