import java.io.*;
import java.util.StringTokenizer;

public class S3_2193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] answer = new long[n+1];

        answer[0] = 0;
        answer[1] = 1;

        for (int i = 2; i <= n; i++) {
            answer[i] = answer[i-1] + answer[i-2];
        }

        System.out.println(answer[n]);
    }
}

