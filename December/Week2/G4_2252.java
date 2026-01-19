import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] indegree = new int[N + 1];
		List<List<Integer>> graph = new ArrayList<>();

		for(int i = 0; i <= N + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			graph.get(A).add(B);
			indegree[B]++;
		}

		Queue<Integer> q = new ArrayDeque<>();

		for(int i = 1; i < indegree.length; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}

		while(!q.isEmpty()) {
			int cur = q.poll();
			System.out.print(cur + " ");

			List<Integer> list = graph.get(cur);

			for(int i = 0; i < list.size(); i++) {
				int tmp = list.get(i);
				indegree[tmp]--;
				if(indegree[tmp] == 0) {
					q.offer(tmp);
				}
			}
		}
	}
}