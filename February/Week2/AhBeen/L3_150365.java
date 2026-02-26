class Solution {
	static int[] dx = {1, 0, 0, -1};
	static int[] dy = {0, -1, 1, 0};
	static char[] dir = {'d', 'l', 'r', 'u'};

	static boolean found;
	static String answer;

	public String solution(int n, int m, int x, int y, int r, int c, int k) {
		found = false;
		answer = "";

		x--; y--; r--; c--;

		int dist = Math.abs(x - r) + Math.abs(y - c);
		if (dist > k || (k - dist) % 2 == 1) return "impossible";

		dfs(n, m, x, y, r, c, k, 0, new StringBuilder());
		return answer;
	}

	static void dfs(int n, int m, int x, int y,
		int r, int c, int k, int depth, StringBuilder sb) {

		if (found) return;

		int dist = Math.abs(x - r) + Math.abs(y - c);
		int remain = k - depth;

		if (dist > remain) return;

		if (depth == k) {
			if (x == r && y == c) {
				answer = sb.toString();
				found = true;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

			sb.append(dir[i]);
			dfs(n, m, nx, ny, r, c, k, depth + 1, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}
