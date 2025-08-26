import java.io.*;
import java.util.*;

class S1_15990 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[][] dp = new long[100001][4];

        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= 100000; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % 1000000009;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % 1000000009;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % 1000000009;
        }

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            long res = (dp[n][1] + dp[n][2] + dp[n][3]) % 1000000009;
            System.out.println(res);
        }

    }
}