import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 1;

		for(int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		int result = 1;
		int seat = 0;
        
		for(int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(br.readLine());
			result *= dp[tmp - seat - 1];
			seat = tmp;
		}

		result *= dp[N - seat];
		System.out.println(result);
	}
}