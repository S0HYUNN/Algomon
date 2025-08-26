import java.io.*;
import java.util.StringTokenizer;

public class S1_10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n+1][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][1] % 1000000000;
                } else if (j == 9) {
                    dp[i][j] = dp[i-1][8] % 1000000000;
                } else {
                    dp[i][j] = (dp[i-1][j-1] % 1000000000 + dp[i-1][j+1] % 1000000000) % 1000000000;
                }
            }
        }

        int sum = 0;
        for (int i = 1; i <= 9; i++) {
            sum += dp[n][i];

            sum %= 1000000000;
        }

        System.out.println(sum % 1000000000);

    }
}

