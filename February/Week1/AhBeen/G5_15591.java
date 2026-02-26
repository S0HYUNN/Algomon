import java.io.*;
import java.util.*;


public class Main {
	static class Node {
		int idx;
		int usado;
		Node(int idx, int usado) {
			this.idx = idx;
			this.usado = usado;
		}
	}
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			graph.get(p).add(new Node(q, r));
			graph.get(q).add(new Node(p, r));
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());

			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			int count = 0;

			Queue<Integer> q = new ArrayDeque<>();
			boolean[] visited = new boolean[N + 1];
			visited[v] = true;
			q.add(v);

			while (!q.isEmpty()) {
				int cur = q.poll();
				List<Node> list = graph.get(cur);
				for (Node node : list) {
					if (!visited[node.idx] && node.usado >= k) {
						count++;
						q.add(node.idx);
						visited[node.idx] = true;
					}
				}
			}
			bw.write(count + "\n");
		}
		bw.flush();
		bw.close();
	}
}
