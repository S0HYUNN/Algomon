import java.util.*;
import java.io.*;

public class Main {
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N + 1];

		int root = -1;
		List<List<Integer>> graph = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if(parent == -1) {
				root = i;
			} else {
				graph.get(i).add(parent);
				graph.get(parent).add(i);
			}
		}
		int removeNode = Integer.parseInt(br.readLine());
		if(removeNode == root) {
			System.out.print(0);
			return;
		} else {
			dfs(root, graph, visited, removeNode);
		}
		System.out.print(count);
	}

	static void dfs(int node, List<List<Integer>> graph, boolean[] visited, int removeNode) {
		visited[node] = true;
		int n = 0;
		for(int next : graph.get(node)) {
			if(next != removeNode && !visited[next]) {
				n++;
				dfs(next, graph, visited, removeNode);
			}
		}
		if(n == 0) {
			count++;
		}
	}
}