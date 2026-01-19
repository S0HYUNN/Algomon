import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<List<Integer>> graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i = 0 ; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		boolean[] visited = new boolean[N + 1];
		int[] parent = new int[N + 1];

		dfs(1, graph, visited, parent);
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; i++) {
			sb.append(parent[i]).append("\n");
		}
		System.out.print(sb);
	}
	static void dfs(int node, List<List<Integer>> graph, boolean[] visited, int[] parent) {
		visited[node] = true;
		for(int nx : graph.get(node)) {
			if(!visited[nx]) {
				parent[nx] = node;
				dfs(nx, graph, visited, parent);
			}
		}
	}
}