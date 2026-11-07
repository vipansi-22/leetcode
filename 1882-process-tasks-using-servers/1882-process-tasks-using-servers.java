import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue freeServers = new PriorityQueue(new Comparator() {
            public int compare(Object a, Object b) {
                int[] sA = (int[]) a;
                int[] sB = (int[]) b;
                if (sA[0] != sB0(sA, sB)) {
                    return Integer.compare(sA[0], sB[0]);
                }
                return Integer.compare(sA[1], sB[1]);
            }
            private int sB0(int[] sA, int[] sB) {
                return sB[0];
            }
        });

        PriorityQueue busyServers = new PriorityQueue(new Comparator() {
            public int compare(Object a, Object b) {
                int[] sA = (int[]) a;
                int[] sB = (int[]) b;
                return Long.compare(sA[0], sB[0]);
            }
        });

        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }

        long currentTime = 0;
        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            while (!busyServers.isEmpty() && ((int[]) busyServers.peek())[0] <= currentTime) {
                int[] server = (int[]) busyServers.poll();
                freeServers.offer(new int[] { server[1], server[2] });
            }

            if (freeServers.isEmpty()) {
                currentTime = ((int[]) busyServers.peek())[0];
                while (!busyServers.isEmpty() && ((int[]) busyServers.peek())[0] <= currentTime) {
                    int[] server = (int[]) busyServers.poll();
                    freeServers.offer(new int[] { server[1], server[2] });
                }
            }

            int[] server = (int[]) freeServers.poll();
            int weight = server[0];
            int index = server[1];

            ans[j] = index;
            busyServers.offer(new int[] { (int) (currentTime + tasks[j]), weight, index });
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna