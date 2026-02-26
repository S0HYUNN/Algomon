import java.io.*;
import java.util.*;


public class Main {
	static int count = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int c = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[c + 1];

		List<List<Integer>> graph = new ArrayList<>();
		for(int i = 0; i <= c; i++) {
			graph.add(new ArrayList<>());
		}
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		visited[1] = true;

		bfs(q, graph, visited);
		// dfs(1, graph, visited);

		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
	}

	// static void dfs(int cur, List<List<Integer>> graph, boolean[] visited){
	// 	visited[cur] = true;
	//
	// 	for(int nx : graph.get(cur)) {
	// 		if(!visited[nx]) {
	// 			count++;
	// 			dfs(nx, graph, visited);
	// 		}
	// 	}
	// }

	static void bfs(Queue<Integer> q, List<List<Integer>> graph, boolean[] visited) {
		while(!q.isEmpty()) {
			int cur = q.poll();

			for(int nx : graph.get(cur)) {
				if(!visited[nx]) {
					visited[nx] = true;
					count++;
					q.add(nx);
				}
			}
		}
	}
}
