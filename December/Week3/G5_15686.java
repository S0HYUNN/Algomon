import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static List<int[]> houses = new ArrayList<>();
	static List<int[]> chickens = new ArrayList<>();
	static boolean[] selected;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int v = Integer.parseInt(st.nextToken());
				if(v == 1) {
					houses.add(new int[]{i, j});
				} else if(v == 2){
					chickens.add(new int[]{i, j});
				}
			}
		}
		selected = new boolean[chickens.size()];
		dfs(0, 0);

		System.out.println(result);
	}

	static void dfs(int idx, int count) {
		if(count == M) {
			result = Math.min(result, calc());
			return;
		}
		if(idx == chickens.size()) {
			return;
		}
		selected[idx] = true;
		dfs(idx + 1, count + 1);

		selected[idx] = false;
		dfs(idx + 1, count);
	}

	static int calc() {
		int sum = 0;
		for(int[] h : houses) {
			int dist = Integer.MAX_VALUE;
			for(int i = 0; i < chickens.size(); i++) {
				if(selected[i]) {
					int[] c = chickens.get(i);
					int d = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
					dist = Math.min(dist, d);
				}
			}
			sum += dist;
		}
		return sum;
	}
}