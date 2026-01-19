import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int x;
	static int y;
	static int K;
	static int[][] map;
	static int[] dice = new int[6];

	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			roll(dir);
			x = nx;
			y = ny;

			if (map[x][y] == 0) {
				map[x][y] = dice[1];
			} else {
				dice[1] = map[x][y];
				map[x][y] = 0;
			}

			sb.append(dice[0]).append("\n");
		}

		System.out.println(sb);
	}

	static void roll(int dir) {
		int temp;
		switch (dir) {
			case 1:
				temp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[1];
				dice[1] = dice[5];
				dice[5] = temp;
				break;
			case 2:
				temp = dice[0];
				dice[0] = dice[5];
				dice[5] = dice[1];
				dice[1] = dice[4];
				dice[4] = temp;
				break;
			case 3:
				temp = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[1];
				dice[1] = dice[2];
				dice[2] = temp;
				break;
			case 4:
				temp = dice[0];
				dice[0] = dice[2];
				dice[2] = dice[1];
				dice[1] = dice[3];
				dice[3] = temp;
				break;
		}
	}
}