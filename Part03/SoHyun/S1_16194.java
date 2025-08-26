import java.io.*;
import java.util.StringTokenizer;

public class S1_16194 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n+1];
        int[] dp = new int[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = arr[i];
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.min(dp[i], arr[j] + dp[i-j]);
            }

        }
        System.out.println(dp[n]);

    }
}

