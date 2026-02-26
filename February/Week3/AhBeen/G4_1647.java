import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node>{
		int from;
		int to;
		int cost;

		Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Node> graph = new ArrayList<>();

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken());
			graph.add(new Node(A, B, C));
		}
		parent = new int[N];
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		Collections.sort(graph);

		int totalCost = 0;
		int maxEdge = 0;

		for(Node n : graph) {
			if(find(n.from) != find(n.to)) {
				union(n.from, n.to);
				totalCost += n.cost;
				maxEdge = Math.max(maxEdge, n.cost);
			}
		}

		System.out.println(totalCost - maxEdge);
	}

	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) parent[b] = a;
	}
}