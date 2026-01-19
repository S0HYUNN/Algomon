import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] move = new int[101];
		for (int i = 1; i <= 100; i++) {
			move[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			move[from] = to;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			move[from] = to;
		}
		boolean[] visited = new boolean[101];
		Queue<int[]> q = new ArrayDeque<>();

		q.add(new int[]{1, 0});
		visited[1] = true;

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int pos = cur[0];
			int count = cur[1];

			if(pos == 100) {
				System.out.println(count);
				return;
			}
			for(int dice = 1; dice <= 6; dice++) {
				int next = pos + dice;
				if(next > 100) continue;

				next = move[next];

				if(!visited[next]) {
					visited[next] = true;
					q.add(new int[] {next, count + 1});
				}
			}
		}
	}
}