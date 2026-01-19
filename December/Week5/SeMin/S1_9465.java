import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[2][n + 1];
            int[][] arr = new int[2][n + 1];

            for (int i = 0 ; i < 2 ; i++) {
                String[] s = br.readLine().split(" "); 
                for (int j = 1; j <= n ; j++) {
                    arr[i][j] = Integer.parseInt(s[j - 1]);
                }
            }

            dp[0][1] = arr[0][1];
            dp[1][1] = arr[1][1];
            
            for (int i = 2; i <= n ; i++) {  
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + arr[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + arr[1][i];
            }
            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
        
    }
}