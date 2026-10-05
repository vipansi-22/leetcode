import java.util.Stack;

class Solution {
    public int scoreOfParentheses(String s) {
        Stack stack = new Stack();
        stack.push(0);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(0);
            } else {
                int v = (Integer) stack.pop();
                int w = (Integer) stack.pop();
                int score = w + Math.max(2 * v, 1);
                stack.push(score);
            }
        }

        return (Integer) stack.pop();
    }
}