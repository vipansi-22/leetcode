import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << (col - 2)));
            }
        }

        int leftMask = 0b00001111;
        int middleMask = 0b00111100;
        int rightMask = 0b11110000;

        int maxGroups = 2 * n;

        for (int mask : rowMasks.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;
            boolean middleFree = (mask & middleMask) == 0;

            if (leftFree && rightFree) {
                continue;
            } else if (leftFree || rightFree || middleFree) {
                maxGroups -= 1;
            } else {
                maxGroups -= 2;
            }
        }

        return maxGroups;
    }
}