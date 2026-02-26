import java.io.*;
import java.util.*;

public class Main {
    static int R, C, K, answer = 0;
    static char[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        visited = new boolean[R][C];
        visited[R-1][0] = true;

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = input.charAt(j);
            }
        }

        dfs(R - 1, 0, 1);
        System.out.println(answer);

    }

    static void dfs(int x, int y, int k) {
        if (x == 0 && y == C - 1) {
            if (k == K) answer++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < R && ny >= 0 && ny < C) { 
                if (!visited[nx][ny] && graph[nx][ny] != 'T') {
                    visited[nx][ny] = true;
                    dfs(nx, ny, k + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }
}