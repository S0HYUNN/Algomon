import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long T = Long.parseLong(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int m = Integer.parseInt(br.readLine());
		int[] B = new int[m];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		Map<Long, Integer> map = new HashMap<>();

		for(int i = 0; i < n; i++) {
			long sum = 0;
			for(int j = i; j < n; j++) {
				sum += A[j];
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			}
		}

		long answer = 0;

		for(int i = 0; i < m; i++) {
			long sum = 0;
			for(int j = i; j < m; j++) {
				sum += B[j];
				long target = T - sum;
				answer += map.getOrDefault(target, 0);
			}
		}

		System.out.println(answer);
	}
}