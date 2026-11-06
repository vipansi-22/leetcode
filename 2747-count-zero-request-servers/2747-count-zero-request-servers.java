import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        Integer[] qIndex = new Integer[m];
        for (int i = 0; i < m; i++) {
            qIndex[i] = i;
        }
        Arrays.sort(qIndex, (a, b) -> Integer.compare(queries[a], queries[b]));
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int[] ans = new int[m];
        Map serverCount = new HashMap();
        int k = 0;
        int j = 0;
        int logLen = logs.length;

        for (int i = 0; i < m; i++) {
            int qIdx = qIndex[i];
            int r = queries[qIdx];
            int l = r - x;

            while (k < logLen && logs[k][1] <= r) {
                Integer sId = logs[k][0];
                Integer count = (Integer) serverCount.get(sId);
                if (count == null) {
                    serverCount.put(sId, 1);
                } else {
                    serverCount.put(sId, count + 1);
                }
                k++;
            }

            while (j < logLen && logs[j][1] < l) {
                Integer sId = logs[j][0];
                Integer count = (Integer) serverCount.get(sId);
                if (count == 1) {
                    serverCount.remove(sId);
                } else {
                    serverCount.put(sId, count - 1);
                }
                j++;
            }

            ans[qIdx] = n - serverCount.size();
        }

        return ans;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna