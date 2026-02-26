import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int left = 0, right = -1;
		int[] arr = new int[n];

		String[] s = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(s[i]);
			right = Math.max(right, arr[i]);
		}
        
		int m = Integer.parseInt(br.readLine());
		while(left <= right) {
			int mid = (left + right)/2;
			long budget =0;
			for(int i = 0; i < n; i++) {
				if(arr[i] > mid) budget += mid;
				else budget += arr[i];
			}
			if(budget <= m) {
				left = mid+1;
			}else {
				right = mid-1;
			}
		}
		System.out.println(right);
	}
}