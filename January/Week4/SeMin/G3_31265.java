import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] count = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			count[i] = Integer.parseInt(st.nextToken());
		}

		boolean[] prev = new boolean[M + 1];
		prev[0] = true;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int[] times = new int[count[i]];
			for (int j = 0; j < count[i]; j++) {
				times[j] = Integer.parseInt(st.nextToken());
			}

			boolean[] group = new boolean[M + 1];
			for (int t : times) {
				for (int m = M; m >= t; m--) {
					if (group[m - t] || m == t) {
						group[m] = true;
					}
				}
			}

			boolean[] curr = new boolean[M + 1];
			for (int a = 0; a <= M; a++) {
				if (!prev[a]) continue;
				for (int b = 1; b + a <= M; b++) {
					if (group[b]) curr[a + b] = true;
				}
			}
			prev = curr;
		}

		for (int i = M; i >= 0; i--) {
			if (prev[i]) {
				System.out.println(i);
				return;
			}
		}

		System.out.println(-1);
	}
}
