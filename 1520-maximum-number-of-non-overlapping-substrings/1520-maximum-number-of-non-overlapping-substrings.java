import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        List<int[]> validIntervals = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;

            int l = first[i];
            int r = last[i];
            boolean isValid = true;

            for (int j = l; j <= r; j++) {
                int ch = s.charAt(j) - 'a';
                if (first[ch] < l) {
                    isValid = false;
                    break;
                }
                r = Math.max(r, last[ch]);
            }

            if (isValid) {
                validIntervals.add(new int[]{l, r});
            }
        }

        
        validIntervals.sort(Comparator.comparingInt(a -> a[1]));

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : validIntervals) {
            int l = interval[0];
            int r = interval[1];

            if (l > prevEnd) {
                result.add(s.substring(l, r + 1));
                prevEnd = r;
            }
        }

        return result;
    }
}