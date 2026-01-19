import java.util.*;
import java.io.*;

public class Main {
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		arr = new int[M];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int low = 0;
		int high = N;
		int result = 0;

		while(low <= high) {
			int mid = (low + high) / 2;

			if(isLight(mid, N)) {
				high = mid - 1;
				result = mid;
			} else {
				low = mid + 1;
			}
		}
		System.out.println(result);
	}

	static boolean isLight(int mid, int N) {
		int prev = 0;

		for(int i = 0; i < arr.length; i++) {
			if(arr[i] - mid <= prev) {
				prev = arr[i] + mid;
			} else {
				return false;
			}
		}

		return N - prev <= 0;
	}
}