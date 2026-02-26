import java.util.Scanner;

public class Main {
	static int count = 0;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] grid = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				grid[i][j] = sc.nextInt();
		int[][] targets = new int[m][2];
		for (int i = 0; i < m; i++) {
			targets[i][0] = sc.nextInt() - 1;
			targets[i][1] = sc.nextInt() - 1;
		}

		boolean[][] visited = new boolean[n][n];
		visited[targets[0][0]][targets[0][1]] = true;

		dfs(grid, targets, visited, 1, n, m, targets[0][0], targets[0][1]);

		System.out.println(count);
	}

	static void dfs(int[][] grid, int[][] targets, boolean[][] visited, int depth, int n, int m, int x, int y) {
		if(depth == m) {
			count++;
			return;
		}

		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;


			if(!visited[nx][ny] && grid[nx][ny] != 1) {
				if(targets[depth][0] == nx && targets[depth][1] == ny) {
					visited[nx][ny] = true;
					dfs(grid, targets, visited, depth + 1, n, m, nx, ny);
					visited[nx][ny] = false;
				} else {
					visited[nx][ny] = true;
					dfs(grid, targets, visited, depth, n, m, nx, ny);
					visited[nx][ny] = false;
				}
			}

		}
	}
}