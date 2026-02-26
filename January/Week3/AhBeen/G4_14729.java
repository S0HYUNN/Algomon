import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[] arr = new int[W];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] leftMax = new int[W];
		int[] rightMax = new int[W];

		leftMax[0] = arr[0];
		for (int i = 1; i < W; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
		}

		rightMax[W - 1] = arr[W - 1];
		for (int i = W - 2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
		}

		int answer = 0;
		for(int i = 1; i < W - 1; i++) {
			answer += Math.max(0, Math.min(leftMax[i], rightMax[i]) - arr[i]);
		}

		System.out.println(answer);
	}
}