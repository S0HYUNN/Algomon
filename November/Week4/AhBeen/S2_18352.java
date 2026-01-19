import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
		}

		int[] distance = new int[N + 1];
		Arrays.fill(distance, -1);
		distance[X] = 0;

		Queue<Integer> q = new ArrayDeque<>();
		q.add(X);

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int nx : graph.get(cur)) {
				if (distance[nx] == -1) {
					distance[nx] = distance[cur] + 1;
					q.add(nx);
				}
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (distance[i] == K) {
				list.add(i);
			}
		}
		if (list.isEmpty()) {
			System.out.println(-1);
		} else {
			Collections.sort(list);
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}
}