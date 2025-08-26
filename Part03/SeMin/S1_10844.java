import java.io.*;
import java.util.*;

class S1_10844 {
    final static long MOD = 1000000000;
    static Long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new Long[n+1][10];

        for(int i = 0; i < 10; i++){
            dp[1][i] = 1L;
        }

        long result = 0;

        for(int i = 1; i <= 9; i++) {
            result += recur(n, i);
        }
        System.out.println(result % MOD);

    }

    static long recur(int a, int b){

        if(a == 1){
            return dp[a][b];
        }

        if(dp[a][b] == null) {
            if (b == 0) {
                dp[a][b] = recur(a - 1, 1);
            } else if (b == 9) {
                dp[a][b] = recur(a - 1, 8);
            } else {
                dp[a][b] = recur(a - 1, b - 1) + recur(a - 1, b + 1);
            }

        }
        return dp[a][b] % MOD;
    }
}