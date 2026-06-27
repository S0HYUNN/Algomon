import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int K;
	static int L;
	static int[][] map;
	static boolean[][] isCleaner;
	static List<Cleaner> cleaners = new ArrayList<>();

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static int[] cdr = {0, 1, 0, -1};
	static int[] cdc = {1, 0, -1, 0};

	static int[][][] cleanOffsetsR = {
		{{0, 0, -1, 1}},
		{{0, 1, 0, 0}},
		{{0, 0, 1, -1}},
		{{0, -1, 0, 0}}
	};

	static class Cleaner {
		int id;
		int r;
		int c;
		int dir;

		Cleaner(int id, int r, int c) {
			this.id = id;
			this.r = r;
			this.c = c;
			this.dir = 0;
		}
	}

	static class Node implements Comparable<Node> {
		int r, c;
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(Node o) {
			if (this.r != o.r) return Integer.compare(this.r, o.r);
			return Integer.compare(this.c, o.c);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		isCleaner = new boolean[N + 1][N + 1];

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			cleaners.add(new Cleaner(i, r, c));
			isCleaner[r][c] = true;
		}

		for (int t = 0; t < L; t++) {
			moveCleaners();
			cleanSpaces();
			accumulateDust();
			diffuseDust();

			System.out.println(getTotalDust());
		}
	}

	static void moveCleaners() {
		for (Cleaner cleaner : cleaners) {
			isCleaner[cleaner.r][cleaner.c] = false;
			Node target = findTarget(cleaner.r, cleaner.c);
			if (target != null) {
				cleaner.r = target.r;
				cleaner.c = target.c;
			}
			isCleaner[cleaner.r][cleaner.c] = true;
		}
	}

	static Node findTarget(int startR, int startC) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N + 1][N + 1];
		List<Node> candidates = new ArrayList<>();

		q.add(new int[]{startR, startC, 0});
		visited[startR][startC] = true;
		int minDist = Integer.MAX_VALUE;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int dist = curr[2];

			if (dist > minDist) break;

			if (map[r][c] > 0) {
				if (minDist == Integer.MAX_VALUE) minDist = dist;
				if (dist == minDist) candidates.add(new Node(r, c));
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 1 || nr > N || nc < 1 || nc > N || visited[nr][nc]) continue;
				if (map[nr][nc] == -1 || isCleaner[nr][nc]) continue;

				visited[nr][nc] = true;
				q.add(new int[]{nr, nc, dist + 1});
			}
		}

		if (candidates.isEmpty()) return null;
		Collections.sort(candidates);
		return candidates.get(0);
	}

	static void cleanSpaces() {
		for (Cleaner cleaner : cleaners) {
			int maxDustSum = -1;
			int bestDir = cleaner.dir;

			for (int d = 0; d < 4; d++) {
				int sum = 0;

				int[] idxs = {d, (d + 3) % 4, (d + 1) % 4};

				if (map[cleaner.r][cleaner.c] > 0) {
					sum += Math.min(map[cleaner.r][cleaner.c], 20);
				}
				for (int idx : idxs) {
					int nr = cleaner.r + cdr[idx];
					int nc = cleaner.c + cdc[idx];
					if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] > 0) {
						sum += Math.min(map[nr][nc], 20);
					}
				}

				if (sum > maxDustSum) {
					maxDustSum = sum;
					bestDir = d;
				}
			}

			cleaner.dir = bestDir;
			int d = bestDir;

			if (map[cleaner.r][cleaner.c] > 0) {
				map[cleaner.r][cleaner.c] = Math.max(0, map[cleaner.r][cleaner.c] - 20);
			}

			int[] idxs = {d, (d + 3) % 4, (d + 1) % 4};
			for (int idx : idxs) {
				int nr = cleaner.r + cdr[idx];
				int nc = cleaner.c + cdc[idx];
				if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] > 0) {
					map[nr][nc] = Math.max(0, map[nr][nc] - 20);
				}
			}
		}
	}

	static void accumulateDust() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] > 0) {
					map[i][j] += 5;
				}
			}
		}
	}

	static void diffuseDust() {
		int[][] addMap = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 0) {
					int neighborSum = 0;
					for (int d = 0; d < 4; d++) {
						int ni = i + dr[d];
						int nj = j + dc[d];
						if (ni >= 1 && ni <= N && nj >= 1 && nj <= N && map[ni][nj] > 0) {
							neighborSum += map[ni][nj];
						}
					}
					addMap[i][j] = neighborSum / 10;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 0) {
					map[i][j] += addMap[i][j];
				}
			}
		}
	}

	static int getTotalDust() {
		int total = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] > 0) {
					total += map[i][j];
				}
			}
		}
		return total;
	}
}