import java.util.*;

class Solution {
	public int solution(int[][] land) {
		int n = land.length;
		int m = land[0].length;

		int[] oil = new int[m];
		boolean[][] visited = new boolean[n][m];

		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		Queue<int[]> q = new ArrayDeque<>();

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(land[i][j] == 1 && !visited[i][j]) {
					q.add(new int[]{i, j});
					visited[i][j] = true;

					int count = 1;
					Set<Integer> set = new HashSet<>();

					while(!q.isEmpty()) {
						int[] cur = q.poll();
						set.add(cur[1]);

						for(int k = 0; k < 4; k++) {
							int nx = cur[0] + dx[k];
							int ny = cur[1] + dy[k];

							if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

							if(land[nx][ny] == 1 && !visited[nx][ny]) {
								visited[nx][ny] = true;
								q.add(new int[]{nx, ny});
								count++;
							}
						}
					}

					for(int idx : set) {
						oil[idx] += count;
					}
				}
			}
		}

		int answer = 0;
		for(int i = 0; i < m; i++) {
			answer = Math.max(answer, oil[i]);
		}

		return answer;
	}
}
