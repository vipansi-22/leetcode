import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.PriorityQueue;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        int[] requests = new int[k];
        
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        int maxRequests = 0;
        
        for (int i = 0; i < n; i++) {
            long currTime = arrival[i];
            
            while (!busy.isEmpty() && busy.peek()[0] <= currTime) {
                long[] serverInfo = busy.poll();
                available.add((int) serverInfo[1]);
            }
            
            if (available.isEmpty()) {
                continue;
            }
            
            int preferred = i % k;
            Integer target = available.ceiling(preferred);
            if (target == null) {
                target = available.first();
            }
            
            available.remove(target);
            requests[target]++;
            if (requests[target] > maxRequests) {
                maxRequests = requests[target];
            }
            
            busy.offer(new long[] { currTime + load[i], target });
        }
        
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