import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List braceExpansionII(String expression) {
        Set set = parseExpr(expression, 0, expression.length() - 1);
        List result = new ArrayList(set);
        Collections.sort(result);
        return result;
    }

    private Set parseExpr(String s, int start, int end) {
        Set unionSet = new HashSet();
        List currentProduct = new ArrayList();
        int i = start;

        while (i <= end) {
            char c = s.charAt(i);

            if (c == ',') {
                unionSet.addAll(multiplyAll(currentProduct));
                currentProduct.clear();
                i++;
            } else if (c == '{') {
                int braceCount = 1;
                int j = i + 1;
                while (j <= end && braceCount > 0) {
                    if (s.charAt(j) == '{') braceCount++;
                    else if (s.charAt(j) == '}') braceCount--;
                    j++;
                }
                currentProduct.add(parseExpr(s, i + 1, j - 2));
                i = j;
            } else {
                Set singleLetter = new HashSet();
                singleLetter.add(String.valueOf(c));
                currentProduct.add(singleLetter);
                i++;
            }
        }

        if (!currentProduct.isEmpty()) {
            unionSet.addAll(multiplyAll(currentProduct));
        }

        return unionSet;
    }

    private Set multiplyAll(List list) {
        Set result = new HashSet();
        result.add("");

        for (int i = 0; i < list.size(); i++) {
            Set set = (Set) list.get(i);
            Set temp = new HashSet();
            for (Object p : result) {
                String prefix = (String) p;
                for (Object s : set) {
                    String suffix = (String) s;
                    temp.add(prefix + suffix);
                }
            }
            result = temp;
        }

        return result;
    }
}