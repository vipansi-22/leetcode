class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] sCount = new int[26];
        for (int i = 0; i < n; i++) {
            sCount[s.charAt(i) - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] prefixCount = new int[26];
            boolean validPrefix = true;
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                prefixCount[c]++;
                if (prefixCount[c] > sCount[c]) {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) continue;

            int[] remCount = new int[26];
            for (int c = 0; c < 26; c++) {
                remCount[c] = sCount[c] - prefixCount[c];
            }

            int targetChar = target.charAt(i) - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (remCount[c] > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target, 0, i);
                    sb.append((char) ('a' + c));
                    remCount[c]--;

                    for (int ch = 0; ch < 26; ch++) {
                        while (remCount[ch] > 0) {
                            sb.append((char) ('a' + ch));
                            remCount[ch]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }

        return "";
    }
}