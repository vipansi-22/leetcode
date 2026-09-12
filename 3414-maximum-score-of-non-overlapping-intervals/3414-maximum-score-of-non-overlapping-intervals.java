import java.util.*;

class Solution {
    static class Interval {
        int left, right, weight, id;
        Interval(int left, int right, int weight, int id) {
            this.left = left;
            this.right = right;
            this.weight = weight;
            this.id = id;
        }
    }

    static class State {
        long weight;
        List<Integer> ids;

        State(long weight, List<Integer> ids) {
            this.weight = weight;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] sorted = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervalsList.get(i);
            sorted[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }

        Arrays.sort(sorted, (a, b) -> a.left != b.left ? Integer.compare(a.left, b.left) : Integer.compare(a.right, b.right));

        int[] nextIdx = new int[n];
        for (int i = 0; i < n; i++) {
            int l = i + 1, r = n, target = sorted[i].right;
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (sorted[mid].left > target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            nextIdx[i] = l;
        }

        State[][] dp = new State[n + 1][5];
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = new State(0, new ArrayList<>());
            for (int k = 1; k <= 4; k++) {
                State skip = dp[i + 1][k];

                int next = nextIdx[i];
                State takeNext = dp[next][k - 1];

                List<Integer> takeIds = new ArrayList<>(takeNext.ids);
                takeIds.add(sorted[i].id);
                Collections.sort(takeIds);
                State take = new State(takeNext.weight + sorted[i].weight, takeIds);

                if (take.weight > skip.weight) {
                    dp[i][k] = take;
                } else if (take.weight < skip.weight) {
                    dp[i][k] = skip;
                } else {
                    dp[i][k] = compareLists(take.ids, skip.ids) < 0 ? take : skip;
                }
            }
        }

        List<Integer> resIds = dp[0][4].ids;
        int[] res = new int[resIds.size()];
        for (int i = 0; i < resIds.size(); i++) {
            res[i] = resIds.get(i);
        }
        return res;
    }

    private int compareLists(List<Integer> a, List<Integer> b) {
        int minSize = Math.min(a.size(), b.size());
        for (int i = 0; i < minSize; i++) {
            int cmp = Integer.compare(a.get(i), b.get(i));
            if (cmp != 0) return cmp;
        }
        return Integer.compare(a.size(), b.size());
    }
}