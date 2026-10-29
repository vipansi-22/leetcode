import java.util.ArrayList;
import java.util.List;

class Solution {
    private int[] head, to, next;
    private int edgeCount;
    private int[] tin, tout;
    private int timer;
    private int[][] up;
    private int[] depth;
    private int[] bit;
    private int[] charMask;
    private int totalNodes;

    private void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount++;
    }

    private void dfs(int u, int p, int d) {
        depth[u] = d;
        up[u][0] = p;
        for (int i = 1; i < 20; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        tin[u] = ++timer;

        for (int e = head[u]; e != -1; e = next[e]) {
            int v = to[e];
            if (v != p) {
                dfs(v, u, d + 1);
            }
        }

        tout[u] = timer;
    }

    private int getLca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = 19; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }

        if (u == v) return u;

        for (int i = 19; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }

        return up[u][0];
    }

    private void fenwickUpdate(int idx, int delta) {
        for (; idx <= totalNodes; idx += idx & -idx) {
            bit[idx] ^= delta;
        }
    }

    private int fenwickQuery(int idx) {
        int sum = 0;
        for (; idx > 0; idx -= idx & -idx) {
            sum ^= bit[idx];
        }
        return sum;
    }

    private void updateSubtree(int u, int maskDelta) {
        fenwickUpdate(tin[u], maskDelta);
        fenwickUpdate(tout[u] + 1, maskDelta);
    }

    private int getRootToNodeXor(int u) {
        return fenwickQuery(tin[u]);
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.totalNodes = n;
        head = new int[n];
        for (int i = 0; i < n; i++) head[i] = -1;
        to = new int[2 * (n - 1)];
        next = new int[2 * (n - 1)];
        edgeCount = 0;

        for (int[] e : edges) {
            addEdge(e[0], e[1]);
            addEdge(e[1], e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        timer = 0;
        up = new int[n][20];
        depth = new int[n];

        dfs(0, 0, 0);

        bit = new int[n + 2];
        charMask = new int[n];

        for (int i = 0; i < n; i++) {
            charMask[i] = 1 << (s.charAt(i) - 'a');
            updateSubtree(i, charMask[i]);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                int newMask = 1 << (parts[2].charAt(0) - 'a');
                int delta = charMask[u] ^ newMask;
                if (delta != 0) {
                    updateSubtree(u, delta);
                    charMask[u] = newMask;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lca = getLca(u, v);

                int pathMask = getRootToNodeXor(u) ^ getRootToNodeXor(v) ^ charMask[lca];
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna