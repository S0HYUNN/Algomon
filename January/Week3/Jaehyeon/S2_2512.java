import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        int max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(arr[i], max);
        }
        int M = Integer.parseInt(br.readLine());

        int lo = 0;
        int hi = max;
        int tmp = 0;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int sum = 0;

            for (int i = 0; i < N; i++) {
                if (arr[i] <= mid) {
                    sum += arr[i];
                } else {
                    sum += mid;
                }
            }

            if (sum <= M) {
                tmp = mid;
                lo = mid + 1;
            } else {
                hi = mid -1;
            }
        }

        System.out.println(tmp);
    }
}

