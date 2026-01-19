import java.util.*;
import java.io.*;

public class Main {
	static class Node implements Comparable<Node> {
		int start, end;
		long cost;

		Node(int start, int end, long cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		List<Node> graph = new ArrayList<>();

		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				long cost = Long.parseLong(st.nextToken());
				if(i < j) {
					graph.add(new Node(i, j, cost));
				}
			}
		}

		parent = new int[N];
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}

		Collections.sort(graph);

		long result = 0;
		int count = 0;

		for(Node n : graph) {
			if(find(n.start) != find(n.end)) {
				union(n.start, n.end);
				result += n.cost;
				count++;
				if(count == N - 1) break;
			}
		}

		System.out.println(result);
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