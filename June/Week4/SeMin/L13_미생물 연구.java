import java.io.*;
import java.util.*;

public class Main {
    public static final int MAX_CONTAINER = 20;
    public static final int MAX_MICRO = 55;

    public static final int[] dRow = {-1, 0, 1, 0};
    public static final int[] dCol = {0, -1, 0, 1};

    public static int containerSize, experimentCount;

    public static int[][] cultureBoard = new int[MAX_CONTAINER][MAX_CONTAINER];
    public static int[][] newCultureBoard = new int[MAX_CONTAINER][MAX_CONTAINER];
    public static boolean[][] visited = new boolean[MAX_CONTAINER][MAX_CONTAINER];

    public static int[] connectedComponentCount = new int[MAX_MICRO];
    public static int[] microSize = new int[MAX_MICRO];

    public static Pair[] microBoundingStart = new Pair[MAX_MICRO];
    public static Pair[] microBoundingEnd = new Pair[MAX_MICRO];

    public static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static class OrderPair {
        int sizeNeg, id;

        OrderPair(int sizeNeg, int id) {
            this.sizeNeg = sizeNeg;
            this.id = id;
        }
    }

    public static boolean isOutOfBounds(int row, int col) {
        return !(0 <= row && row < containerSize && 0 <= col && col < containerSize);
    }

    public static void dfsMarkComponent(int row, int col, int microId) {
        visited[row][col] = true;

        for (int dir = 0; dir < 4; dir++) {
            int newRow = row + dRow[dir];
            int newCol = col + dCol[dir];

            if (isOutOfBounds(newRow, newCol)) continue;
            if (visited[newRow][newCol]) continue;
            if (cultureBoard[newRow][newCol] != microId) continue;

            dfsMarkComponent(newRow, newCol, microId);
        }
    }

    public static void removeMicroorganism(int microId) {
        for (int row = 0; row < containerSize; row++) {
            for (int col = 0; col < containerSize; col++) {
                if (cultureBoard[row][col] == microId) {
                    cultureBoard[row][col] = 0;
                }
            }
        }
    }

    public static void arrangeMicroorganisms(int microId, int r1, int c1, int r2, int c2) {
        for (int row = 0; row < containerSize; row++) {
            Arrays.fill(visited[row], false);
        }

        Arrays.fill(connectedComponentCount, 0);

        for (int row = r1; row < r2; row++) {
            for (int col = c1; col < c2; col++) {
                cultureBoard[row][col] = microId;
            }
        }

        for (int row = 0; row < containerSize; row++) {
            for (int col = 0; col < containerSize; col++) {
                if (cultureBoard[row][col] == 0) continue;
                if (visited[row][col]) continue;

                int id = cultureBoard[row][col];
                connectedComponentCount[id]++;
                dfsMarkComponent(row, col, id);
            }
        }

        for (int id = 1; id <= microId; id++) {
            if (connectedComponentCount[id] >= 2) {
                removeMicroorganism(id);
            }
        }
    }

    public static void relocateMicroorganisms(int microCount) {
        for (int row = 0; row < containerSize; row++) {
            Arrays.fill(newCultureBoard[row], 0);
        }

        for (int id = 1; id <= microCount; id++) {
            microSize[id] = 0;
            microBoundingStart[id] = new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
            microBoundingEnd[id] = new Pair(0, 0);
        }

        for (int row = 0; row < containerSize; row++) {
            for (int col = 0; col < containerSize; col++) {
                int id = cultureBoard[row][col];
                if (id == 0) continue;

                microSize[id]++;
                microBoundingStart[id].first = Math.min(microBoundingStart[id].first, row);
                microBoundingStart[id].second = Math.min(microBoundingStart[id].second, col);

                microBoundingEnd[id].first = Math.max(microBoundingEnd[id].first, row);
                microBoundingEnd[id].second = Math.max(microBoundingEnd[id].second, col);
            }
        }

        ArrayList<OrderPair> relocationOrder = new ArrayList<>();

        for (int id = 1; id <= microCount; id++) {
            if (microSize[id] == 0) continue;
            relocationOrder.add(new OrderPair(-microSize[id], id));
        }

        Collections.sort(relocationOrder, (a, b) -> a.sizeNeg - b.sizeNeg);

        for (OrderPair order : relocationOrder) {
            int id = order.id;

            Pair start = microBoundingStart[id];
            Pair end = microBoundingEnd[id];

            int h = end.first - start.first + 1;
            int w = end.second - start.second + 1;

            for (int nr = 0; nr <= containerSize - h; nr++) {
                boolean done = false;

                for (int nc = 0; nc <= containerSize - w; nc++) {
                    boolean canPlace = true;

                    for (int r = 0; r < h && canPlace; r++) {
                        for (int c = 0; c < w; c++) {
                            if (cultureBoard[start.first + r][start.second + c] != id) continue;

                            if (newCultureBoard[nr + r][nc + c] != 0) {
                                canPlace = false;
                                break;
                            }
                        }
                    }

                    if (canPlace) {
                        for (int r = 0; r < h; r++) {
                            for (int c = 0; c < w; c++) {
                                if (cultureBoard[start.first + r][start.second + c] != id) continue;
                                newCultureBoard[nr + r][nc + c] = id;
                            }
                        }

                        done = true;
                        break;
                    }
                }

                if (done) break;
            }
        }

        for (int row = 0; row < containerSize; row++) {
            System.arraycopy(newCultureBoard[row], 0, cultureBoard[row], 0, containerSize);
        }
    }

    public static void calculateExperimentResult(int microCount) {
        boolean[][] isAdjacent = new boolean[MAX_MICRO][MAX_MICRO];

        for (int row = 0; row < containerSize; row++) {
            for (int col = 0; col < containerSize; col++) {
                if (cultureBoard[row][col] == 0) continue;

                for (int dir = 0; dir < 4; dir++) {
                    int nr = row + dRow[dir];
                    int nc = col + dCol[dir];

                    if (isOutOfBounds(nr, nc)) continue;
                    if (cultureBoard[nr][nc] == 0) continue;

                    if (cultureBoard[row][col] != cultureBoard[nr][nc]) {
                        int a = cultureBoard[row][col];
                        int b = cultureBoard[nr][nc];
                        isAdjacent[a][b] = true;
                        isAdjacent[b][a] = true;
                    }
                }
            }
        }

        int score = 0;

        for (int a = 1; a <= microCount; a++) {
            for (int b = a + 1; b <= microCount; b++) {
                if (isAdjacent[a][b]) {
                    score += microSize[a] * microSize[b];
                }
            }
        }

        System.out.println(score);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        containerSize = Integer.parseInt(st.nextToken());
        experimentCount = Integer.parseInt(st.nextToken());

        for (int row = 0; row < containerSize; row++) {
            Arrays.fill(cultureBoard[row], 0);
        }

        for (int experimentId = 1; experimentId <= experimentCount; experimentId++) {
            st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            arrangeMicroorganisms(experimentId, r1, c1, r2, c2);
            relocateMicroorganisms(experimentId);
            calculateExperimentResult(experimentId);
        }
    }
}