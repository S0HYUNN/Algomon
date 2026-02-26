import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		StringTokenizer st;
		int maxHeight = 0;
		int maxIdx = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
		for(int i = 1; i < N; i++) {
			if(maxHeight < arr[i][1]) {
				maxHeight = arr[i][1];
				maxIdx = i;
			}
		}
		int size = 0;

		int leftMax = arr[0][1];
		int prevX = arr[0][0];
		for(int i = 1; i <= maxIdx; i++) {
			size += leftMax * (arr[i][0] - prevX);
			leftMax = Math.max(leftMax, arr[i][1]);
			prevX = arr[i][0];
		}

		int rightMax = arr[N - 1][1];
		prevX = arr[N - 1][0];
		for(int i = N - 2; i >= maxIdx; i--) {
			size += rightMax * (prevX - arr[i][0]);
			rightMax = Math.max(rightMax, arr[i][1]);
			prevX = arr[i][0];
		}

		size += arr[maxIdx][1];
		System.out.println(size);
	}
}