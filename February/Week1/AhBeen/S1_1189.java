import java.io.*;
import java.util.*;


public class Main {
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];
		boolean[][] visited = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		visited[R - 1][0] = true;
		dfs(R, C, K, R - 1, 0, 1, map, visited);
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static void dfs(int R, int C, int K, int r, int c, int depth, char[][] map, boolean[][] visited){
		int[] moveR = {1, -1, 0, 0};
		int[] moveC = {0, 0, 1, -1};

		if(r == 0 && c == C - 1) {
			if(depth == K) {
				answer++;
			}
			return;
		}
		for(int i = 0; i < 4; i++) {
			int nr = r + moveR[i];
			int nc = c + moveC[i];

			if(nr < 0 || nc < 0 || nr >= R || nc >= C) {
				continue;
			}
			if(!visited[nr][nc] && map[nr][nc] != 'T') {
				visited[nr][nc] = true;
				dfs(R, C, K, nr, nc, depth + 1, map, visited);
				visited[nr][nc] = false;
			}
		}
	}
}
