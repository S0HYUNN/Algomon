import java.util.*;
import java.io.*;

public class B2_1978 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            boolean is_prime = true;

            if (arr[i] == 1)
                continue;

            for (int j = 2; j < arr[i]; j++) {
                if (arr[i] % j == 0) {
                    is_prime = false;
                }
            }
            if (is_prime) {
                count++;
            }
        }
        System.out.println(count);
    }
}

