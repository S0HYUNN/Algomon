import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		int[] indegree = new int[N + 1];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			int prev = Integer.parseInt(st.nextToken());

			for(int j = 1; j < count; j++) {
				int nx = Integer.parseInt(st.nextToken());
				graph.get(prev).add(nx);
				indegree[nx]++;
				prev = nx;
			}
		}

		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}

		List<Integer> result = new ArrayList<>();

		while(!q.isEmpty()) {
			int cur = q.poll();
			result.add(cur);

			for(int nx : graph.get(cur)) {
				indegree[nx]--;
				if(indegree[nx] == 0) {
					q.offer(nx);
				}
			}
		}

		if(result.size() != N) {
			System.out.println(0);
		} else {
			StringBuilder sb = new StringBuilder();
			for(int x : result) {
				sb.append(x).append("\n");
			}
			System.out.print(sb);
		}
	}
}