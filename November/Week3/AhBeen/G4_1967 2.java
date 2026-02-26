import java.util.*;
import java.io.*;

public class Main {
	static List<List<Node>> graph;
	static boolean[] visited;
	static int farNode = 0;
	static int maxDist = 0;

	static class Node {
		int number;
		int weight;

		public Node(int number, int weight) {
			this.number = number;
			this.weight = weight;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		StringTokenizer st;

		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph.get(n).add(new Node(c, w));
			graph.get(c).add(new Node(n, w));
		}

		visited = new boolean[N + 1];
		visited[1] = true;
		dfs(1, 0);

		int start = farNode;
		visited = new boolean[N + 1];
		visited[start] = true;
		dfs(start, 0);

		System.out.print(maxDist);
	}

	static void dfs(int now, int dist) {
		if(dist > maxDist) {
			maxDist = dist;
			farNode = now;
		}

		for(Node next : graph.get(now)) {
			if(!visited[next.number]) {
				visited[next.number] = true;
				dfs(next.number, dist + next.weight);
			}
		}
	}
}