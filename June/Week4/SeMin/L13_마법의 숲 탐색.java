import java.io.*;
import java.util.*;

public class Main {
    static int R, C, K;
    static int[][] map;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int[][] check = {
            {-2, 0}, {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 0}, {0, 1}, {1, 0}
    };

    static boolean inRange(int r, int c) {
        return 0 <= r && r < R + 3 && 0 <= c && c < C;
    }

    static boolean canGo(int r, int c) {
        for (int[] p : check) {
            int nr = r + p[0];
            int nc = c + p[1];

            if (!inRange(nr, nc) || map[nr][nc] != 0) {
                return false;
            }
        }
        return true;
    }

    static void reset() {
        for (int i = 0; i < R + 3; i++) {
            Arrays.fill(map[i], 0);
        }
    }

    static int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[R + 3][C];

        q.offer(new int[]{r, c});
        visited[r][c] = true;

        int maxRow = r;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur[0] + dr[dir];
                int nc = cur[1] + dc[dir];

                if (!inRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == 0) continue;

                if (Math.abs(map[cur[0]][cur[1]]) == Math.abs(map[nr][nc]) ||
                        map[cur[0]][cur[1]] < 0) {

                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                    maxRow = Math.max(maxRow, nr);
                }
            }
        }

        return maxRow;
    }

    static int[] drop(int num, int c, int d) {
        int r = 1;

        while (true) {

            if (canGo(r + 1, c)) {
                r++;
            }
            else if (canGo(r + 1, c - 1)) {
                r++;
                c--;
                d = (d + 3) % 4;
            }
            else if (canGo(r + 1, c + 1)) {
                r++;
                c++;
                d = (d + 1) % 4;
            }
            else {
                break;
            }
        }

        if (r < 4) {
            return new int[]{-1, -1};
        }

        map[r][c] = num;

        for (int dir = 0; dir < 4; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (dir == d) map[nr][nc] = -num;
            else map[nr][nc] = num;
        }

        return new int[]{r, c};
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R + 3][C];

        long ans = 0;

        for (int num = 1; num <= K; num++) {

            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            int[] pos = drop(num, c, d);

            if (pos[0] == -1) {
                reset();
            }
            else {
                ans += bfs(pos[0], pos[1]) - 2;
            }
        }

        System.out.println(ans);
    }
}