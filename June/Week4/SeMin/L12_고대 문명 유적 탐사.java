import java.io.*;
import java.util.*;

public class Main {
    static final int N_LARGE = 5;
    static final int N_SMALL = 3;

    static class Board {
        int[][] a;

        public Board() {
            a = new int[N_LARGE][N_LARGE];
        }

        public Board copy() {
            Board newBoard = new Board();
            for (int i = 0; i < N_LARGE; i++) {
                System.arraycopy(this.a[i], 0, newBoard.a[i], 0, N_LARGE);
            }
            return newBoard;
        }

        public boolean inRange(int r, int c) {
            return 0 <= r && r < N_LARGE && 0 <= c && c < N_LARGE;
        }

        public Board rotate(int sy, int sx, int cnt) {
            Board result = this.copy();

            for (int k = 0; k < cnt; k++) {
                int[][] temp = new int[N_SMALL][N_SMALL];

                for (int r = 0; r < N_SMALL; r++) {
                    for (int c = 0; c < N_SMALL; c++) {
                        temp[r][c] = result.a[sy + r][sx + c];
                    }
                }

                for (int r = 0; r < N_SMALL; r++) {
                    for (int c = 0; c < N_SMALL; c++) {
                        result.a[sy + c][sx + (N_SMALL - 1 - r)] = temp[r][c];
                    }
                }
            }

            return result;
        }

        public int calScore() {
            int score = 0;

            boolean[][] visit = new boolean[N_LARGE][N_LARGE];

            int[] dr = {0, 1, 0, -1};
            int[] dc = {1, 0, -1, 0};

            for (int r = 0; r < N_LARGE; r++) {
                for (int c = 0; c < N_LARGE; c++) {
                    if (a[r][c] == 0 || visit[r][c]) continue;

                    Queue<int[]> q = new LinkedList<>();
                    List<int[]> trace = new ArrayList<>();

                    q.offer(new int[]{r, c});
                    trace.add(new int[]{r, c});
                    visit[r][c] = true;

                    int value = a[r][c];

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int nr = cur[0] + dr[dir];
                            int nc = cur[1] + dc[dir];

                            if (!inRange(nr, nc)) continue;
                            if (visit[nr][nc]) continue;
                            if (a[nr][nc] != value) continue;

                            visit[nr][nc] = true;
                            q.offer(new int[]{nr, nc});
                            trace.add(new int[]{nr, nc});
                        }
                    }

                    if (trace.size() >= 3) {
                        score += trace.size();

                        for (int[] pos : trace) {
                            a[pos[0]][pos[1]] = 0;
                        }
                    }
                }
            }

            return score;
        }

        public void fill(Queue<Integer> newArtifacts) {
            for (int c = 0; c < N_LARGE; c++) {
                for (int r = N_LARGE - 1; r >= 0; r--) {
                    if (a[r][c] == 0) {
                        a[r][c] = newArtifacts.poll();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Board board = new Board();

        for (int i = 0; i < N_LARGE; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N_LARGE; j++) {
                board.a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> newArtifactsQueue = new LinkedList<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            newArtifactsQueue.offer(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();

        for (int turn = 0; turn < K; turn++) {
            int maxScore = 0;
            Board maxScoreBoard = null;

            for (int cnt = 1; cnt <= 3; cnt++) {
                for (int sx = 0; sx <= N_LARGE - N_SMALL; sx++) {
                    for (int sy = 0; sy <= N_LARGE - N_SMALL; sy++) {

                        Board rotated = board.rotate(sy, sx, cnt);

                        int score = rotated.calScore();

                        if (score > maxScore) {
                            maxScore = score;
                            maxScoreBoard = rotated;
                        }
                    }
                }
            }

            if (maxScore == 0) break;

            board = maxScoreBoard;

            int turnScore = maxScore;

            while (true) {
                board.fill(newArtifactsQueue);

                int gained = board.calScore();

                if (gained == 0) break;

                turnScore += gained;
            }

            sb.append(turnScore).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}