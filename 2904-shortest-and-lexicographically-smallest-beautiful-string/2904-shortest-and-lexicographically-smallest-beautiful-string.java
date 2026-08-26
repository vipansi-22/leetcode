import java.util.ArrayList;
import java.util.List;

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        List<Integer> ones = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }

        if (ones.size() < k) {
            return "";
        }

        int minLen = Integer.MAX_VALUE;
        String res = "";

        for (int i = 0; i <= ones.size() - k; i++) {
            int start = ones.get(i);
            int end = ones.get(i + k - 1);
            int len = end - start + 1;

            String sub = s.substring(start, end + 1);

            if (len < minLen) {
                minLen = len;
                res = sub;
            } else if (len == minLen) {
                if (sub.compareTo(res) < 0) {
                    res = sub;
                }
            }
        }

        return res;
    }
}