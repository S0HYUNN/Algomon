import java.io.*;
import java.util.*;

public class Main {

	static class Node {
		int idx, cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		List<List<Node>> graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		StringTokenizer st;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(s).add(new Node(e, c));
		}

		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

		st = new StringTokenizer(br.readLine());

		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		pq.offer(new Node(s, 0));
		dist[s] = 0;

		while(!pq.isEmpty()) {
			Node cur = pq.poll();

			if(cur.idx == e) {
				System.out.println(dist[cur.idx]);
				return;
			}
			if(dist[cur.idx] < cur.cost) {
				continue;
			}
			for(int i = 0; i < graph.get(cur.idx).size(); i++) {
				Node nx = graph.get(cur.idx).get(i);
				if(dist[nx.idx] > cur.cost + nx.cost) {
					dist[nx.idx] = cur.cost + nx.cost;
					pq.offer(new Node(nx.idx, dist[nx.idx]));
				}
			}
		}
	}
}