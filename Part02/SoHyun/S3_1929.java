import java.util.*;
import java.io.*;

public class S3_1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        boolean[] is_prime = new boolean[n+1];

        for (int i = 2; i <= n; i++) {
            if (is_prime[i])
                continue;

            if (i >= m)
                sb.append(i).append("\n");

            for (int j = i*2; j <= n; j += i) {
                is_prime[j] = true;
            }
        }

        System.out.println(sb);
    }
}
