import java.util.*;
import java.io.*;

public class Main {
	static int L;
	static int N;
	static int Q;
	static int[][] map;
	static int[][] knightMap;

	static class Knight {
		int id;
		int r;
		int c;
		int h;
		int w;
		int k;
		int initK;
		int totalDamage = 0;

		Knight() {}

		Knight(int id, int r, int c, int h, int w, int k) {
			this.id = id;
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
			this.initK = k;
		}
	}

	static List<Knight> knights = new ArrayList<>();

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		map = new int[L + 1][L + 1];
		knightMap = new int[L + 1][L + 1];

		knights.add(new Knight());

		for (int i = 1; i <= L; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= L; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			knights.add(new Knight(i, r, c, h, w, k));
		}

		updateKnightMap();

		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());

			int i = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			move(i, d);
		}

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			if (knights.get(i).k > 0) {
				answer += knights.get(i).totalDamage;
			}
		}
		System.out.println(answer);
	}

	static void move(int i, int d) {
		if(knights.get(i).k <= 0) return;

		Queue<Integer> q = new ArrayDeque<>();
		Set<Integer> movedKnights = new HashSet<>();

		q.add(i);
		movedKnights.add(i);

		while(!q.isEmpty()) {
			int curId = q.poll();
			Knight cur = knights.get(curId);

			int nr = cur.r + dr[d];
			int nc = cur.c + dc[d];

			for (int r = nr; r < nr + cur.h; r++) {
				for (int c = nc; c < nc + cur.w; c++) {
					if (r < 1 || r > L || c < 1 || c > L || map[r][c] == 2) {
						return;
					}

					if (knightMap[r][c] != 0 && knightMap[r][c] != curId) {
						int nextId = knightMap[r][c];
						if (!movedKnights.contains(nextId)) {
							movedKnights.add(nextId);
							q.add(nextId);
						}
					}
				}
			}
		}

		for (int id : movedKnights) {
			Knight k = knights.get(id);
			k.r += dr[d];
			k.c += dc[d];

			if (id == i) continue;

			int damage = 0;
			for (int r = k.r; r < k.r + k.h; r++) {
				for (int c = k.c; c < k.c + k.w; c++) {
					if (map[r][c] == 1) {
						damage++;
					}
				}
			}

			k.k -= damage;
			k.totalDamage += damage;
		}

		updateKnightMap();
	}

	static void updateKnightMap() {
		for (int i = 1; i <= L; i++) {
			Arrays.fill(knightMap[i], 0);
		}

		for (int i = 1; i <= N; i++) {
			Knight k = knights.get(i);
			if (k.k <= 0) continue;

			for (int r = k.r; r < k.r + k.h; r++) {
				for (int c = k.c; c < k.c + k.w; c++) {
					knightMap[r][c] = k.id;
				}
			}
		}
	}
}