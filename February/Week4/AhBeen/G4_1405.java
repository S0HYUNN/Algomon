import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static double[] p = new double[4];
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static double answer = 0.0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < 4; i++) {
			p[i] = Double.parseDouble(st.nextToken()) * 0.01;
		}

		visited = new boolean[30][30];
		int start = 15;

		visited[start][start] = true;
		dfs(start, start, 0, 1.0);
		System.out.printf("%.10f\n", answer);
	}

	static void dfs(int x, int y, int depth, double prob) {
		if (depth == N) {
			answer += prob;
			return;
		}

		for (int dir = 0; dir < 4; dir++) {
			if (p[dir] == 0.0) continue;

			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (visited[nx][ny]) continue;

			visited[nx][ny] = true;
			dfs(nx, ny, depth + 1, prob * p[dir]);
			visited[nx][ny] = false;
		}
	}
}