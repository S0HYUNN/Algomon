import java.util.*;
import java.io.*;

public class Main {
	static class Node {
		int x, y, dist;
		int destroy;

		Node(int x, int y, int dist, int destroy) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.destroy = destroy;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		System.out.println(bfs(map, N, M));
	}

	static int bfs(int[][] map, int N, int M) {
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};

		boolean[][][] visited = new boolean[N][M][2];

		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, 1, 0));
		visited[0][0][0] = true;

		while(!q.isEmpty()) {
			Node cur = q.poll();

			if(cur.x == N - 1 && cur.y == M - 1) {
				return cur.dist;
			}

			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

				if(map[nx][ny] == 0 && !visited[nx][ny][cur.destroy]) {
					visited[nx][ny][cur.destroy] = true;
					q.add(new Node(nx, ny, cur.dist + 1, cur.destroy));
				} else if (map[nx][ny] == 1 && cur.destroy == 0 && !visited[nx][ny][1]) {
					visited[nx][ny][1] = true;
					q.add(new Node(nx, ny, cur.dist + 1, 1));
				}
			}
		}
		return -1;
	}
}