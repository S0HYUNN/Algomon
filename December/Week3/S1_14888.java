import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] num;
	static int[] op = new int[4];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}

		dfs(1, num[0]);
		System.out.println(max);
		System.out.println(min);
	}

	static void dfs(int idx, int v) {
		if(idx == N) {
			max = Math.max(max, v);
			min = Math.min(min, v);
			return;
		}

		for(int i = 0; i < 4; i++) {
			if(op[i] > 0) {
				op[i]--;

				int next = v;
				if(i == 0) {
					next = v + num[idx];
				} else if (i == 1) {
					next = v - num[idx];
				} else if (i == 2) {
					next = v * num[idx];
				} else {
					next = v / num[idx];
				}
				dfs(idx + 1, next);
				op[i]++;
			}
		}
	}
}