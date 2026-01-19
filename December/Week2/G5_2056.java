import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		List<List<Integer>> graph = new ArrayList<>();

		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		int[] indegree = new int[N + 1];
		int[] t = new int[N + 1];

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			t[i] = Integer.parseInt(st.nextToken());

			int n = Integer.parseInt(st.nextToken());
			for(int j = 0; j < n; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				graph.get(tmp).add(i);
				indegree[i]++;
			}
		}
		System.out.println(topologicalSort(N, graph, indegree, t));
	}

	static int topologicalSort(int N, List<List<Integer>> graph, int[] indegree, int[] t) {
		Queue<Integer> q = new ArrayDeque<>();

		int[] result = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			result[i] = t[i];

			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		while (!q.isEmpty()) {
			int cur = q.poll();
			for(int nx : graph.get(cur)) {
				indegree[nx]--;
				result[nx] = Math.max(result[nx], result[cur] + t[nx]);

				if(indegree[nx] == 0) {
					q.offer(nx);
				}
			}
		}

		int max = Integer.MIN_VALUE;
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, result[i]);
		}

		return max;
	}
}