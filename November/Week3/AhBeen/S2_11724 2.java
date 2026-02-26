import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>();
		boolean[] visited = new boolean[N + 1];
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		int count = 0;
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				dfs(i, graph, visited);
				count++;
			}
		}
		System.out.println(count);
	}

	static void dfs(int a, List<List<Integer>> graph, boolean[] visited) {
		visited[a] = true;
		for(int b : graph.get(a)) {
			if(!visited[b]) {
				dfs(b, graph, visited);
			}
		}
	}
}