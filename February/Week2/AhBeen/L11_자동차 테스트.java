import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(arr[i], i);
		}


		for (int i = 0; i < q; i++) {
			int target = Integer.parseInt(br.readLine());
			Integer idx = map.get(target);

			if (idx == null) {
				sb.append(0).append("\n");
			} else {
				sb.append(idx * (n - 1 - idx)).append("\n");
			}
		}

		System.out.println(sb.toString());
	}
}