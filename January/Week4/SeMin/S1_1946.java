import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[a - 1] = b;
			}
			int count = 1;
			int rank = arr[0];
			for(int i = 1; i < N; i++) {
				if(rank > arr[i]) {
					count++;
					rank = arr[i];
				}
			}
			sb.append(count + "\n");
		}
		System.out.println(sb);
	}
}
