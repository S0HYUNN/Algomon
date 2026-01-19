import java.io.*;
import java.util.*;
 
public class Main {	
    static int N;
    static int[] streetlamp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        N = Integer.parseInt(br.readLine()); 
        int M = Integer.parseInt(br.readLine());
		
        streetlamp = new int[M];
		
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            streetlamp[i] = Integer.parseInt(s[i]);
        }
		
        int left = 1; 
        int right = N;
        int ans = 0;
		
        while (left <= right) {
            int mid = (left + right) / 2;
			
            if (canLight(mid)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
		
        System.out.println(ans);
    }
	
    public static boolean canLight(int h) {
        int prev = 0;
		
        for (int i = 0; i < streetlamp.length; i++) {
            if (streetlamp[i] - h <= prev) {
                prev = streetlamp[i] + h;
            } else {
                return false;
            }
        }
        return N - prev <= 0;
    }
}