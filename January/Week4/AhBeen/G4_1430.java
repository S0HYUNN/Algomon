import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import org.w3c.dom.Node;

public class Main {
	static int N, R, D, X, Y;
	static int[] xs, ys;
	static List<Integer>[] graph;
	static int R2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		xs = new int[N];
		ys = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			xs[i] = Integer.parseInt(st.nextToken());
			ys[i] = Integer.parseInt(st.nextToken());
		}

		R2 = R * R;
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();


		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (dist(xs[i], ys[i], xs[j], ys[j]) <= R2) {
					graph[i].add(j);
					graph[j].add(i);
				}
			}
		}

		int[] distHop = new int[N];
		Arrays.fill(distHop, -1);
		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			if (dist(xs[i], ys[i], X, Y) <= R2) {
				distHop[i] = 0;
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph[cur]) {
				if (distHop[next] == -1) {
					distHop[next] = distHop[cur] + 1;
					q.add(next);
				}
			}
		}

		double answer = 0.0;
		for (int i = 0; i < N; i++) {
			if (distHop[i] >= 0) {
				answer += D * Math.pow(0.5, distHop[i]);
			}
		}
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static int dist(int x1, int y1, int x2, int y2) {
		int dx = x1 - x2;
		int dy = y1 - y2;

		return dx * dx + dy * dy;
	}
}
