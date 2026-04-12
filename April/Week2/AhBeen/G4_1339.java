import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[26];
		Arrays.fill(arr, 0);
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < str.length(); j++) {
				arr[str.charAt(j) - 'A'] += (int)Math.pow(10, str.length() - 1 - j);
			}
		}

		int answer = 0;
		int num = 9;
		Arrays.sort(arr, (a, b) -> b - a);
		for(int a : arr) {
			if(a == 0) break;
			answer += a * num;
			num--;
		}

		System.out.println(answer);
	}
}