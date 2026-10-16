import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1000000007;

    static class SegmentTree {
        int n;
        long[] tree;
        long[] sumTree;
        long[] lazy;

        public SegmentTree(int n) {
            this.n = n;
            this.tree = new long[4 * n];
            this.sumTree = new long[4 * n];
            this.lazy = new long[4 * n];
        }

        private void pushDown(int node, int start, int end) {
            if (lazy[node] != 0) {
                int mid = start + (end - start) / 2;
                int leftChild = 2 * node + 1;
                int rightChild = 2 * node + 2;

                applyLazy(leftChild, start, mid, lazy[node]);
                applyLazy(rightChild, mid + 1, end, lazy[node]);

                lazy[node] = 0;
            }
        }

        private void applyLazy(int node, int start, int end, long val) {
            int len = end - start + 1;
            tree[node] = (tree[node] + 2 * val * sumTree[node] + val * val % MOD * len) % MOD;
            sumTree[node] = (sumTree[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        public void update(int node, int start, int end, int l, int r, long val) {
            if (l <= start && end <= r) {
                applyLazy(node, start, end, val);
                return;
            }

            pushDown(node, start, end);

            int mid = start + (end - start) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;

            if (l <= mid) {
                update(leftChild, start, mid, l, r, val);
            }
            if (r > mid) {
                update(rightChild, mid + 1, end, l, r, val);
            }

            tree[node] = (tree[leftChild] + tree[rightChild]) % MOD;
            sumTree[node] = (sumTree[leftChild] + sumTree[rightChild]) % MOD;
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        Map lastPos = new HashMap();

        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            int prevIdx = lastPos.containsKey(val) ? (Integer) lastPos.get(val) : -1;

            st.update(0, 0, n - 1, prevIdx + 1, i, 1);
            totalSum = (totalSum + st.tree[0]) % MOD;

            lastPos.put(val, i);
        }

        return (int) totalSum;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna