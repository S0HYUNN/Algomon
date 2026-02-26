import java.io.*;
import java.util.*;


public class Main {
	static int answer = -1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int p1 = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());

		int m = Integer.parseInt(br.readLine());
		List<List<Integer>> graph = new ArrayList<>();
		boolean[] visited = new boolean[n + 1];
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(p).add(c);
			graph.get(c).add(p);
		}

		dfs( p1, p2, graph, 0, visited);

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static void dfs(int s, int e, List<List<Integer>> graph, int count, boolean[] visited){
		if(s == e) {
			answer = count;
			return;
		}
		visited[s] = true;
		for(int nx : graph.get(s)) {
			if (!visited[nx]) {
				dfs(nx, e, graph, count + 1, visited);
			}
		}
	}
}
