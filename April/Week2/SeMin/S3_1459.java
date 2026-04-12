import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());
        long w = Long.parseLong(st.nextToken());  
        long s = Long.parseLong(st.nextToken()); 
		
		long min = Integer.MAX_VALUE;
		
		long time = (x+y) * w; 
		min = Math.min(time, min);
		
		if(2*w < s) {
			time = (x+y) * w;
		}
		else if(w>s) { 
			if((x+y) % 2 ==0) {
				time = Math.max(x, y)*s;
			}
			else {
				time = (Math.max(x, y)- 1) *s;
				time += w;
			}
		}
		else { 
			if(x == y) {
				time = s * x;
			}
			else {
				time = Math.min(x*s, y*s);
				time += Math.abs(x-y)*w;
			}
		}
		
		System.out.println(time);
	}
}