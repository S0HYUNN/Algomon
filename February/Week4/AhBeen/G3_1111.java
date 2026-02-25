import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		if(N == 1) {
			bw.write("A");
			bw.flush();
			bw.close();
			return;
		}

		if(N == 2) {
			if(arr[0] == arr[1]) bw.write(String.valueOf(arr[0]));
			else bw.write("A");
			bw.flush();
			bw.close();
			return;
		}

		long a, b;

		if (arr[0] == arr[1]) {
			for (int i = 1; i < N; i++) {
				if (arr[i] != arr[0]) {
					bw.write("B");
					bw.flush();
					bw.close();
					return;
				}
			}
			bw.write(String.valueOf(arr[0]));
			bw.flush();
			bw.close();
			return;
		} else {
			long num = arr[2] - arr[1];
			long den = arr[1] - arr[0];

			if (num % den != 0) {
				bw.write("B");
				bw.flush();
				bw.close();
				return;
			}
			a = num / den;
			b = arr[1] - a * arr[0];
		}

		for (int i = 0; i < N - 1; i++) {
			if(arr[i + 1] != a * arr[i] + b) {
				bw.write("B");
				bw.flush();
				bw.close();
				return;
			}
		}

		bw.write(String.valueOf(a * arr[N - 1] + b));
		bw.flush();
		bw.close();
	}
}