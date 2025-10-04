import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;
        for (int i = 0; i < N - 2; i++) {
            int more = 0;
            for (int k = i + 1; k < N; k++) {
                if (nums[i] < nums[k])  more++;
                else answer += more;
            }
        }

        System.out.println(answer);
    }
}