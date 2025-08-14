import java.util.*;
import java.io.*;

class S3_1929 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] st = br.readLine().split(" ");

        int N = Integer.parseInt(st[0]);
        int M = Integer.parseInt(st[1]);

        boolean[] isPrime = new boolean[M + 1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;

        for (int i = 2; i <= Math.sqrt(M); i++) {
            if(!isPrime[i]) continue;
            for (int j = 2; i * j <= M; j++) {
                isPrime[i * j] = false;
            }
        }

        for (int i = N; i <= M; i++) {
            if (isPrime[i]) {
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}


