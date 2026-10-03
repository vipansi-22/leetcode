import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        Stack stack = new Stack();
        stack.push(-1);
        int maxLen = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    int len = i - (Integer) stack.peek();
                    if (len > maxLen) {
                        maxLen = len;
                    }
                }
            }
        }

        return maxLen;
    }
}