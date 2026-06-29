import java.util.*;
import java.io.*;

public class Main {
	static int K;
	static int M;

	static int[][] map = new int[5][5];

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static class Node {
		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] wallNumbers;
	static int wallIdx = 0;

	static class Result {
		int r, c, angle, score;
		Result(int r, int c, int angle, int score) {
			this.r = r;
			this.c = c;
			this.angle = angle;
			this.score = score;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		wallNumbers = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			wallNumbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int t = 0; t < K; t++) {
			int turnScore = 0;

			Result best = findBestRotation();

			if (best.score == 0) {
				break;
			}

			for (int i = 0; i < best.angle; i++) {
				rotate(best.r, best.c, map);
			}

			while (true) {
				int gainScore = removeAndCount();
				if (gainScore == 0) break;

				turnScore += gainScore;
				fillEmptySpaces();
			}

			System.out.print(turnScore + " ");
		}
	}

	static Result findBestRotation() {
		Result best = new Result(5, 5, 4, 0);

		for (int angle = 1; angle <= 3; angle++) {
			for (int c = 1; c <= 3; c++) {
				for (int r = 1; r <= 3; r++) {

					int[][] tmpMap = copyMap(map);

					for (int i = 0; i < angle; i++) {
						rotate(r, c, tmpMap);
					}

					int score = gain(tmpMap);

					if (score > best.score) {
						best = new Result(r, c, angle, score);
					}
				}
			}
		}
		return best;
	}

	static void rotate(int r, int c, int[][] targetMap) {
		int[][] small = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				small[i][j] = targetMap[r - 1 + i][c - 1 + j];
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				targetMap[r - 1 + j][c - 1 + (2 - i)] = small[i][j];
			}
		}
	}

	static int gain(int[][] tmp) {
		int totalScore = 0;
		boolean[][] visited = new boolean[5][5];

		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(visited[i][j]) continue;

				Queue<Node> q = new ArrayDeque<>();
				List<Node> list = new ArrayList<>();

				int targetNum = tmp[i][j];

				q.add(new Node(i, j));
				list.add(new Node(i, j));
				visited[i][j] = true;

				while(!q.isEmpty()) {
					Node cur = q.poll();

					for(int d = 0; d < 4; d++) {
						int nr = cur.r + dr[d];
						int nc = cur.c + dc[d];

						if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
						if(visited[nr][nc] || tmp[nr][nc] != targetNum) continue;

						visited[nr][nc] = true;
						q.add(new Node(nr, nc));
						list.add(new Node(nr, nc));
					}
				}

				if(list.size() >= 3) {
					totalScore += list.size();
				}
			}
		}

		return totalScore;
	}

	static int removeAndCount() {
		int totalScore = 0;
		boolean[][] visited = new boolean[5][5];
		List<Node> eraseList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (visited[i][j] || map[i][j] == 0) continue;

				Queue<Node> q = new ArrayDeque<>();
				List<Node> list = new ArrayList<>();
				int targetNum = map[i][j];

				q.add(new Node(i, j));
				list.add(new Node(i, j));
				visited[i][j] = true;

				while (!q.isEmpty()) {
					Node cur = q.poll();
					for (int d = 0; d < 4; d++) {
						int nr = cur.r + dr[d];
						int nc = cur.c + dc[d];

						if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
						if (visited[nr][nc] || map[nr][nc] != targetNum) continue;

						visited[nr][nc] = true;
						q.add(new Node(nr, nc));
						list.add(new Node(nr, nc));
					}
				}

				if (list.size() >= 3) {
					totalScore += list.size();
					eraseList.addAll(list);
				}
			}
		}

		for (Node node : eraseList) {
			map[node.r][node.c] = 0;
		}

		return totalScore;
	}

	static void fillEmptySpaces() {
		for (int j = 0; j < 5; j++) {
			for (int i = 4; i >= 0; i--) {
				if (map[i][j] == 0) {
					map[i][j] = wallNumbers[wallIdx++];
				}
			}
		}
	}

	static int[][] copyMap(int[][] origin) {
		int[][] tmp = new int[5][5];
		for (int i = 0; i < 5; i++) {
			tmp[i] = origin[i].clone();
		}
		return tmp;
	}
}