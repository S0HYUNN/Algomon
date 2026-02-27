import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;

		for (int t = 0; t < 2; t++) {

			int[] down = new int[N];
			int[] up = new int[N];
			Arrays.fill(down, 1);
			Arrays.fill(up, 1);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < i; j++) {
					if (arr[j] > arr[i]) {
						down[i] = Math.max(down[i], down[j] + 1);
					}
				}
			}

			for (int i = N - 1; i >= 0; i--) {
				for (int j = N - 1; j > i; j--) {
					if (arr[j] > arr[i]) {
						up[i] = Math.max(up[i], up[j] + 1);
					}
				}
			}

			for (int i = 0; i < N; i++) {
				answer = Math.max(answer, down[i] + up[i] - 1);
			}

			for (int i = 0; i < N / 2; i++) {
				int tmp = arr[i];
				arr[i] = arr[N - 1 - i];
				arr[N - 1 - i] = tmp;
			}
		}

		System.out.println(answer);
	}
}