import java.io.*;
import java.util.*;

public class Main {
	static int min = 64;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				if(line.charAt(j) == 'W') {
					map[i][j] = 1;
				} else {
					map[i][j] = 0;
				}
			}
		}

		int row = N - 7;
		int col = M - 7;

		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				find(i, j, map);
			}
		}

		System.out.println(min);
	}

	public static void find(int x, int y, int[][] map) {
		int eX = x + 8;
		int eY = y + 8;
		int count = 0;

		int color = map[x][y];

		for(int i = x; i < eX; i++) {
			for(int j = y; j < eY; j++) {
				if(map[i][j] != color) {
					count++;
				}
				if(color == 1) {
					color = 0;
				} else {
					color = 1;
				}
			}
			if(color == 1) {
				color = 0;
			} else {
				color = 1;
			}
		}
		count = Math.min(count, 64 - count);

		min = Math.min(min, count);
	}
}