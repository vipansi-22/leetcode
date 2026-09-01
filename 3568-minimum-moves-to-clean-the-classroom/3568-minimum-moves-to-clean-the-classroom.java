import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1, startC = -1;
        List<int[]> litters = new ArrayList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litters.add(new int[]{r, c});
                }
            }
        }

        int numLitter = litters.size();
        if (numLitter == 0) return 0;

        int numStates = 1 << numLitter;
        int[][][][] dist = new int[m][n][numStates][energy + 1];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                for (int mask = 0; mask < numStates; mask++) {
                    Arrays.fill(dist[r][c][mask], -1);
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        
        int initialMask = 0;
        dist[startR][startC][initialMask][energy] = 0;
        queue.offer(new int[]{startR, startC, initialMask, energy});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int mask = curr[2];
            int e = curr[3];
            int steps = dist[r][c][mask][e];

            if (mask == numStates - 1) {
                return steps;
            }

            if (e == 0) continue;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                    char cell = classroom[nr].charAt(nc);
                    int nmask = mask;
                    int ne = e - 1;

                    if (cell == 'R') {
                        ne = energy;
                    } else if (cell == 'L') {
                        for (int j = 0; j < numLitter; j++) {
                            if (litters.get(j)[0] == nr && litters.get(j)[1] == nc) {
                                nmask |= (1 << j);
                                break;
                            }
                        }
                    }

                    if (dist[nr][nc][nmask][ne] == -1) {
                        dist[nr][nc][nmask][ne] = steps + 1;
                        queue.offer(new int[]{nr, nc, nmask, ne});
                    }
                }
            }
        }

        return -1;
    }
}