import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int m;
		int v;

		Node(int m, int v) {
			this.m = m;
			this.v = v;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<Node> list = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			list.add(new Node(M, V));
		}

		int[] bag = new int[K];
		for(int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}

		Collections.sort(list, (a, b) -> a.m - b.m);
		Arrays.sort(bag);

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		long total = 0;
		int idx = 0;

		for(int b : bag) {
			while(idx < N && list.get(idx).m <= b) {
				pq.add(list.get(idx).v);
				idx++;
			}
			if(!pq.isEmpty()) {
				total += pq.poll();
			}
		}

		System.out.println(total);
	}
}