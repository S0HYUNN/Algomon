import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3_1929 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

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