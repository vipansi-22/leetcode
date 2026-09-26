import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List knowledge) {
        Map map = new HashMap();
        for (Object item : knowledge) {
            List k = (List) item;
            map.put((String) k.get(0), (String) k.get(1));
        }

        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int i = 0;

        while (i < n) {
            char c = s.charAt(i);
            if (c == '(') {
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                String key = s.substring(i + 1, j);
                Object val = map.get(key);
                if (val != null) {
                    sb.append((String) val);
                } else {
                    sb.append("?");
                }
                i = j + 1;
            } else {
                sb.append(c);
                i++;
            }
        }

        return sb.toString();
    }
}