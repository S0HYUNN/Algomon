import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] dp = new int[41];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
        
		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
        int result = 1;

		int beforeSeat = 0;
		for (int i = 0; i < M; i++) {
			int temp = Integer.parseInt(br.readLine());
			result *= dp[temp - beforeSeat - 1];
			beforeSeat = temp;
		}
		result *= dp[N - beforeSeat];
        System.out.println(result);
    }
}