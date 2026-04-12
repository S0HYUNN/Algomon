import java.io.*;
import java.util.*;
 
public class Main {
 
    public static int answer = 0;
    public static int N,L;
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
		
        int[] waters = new int[2 * N];
 
        for (int i=0; i<2*N; i+=2) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            waters[i] = start;
            waters[i+1] = end;
        }
		
        Arrays.sort(waters);
		
        int lastIdx = 0;
 
        for (int i = 0; i< 2 * N; i += 2) {

            int start = waters[i];
            
            if (lastIdx >= start) {
                start = lastIdx + 1;
            }

            int end = waters[i+1];
            int length = end - start;
            
            if (length <= 0) continue;

            int cnt = length/L;

            int res = length%L;
 
            if (res != 0) {
                cnt++;
                lastIdx = waters[i+1]-1 + (L - res);
            }

            answer += cnt;
        }
 
        System.out.println(answer);
    }
}