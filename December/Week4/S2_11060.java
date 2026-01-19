import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N + 1];
		long[] dp = new long[N + 1];
		Arrays.fill(dp, Long.MAX_VALUE);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dp[1] = 0;

		for (int i = 1; i <= N; i++) {
			if (dp[i] == Long.MAX_VALUE) continue;

			for (int j = 1; j <= arr[i]; j++) {
				if (i + j > N) break;
				dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
			}
		}

		System.out.println(dp[N] == Long.MAX_VALUE ? -1 : dp[N]);
	}
}