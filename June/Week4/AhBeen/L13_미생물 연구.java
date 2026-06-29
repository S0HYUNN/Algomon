import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;
	static List<int[]> microInsertionList;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static Set<int[]> microList;
	static class MicroInfo {
		int index, size;
		Set<Integer> adjacentSet;
		MicroInfo(int index, int size, Set<Integer> adjacentSet) {
			this.index = index;
			this.size = size;
			this.adjacentSet = adjacentSet;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		microInsertionList = new ArrayList<>();
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			int endR = Integer.parseInt(st.nextToken());
			int endC = Integer.parseInt(st.nextToken());
			microInsertionList.add(new int[]{startR, startC, endR, endC});
		}

		for (int i = 1; i <= q; i++) {
			int[] coords = microInsertionList.get(i - 1);
			insertMicro(coords, i);
			moveMicro();
			recordResult();
		}
	}

	static void insertMicro(int[] coords, int index) {
		int[][] newMap = map;
		int sr = coords[0]; int sc = coords[1];
		int er = coords[2]; int ec = coords[3];
		for (int i = sc; i < ec; i++) {
			for (int j = sr; j < er; j++) {
				newMap[i][j] = index;
			}
		}

		Map<Integer, Integer> microMap = new HashMap<>();
		microList = new TreeSet<>((a, b) -> {
			if (a[1] != b[1]) {
				return Integer.compare(b[1], a[1]);
			} else {
				return Integer.compare(a[0], b[0]);
			}
		});
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (newMap[i][j] != 0) {
					if (visited[i][j]) continue;
					MicroInfo info = bfs(i, j, newMap);
					if (info.index == newMap[i][j]) {
						microMap.put(info.index, microMap.getOrDefault(info.index, 0) + 1);
						if (microMap.get(info.index) > 1) {
							newMap = removeMicro(newMap, info.index);
							continue;
						}
					}
					microList.add(new int[]{info.index, info.size});
				}
			}
		}

		map = newMap;
	}

	static void moveMicro() {
		int[][] newMap = new int[n][n];

		for (int[] info : microList) {
			int id = info[0];
			List<int[]> shape = findOriginalPos(id);

			int maxR = 0, maxC = 0;
			for (int[] p : shape) {
				maxR = Math.max(maxR, p[0]);
				maxC = Math.max(maxC, p[1]);
			}

			boolean placed = false;
			for (int c = 0; c + maxC < n && !placed; c++) {
				for (int r = 0; r + maxR < n; r++) {
					boolean isValid = true;
					for (int[] p : shape) {
						int nr = r + p[0];
						int nc = c + p[1];
						if (newMap[nr][nc] != 0) {
							isValid = false;
							break;
						}
					}
					if (!isValid) continue;

					for (int[] p : shape) {
						newMap[r + p[0]][c + p[1]] = id;
					}
					placed = true;
					break;
				}
			}
		}

		map = newMap;
		microList.clear();
	}

	static void recordResult() {
		visited = new boolean[n][n];

		List<MicroInfo> infos = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					MicroInfo info = bfs(i, j, map);
					infos.add(info);
				}
			}
		}

		Set<String> pairSet = new HashSet<>();
		for (MicroInfo info : infos) {
			int a = info.index;
			for (int b : info.adjacentSet) {
				if (b == 0 || a == b) continue;
				int u = Math.min(a, b);
				int v = Math.max(a, b);
				pairSet.add(u + "," + v);
			}
		}

		Map<Integer, Integer> sizeMap = new HashMap<>();
		for (MicroInfo info : infos) {
			sizeMap.put(info.index, info.size);
		}

		int result = 0;
		for (String pair : pairSet) {
			String[] parts = pair.split(",");
			int a = Integer.parseInt(parts[0]);
			int b = Integer.parseInt(parts[1]);
			result += sizeMap.get(a) * sizeMap.get(b);
		}
		System.out.println(result);
	}

	static MicroInfo bfs(int r, int c, int[][] map) {
		Queue<int[]> queue  = new ArrayDeque<>();
		queue.add(new int[]{r, c});
		visited[r][c] = true;

		int index = map[r][c];
		int size = 1;
		Set<Integer> adSet = new HashSet<>();
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dx[i];
				int nc = curr[1] + dy[i];

				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				if (visited[nr][nc]) continue;
				if (map[nr][nc] != map[r][c]) {
					adSet.add(map[nr][nc]);
				}
				if (map[nr][nc] == map[r][c]) {
					queue.add(new int[]{nr, nc});
					visited[nr][nc] = true;
					size++;
				}
			}
		}

		return new MicroInfo(index, size, adSet);
	}

	static int[][] removeMicro(int[][] map, int target) {
		int[][] newMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == target) map[i][j] = 0;
			}
		}
		newMap = map;
		return newMap;
	}

	static List<int[]> findOriginalPos(int id) {
		List<int[]> abs = new ArrayList<>();
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];
		boolean found = false;
		for (int r = 0; r < n && !found; r++) {
			for (int c = 0; c < n; c++) {
				if (map[r][c] == id) {
					q.add(new int[] { r, c });
					visited[r][c] = true;
					found = true;
					break;
				}
			}
		}

		int minR = n, minC = n;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			abs.add(cur);
			minR = Math.min(minR, cur[0]);
			minC = Math.min(minC, cur[1]);

			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dx[d];
				int nc = cur[1] + dy[d];
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				if (!visited[nr][nc] && map[nr][nc] == id) {
					visited[nr][nc] = true;
					q.add(new int[]{nr, nc});
				}
			}
		}

		List<int[]> rel = new ArrayList<>(abs.size());
		for (int[] p : abs) rel.add(new int[]{p[0] - minR, p[1] - minC});
		return rel;
	}
}
