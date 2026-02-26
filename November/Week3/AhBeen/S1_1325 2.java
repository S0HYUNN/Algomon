import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>();

		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(b).add(a);
		}

		int[] arr = new int[N + 1];
		int max = 0;
		for(int i = 0; i <= N; i++) {
			boolean[] visited = new boolean[N + 1];
			int count = bfs(i, visited, graph);
			arr[i] = count;
			max = Math.max(count, max);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			if(arr[i] == max) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb);
	}

	static int bfs(int start, boolean[] visited, List<List<Integer>> graph) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		visited[start] = true;
		int count = 1;

		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next : graph.get(cur)) {
				if(!visited[next]) {
					visited[next] = true;
					q.add(next);
					count++;
				}
			}
		}
		return count;
	}
}