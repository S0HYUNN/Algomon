import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static final int MAX = 100000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		System.out.println(bfs(N, K));
	}

	static int bfs(int N, int K) {
		boolean[] visited = new boolean[MAX + 1];
		int[] dist = new int[MAX + 1];

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);
		visited[N] = true;

		while(!q.isEmpty()) {
			int cur = q.poll();

			if(cur == K) {
				return dist[cur];
			}
			int[] next = {cur - 1, cur + 1, cur * 2};
			for(int nx : next) {
				if(nx < 0 || nx > MAX) continue;
				if(visited[nx]) continue;

				visited[nx] = true;
				dist[nx] = dist[cur] + 1;
				q.offer(nx);
			}
		}
		return -1;
	}
}
