import java.util.*;
import java.io.*;

public class Main {
	static class Node {
		int idx;
		int cost;
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		List<List<Node>> graph = new ArrayList<>();
		List<List<Node>> reverse = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			reverse.add(new ArrayList<>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(s).add(new Node(e, c));
			reverse.get(e).add(new Node(s, c));
		}

		int[] dist1 = dijkstra(reverse, X, N);
		int[] dist2 = dijkstra(graph, X, N);

		int max = 0;
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, dist1[i] + dist2[i]);
		}

		System.out.println(max);
	}

	static int[] dijkstra(List<List<Node>> graph, int start, int N) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int now = cur.idx;
			int nowCost = cur.cost;

			if (dist[now] < nowCost) continue;

			for (Node next : graph.get(now)) {
				int cost = nowCost + next.cost;
				if (dist[next.idx] > cost) {
					dist[next.idx] = cost;
					pq.offer(new Node(next.idx, cost));
				}
			}
		}
		return dist;
	}
}