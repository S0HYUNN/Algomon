import java.util.*;
import java.io.*;

class Main {
    static int[][] arr;
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][3];
        StringTokenizer st;
        
        for (int i = 0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
        

        for (int i = 1; i <= N; i++) {
            arr[i][0] += Math.min(arr[i - 1][1], arr[i - 1][2]);
            arr[i][1] += Math.min(arr[i - 1][0], arr[i - 1][2]);
            arr[i][2] += Math.min(arr[i - 1][1], arr[i - 1][0]);
        }

        
        int result = Math.min(arr[N][0], Math.min(arr[N][1], arr[N][2]));
        System.out.println(result);
    }
}