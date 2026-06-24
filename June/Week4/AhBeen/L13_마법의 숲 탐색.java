import java.io.*;
import java.util.*;

public class Main {
	static int R, C, K;
	static int[][] map;
	static boolean[][] isExit;

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	static class Golem {
		int r, c, dir;
		Golem(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[R + 4][C + 1];
		isExit = new boolean[R + 4][C + 1];

		int totalRowSum = 0;

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			Golem g = new Golem(1, c, d);

			moveGolem(g);

			if (g.r <= 4) {
				resetMap();
				continue;
			}

			settleGolem(i, g);

			int maxRow = bfsMaxRow(g.r, g.c);

			totalRowSum += (maxRow - 3);
		}

		System.out.println(totalRowSum);
	}

	static void moveGolem(Golem g) {
		while (true) {
			if (canMoveSouth(g.r, g.c)) {
				g.r++;

				continue;
			}

			if (canMoveWest(g.r, g.c)) {
				g.c--;
				g.r++;
				g.dir = (g.dir + 3) % 4;
				continue;
			}

			if (canMoveEast(g.r, g.c)) {
				g.c++;
				g.r++;
				g.dir = (g.dir + 1) % 4;
				continue;
			}
			break;
		}
	}

	static boolean canMoveSouth(int r, int c) {
		return isAvailable(r + 2, c) && isAvailable(r + 1, c - 1) && isAvailable(r + 1, c + 1);
	}

	static boolean canMoveWest(int r, int c) {
		return isAvailable(r - 1, c - 1) && isAvailable(r, c - 2) && isAvailable(r + 1, c - 1)
			&& isAvailable(r + 1, c - 2) && isAvailable(r + 2, c - 1);
	}

	static boolean canMoveEast(int r, int c) {
		return isAvailable(r - 1, c + 1) && isAvailable(r, c + 2) && isAvailable(r + 1, c + 1)
			&& isAvailable(r + 1, c + 2) && isAvailable(r + 2, c + 1);
	}

	static boolean isAvailable(int r, int c) {
		if (c < 1 || c > C) return false;
		if (r > R + 3) return false;
		if (r < 1) return true;

		return map[r][c] == 0;
	}

	static void settleGolem(int id, Golem g) {
		map[g.r][g.c] = id;
		for (int i = 0; i < 4; i++) {
			map[g.r + dr[i]][g.c + dc[i]] = id;
		}
		int exitR = g.r + dr[g.dir];
		int exitC = g.c + dc[g.dir];
		isExit[exitR][exitC] = true;
	}

	static int bfsMaxRow(int startR, int startC) {
		int maxR = startR;
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[R + 4][C + 1];

		q.add(new int[]{startR, startC});
		visited[startR][startC] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int cr = curr[0];
			int cc = curr[1];
			int currentGolemId = map[cr][cc];

			maxR = Math.max(maxR, cr);

			for (int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];

				if (nr < 1 || nr > R + 3 || nc < 1 || nc > C || map[nr][nc] == 0) continue;
				if (visited[nr][nc]) continue;

				if (map[nr][nc] == currentGolemId || (isExit[cr][cc] && map[nr][nc] != currentGolemId)) {
					visited[nr][nc] = true;
					q.add(new int[]{nr, nc});
				}
			}
		}
		return maxR;
	}

	static void resetMap() {
		map = new int[R + 4][C + 1];
		isExit = new boolean[R + 4][C + 1];
	}
}