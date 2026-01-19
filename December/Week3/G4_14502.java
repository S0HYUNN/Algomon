import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static List<int[]> list = new ArrayList<>();
	static int max = 0;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					list.add(new int[]{i, j});
				}
			}
		}

		dfs(0, 0);
		System.out.println(max);
	}

	static void dfs(int s, int count) {
		if(count == 3) {
			bfs();
			return;
		}
		for(int i = s; i < N * M; i++) {
			int x = i / M;
			int y = i % M;

			if(map[x][y] == 0) {
				map[x][y] = 1;
				dfs(i + 1, count + 1);
				map[x][y] = 0;
			}
		}
	}

	static void bfs() {
		int[][] copy = new int[N][M];
		for(int i = 0; i < N; i++) {
			copy[i] = map[i].clone();
		}
		Queue<int[]> q = new ArrayDeque<>();
		for(int[] l : list) {
			q.offer(l);
		}

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			for(int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];

				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(copy[nx][ny] == 0) {
					copy[nx][ny] = 2;
					q.offer(new int[] {nx, ny});
				}
			}
		}

		int safe = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copy[i][j] == 0) {
					safe++;
				}
			}
		}

		max = Math.max(max, safe);
	}
}