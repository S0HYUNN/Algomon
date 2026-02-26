import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[100];


		int[] L = new int[N];
		int[] J = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			J[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 0; i < N; i++) {
			int cost = L[i];
			int joy = J[i];

			for(int used = 99; used >= cost; used--) {
				dp[used] = Math.max(dp[used], dp[used - cost] + joy);
			}
		}

		bw.write(String.valueOf(dp[99]));
		bw.flush();
		bw.close();
	}
}