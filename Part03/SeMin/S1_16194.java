import java.io.*;
import java.util.*;

class S1_16194 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] price = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int j = 1; j <= n; j++) {
            price[j] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int best = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                best = Math.min(best, dp[i - j] + price[j]);
            }
            dp[i] = best;
        }

        System.out.println(dp[n]);
    }
}