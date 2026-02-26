import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		arr = new int[N];
		long low = 0, high = -1;
		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
			high = Math.max(high, arr[i]);
		}

		int M = Integer.parseInt(br.readLine());
		while(low <= high) {
			long mid = (low + high) / 2;
			long budget = 0;

			for (int j : arr) {
				budget += Math.min(j, mid);
			}
			if(budget <= M) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		System.out.println(high);
	}
}