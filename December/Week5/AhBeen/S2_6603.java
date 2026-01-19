import java.util.*;
import java.io.*;

public class Main {
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	static int[] answer = new int[6];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());

			int k = Integer.parseInt(st.nextToken());
			if(k == 0) {
				break;
			}
			arr = new int[k];
			for(int i = 0; i < k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int s, int depth) {
		if (depth == 6) {
			for (int n : answer) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = s; i < arr.length; i++) {
			answer[depth] = arr[i];
			dfs(i + 1, depth + 1);
		}
	}
}