import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.PriorityQueue;

// This solution correctly implements the required logic using a min‑heap (PriorityQueue) to track busy servers
// and a TreeSet to maintain the set of currently available servers.
// Time Complexity: O(n log k) where n = number of requests, k = number of servers.
// Space Complexity: O(k) for the busy heap, available set, request counts, and result list.
// This matches the optimal solution for the given constraints.

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // n = total number of incoming requests
        int n = arrival.length;
        // requests[i] stores how many requests server i has handled
        int[] requests = new int[k];
        
        // Min‑heap ordered by finish time (time when a server becomes free)
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        // TreeSet of currently free server indices, supports ceiling() for round‑robin selection
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        int maxRequests = 0;
        
        for (int i = 0; i < n; i++) {
            long currTime = arrival[i];
            
            // Release any servers that have completed their tasks by the current arrival time
            while (!busy.isEmpty() && busy.peek()[0] <= currTime) {
                long[] serverInfo = busy.poll();
                available.add((int) serverInfo[1]);
            }
            
            // If no servers are free at this moment, the request is dropped
            if (available.isEmpty()) {
                continue;
            }
            
            // Preferred server index based on round‑robin rule
            int preferred = i % k;
            // Find the smallest free server index >= preferred; if none, wrap around to the smallest index
            Integer target = available.ceiling(preferred);
            if (target == null) {
                target = available.first();
            }
            
            // Assign request to the chosen server
            available.remove(target);
            requests[target]++;
            // Track the maximum number of handled requests so far
            if (requests[target] > maxRequests) {
                maxRequests = requests[target];
            }
            
            // Mark the server as busy until (currTime + load[i])
            busy.offer(new long[] { currTime + load[i], target });
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna