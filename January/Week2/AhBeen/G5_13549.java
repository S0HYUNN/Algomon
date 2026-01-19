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
		int[] dist = new int[MAX + 1];
		Arrays.fill(dist, -1);

		Deque<Integer> dq = new ArrayDeque<>();
		dq.offer(N);
		dist[N] = 0;
		while(!dq.isEmpty()) {
			int cur = dq.poll();

			if(cur == K) {
				return dist[cur];
			}
			int[] next = {cur * 2, cur - 1, cur + 1};
			for(int i = 0; i < next.length; i++) {
				int nx = next[i];
				if (nx < 0 || nx > MAX) continue;
				if (dist[nx] != -1) continue;

				if(i == 0) {
					dist[nx] = dist[cur];
					dq.offerFirst(nx);
				} else {
					dist[nx] = dist[cur] + 1;
					dq.offerLast(nx);
				}
			}
		}
		return -1;
	}
}
