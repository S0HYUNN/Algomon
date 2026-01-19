import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			List<List<Integer>> graph = new ArrayList<>();
			for(int j = 0; j < V; j++) {
				graph.add(new ArrayList<>());
			}
			for (int j = 0; j < E; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;

				graph.get(a).add(b);
				graph.get(b).add(a);
			}

			int[] color = new int[V];
			boolean flag = true;

			for(int j = 0; j < V && flag; j++) {
				if(color[j] == 0) {
					Queue<Integer> q = new ArrayDeque<>();
					q.add(j);
					color[j] = 1;

					while(!q.isEmpty() && flag) {
						int cur = q.poll();
						for(int next : graph.get(cur)) {
							if(color[next] == 0) {
								color[next] = -color[cur];
								q.add(next);
							} else if(color[next] == color[cur]) {
								flag = false;
								break;
							}
						}
					}
				}
			}
			System.out.println(flag ? "YES" : "NO");
		}
	}
}