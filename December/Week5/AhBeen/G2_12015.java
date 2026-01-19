import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		int[] LIS = new int[N];

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		LIS[0] = arr[0];
		int length = 1;
		for(int i = 1; i < N; i++) {
			int key = arr[i];
			if(LIS[length - 1] < key) {
				length++;
				LIS[length - 1] = key;
			} else {
				// int low = lowerBound(LIS, length, key);
				// LIS[low] = key;
				int low = Arrays.binarySearch(LIS, 0, length, key);
				if(low < 0) low = -(low + 1);
				LIS[low] = key;
			}
		}
		System.out.println(length);
	}

	// static int lowerBound(int[] LIS, int length, int key) {
	// 	int low = 0;
	// 	int high = length;
	// 	while(low < high) {
	// 		int mid = (low + high) / 2;
	// 		if(LIS[mid] < key) low = mid + 1;
	// 		else high = mid;
	// 	}
	// 	return low;
	// }
}