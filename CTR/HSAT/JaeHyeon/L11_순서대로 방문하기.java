import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static long answer = 0;
    static int N, M;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Point[] point = new Point[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (map[r - 1][c - 1] == 1) {
                System.out.println(0);
                return;
            }
            point[i] = new Point(r - 1, c - 1);
        }

        boolean[][] visited = new boolean[N][N];
        visited[point[0].r][point[0].c] = true;
        dfs(map, visited, point, 1, point[0]);

        System.out.println(answer);
    }

    static void dfs(int[][] map, boolean[][] visited, Point[] point, int cnt, Point now) {
        if (cnt == M) {
            answer++;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = now.r + dr[d];
            int nc = now.c + dc[d];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != 1 && !visited[nr][nc]) {
                if (nr == point[cnt].r && nc == point[cnt].c) {
                    visited[nr][nc] = true;
                    dfs(map, visited, point, cnt + 1, new Point(nr, nc));
                    visited[nr][nc] = false;
                } else {
                    visited[nr][nc] = true;
                    dfs(map, visited, point, cnt, new Point(nr,nc));
                    visited[nr][nc] = false;
                }
            }
        }
    }
}

class Point {
    int r, c;
    Point (int r, int c) {
        this.r = r;
        this.c = c;
    }
}