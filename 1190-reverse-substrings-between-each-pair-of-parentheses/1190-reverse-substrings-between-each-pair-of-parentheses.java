import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack stack = new Stack();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == ')') {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && (Character) stack.peek() != '(') {
                    sb.append((Character) stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // Remove '('
                }
                for (int j = 0; j < sb.length(); j++) {
                    stack.push(sb.charAt(j));
                }
            } else {
                stack.push(c);
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append((Character) stack.pop());
        }

        return result.reverse().toString();
    }
}