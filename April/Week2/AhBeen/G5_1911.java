import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		Long[][] arr = new Long[N][2];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Long.parseLong(st.nextToken());
			arr[i][1] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr, (a1, a2) -> a1[0].compareTo(a2[0]));

		long count = 0;
		long idx = 0;
		for(int i = 0; i < N; i++) {
			long start = Math.max(arr[i][0], idx);
			long end = arr[i][1];

			if(end <= idx) {
				continue;
			}
			long len = end - start;
			long need = (len + L - 1) / L;

			count += need;
			idx = start + L * need;
		}

		System.out.println(count);
	}
}