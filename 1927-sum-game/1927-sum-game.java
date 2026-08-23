class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sum1 = 0, sum2 = 0;
        int q1 = 0, q2 = 0;

        for (int i = 0; i < n / 2; i++) {
            char ch = num.charAt(i);
            if (ch == '?') q1++;
            else sum1 += ch - '0';
        }

        for (int i = n / 2; i < n; i++) {
            char ch = num.charAt(i);
            if (ch == '?') q2++;
            else sum2 += ch - '0';
        }


        return (sum1 - sum2) * 2 != (q2 - q1) * 9;
    }
}