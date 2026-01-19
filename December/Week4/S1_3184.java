import java.util.*;
import java.io.*;

public class Main {
	static boolean[][] visited;
	static int[] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];
		visited = new boolean[R][C];

		int sheep = 0;
		int wolf = 0;

		for(int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}

		result = new int[2];

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(!visited[i][j] && !(map[i][j] == '#')) {
					result[0] = 0;
					result[1] = 0;
					int[] arr = dfs(i, j, map, R, C);
					if(!(arr[0] == 0 && arr[1] == 0)) {
						if (arr[0] > arr[1]) {
							sheep += arr[0];
						} else {
							wolf += arr[1];
						}
					}
				}
			}
		}
		System.out.println(sheep + " " + wolf);
	}

	static int[] dfs(int x, int y, char[][] map, int R, int C) {
		int[] count = new int[2];
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};

		visited[x][y] = true;
		if(map[x][y] == 'o') {
			count[0]++;
		}
		if(map[x][y] == 'v') {
			count[1]++;
		}
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
				continue;
			}
			if(visited[nx][ny] || map[nx][ny] == '#') {
				continue;
			}
			int[] next = dfs(nx, ny, map, R, C);
			count[0] += next[0];
			count[1] += next[1];
		}
		return count;
	}
}