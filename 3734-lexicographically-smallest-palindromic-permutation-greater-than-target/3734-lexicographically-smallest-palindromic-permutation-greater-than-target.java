class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        int oddChar = -1;
        for (int c = 0; c < 26; c++) {
            if (freq[c] % 2 != 0) {
                oddCount++;
                oddChar = c;
            }
        }

        if (oddCount > 1) {
            return "";
        }

        int halfLen = n / 2;
        int[] halfFreq = new int[26];
        for (int c = 0; c < 26; c++) {
            halfFreq[c] = freq[c] / 2;
        }

        for (int i = halfLen; i >= 0; i--) {
            int[] prefixCount = new int[26];
            boolean validPrefix = true;
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                prefixCount[c]++;
                if (prefixCount[c] > halfFreq[c]) {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) continue;

            int[] remFreq = new int[26];
            for (int c = 0; c < 26; c++) {
                remFreq[c] = halfFreq[c] - prefixCount[c];
            }

            int minChar = (i == halfLen) ? -1 : (target.charAt(i) - 'a');

            for (int c = minChar + 1; c < 26; c++) {
                if (i < halfLen && remFreq[c] == 0) continue;

                if (i < halfLen) remFreq[c]--;

                StringBuilder half = new StringBuilder();
                half.append(target, 0, i);
                if (i < halfLen) half.append((char) ('a' + c));

                for (int ch = 0; ch < 26; ch++) {
                    while (remFreq[ch] > 0) {
                        half.append((char) ('a' + ch));
                        remFreq[ch]--;
                    }
                }

                StringBuilder full = new StringBuilder(half);
                if (n % 2 != 0) {
                    full.append((char) ('a' + oddChar));
                }
                for (int j = halfLen - 1; j >= 0; j--) {
                    full.append(half.charAt(j));
                }

                String candidate = full.toString();
                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }

                if (i < halfLen) remFreq[c]++;
            }
        }

        return "";
    }
}