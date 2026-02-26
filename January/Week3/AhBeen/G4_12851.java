import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static class Node {
		int t;
		int count;
		public Node(int t, int count) {
			this.t = t;
			this.count = count;
		}
	}
	static final int MAX = 100000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Node node = bfs(N, K);
		System.out.println(node.t);
		System.out.println(node.count);
	}

	static Node bfs(int N, int K) {
		int[] dist = new int[MAX + 1];
		int[] count = new int[MAX + 1];
		Arrays.fill(dist, -1);
		Arrays.fill(count, 0);
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);
		dist[N] = 0;
		count[N] = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();

			if(dist[K] != -1 && dist[cur] > dist[K]) {
				break;
			}
			int[] next = {cur - 1, cur + 1, cur * 2};
			for(int nx : next) {
				if(nx < 0 || nx > MAX) continue;
				if (dist[nx] == -1) {
					dist[nx] = dist[cur] + 1;
					count[nx] = count[cur];
					q.offer(nx);
				} else if (dist[nx] == dist[cur] + 1) {
					count[nx] += count[cur];
				}
			}
		}
		return new Node(dist[K], count[K]);
	}
}
