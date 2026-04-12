import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int len = st.countTokens();

            for (int j= 0; j < len; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int a = (j > 0) ? arr[i - 1][j - 1] : 0;
                int b = (j < i) ? arr[i - 1][j] : 0;

                arr[i][j] += Math.max(a, b);
            }   
        }
        
        int result = -1;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, arr[n - 1][i]);
        }
        
        System.out.println(result);        
    }
}