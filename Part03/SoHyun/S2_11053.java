import java.io.*;
import java.util.StringTokenizer;

public class S2_11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] arr = new int[n];
        int[] dp = new int[n];

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i])
                    dp[i] = dp[j] + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            cnt = Math.max(cnt, dp[i]);
        }

        System.out.println(cnt);

    }
}

