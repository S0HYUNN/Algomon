import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();

		int N;
		int length = str.length();
		if(str.length() < 10) {
			N = str.length();
		} else {
			N = 9 + (str.length() - 9) / 2;
		}

		boolean[] visited = new boolean[N + 1];

		dfs(str, 0, length, N, visited, new StringBuilder());
	}

	static void dfs(String str, int depth , int length, int N, boolean[] visited, StringBuilder sb) {
		if(depth == length) {
			System.out.println(sb.toString());
			System.exit(0);
		}

		int num = str.charAt(depth) - '0';

		if(num >= 1 && num <= N && !visited[num]) {
			visited[num] = true;

			int preLength = sb.length();
			sb.append(num).append(" ");
			dfs(str, depth + 1, length, N, visited, sb);

			sb.setLength(preLength);
			visited[num] = false;
		}

		if(depth + 1 < length) {
			if(str.charAt(depth) != '0') {
				num = Integer.parseInt(str.substring(depth, depth + 2));

				if(num >= 1 && num <= N && !visited[num]) {
					visited[num] = true;

					int preLength = sb.length();
					sb.append(num).append(" ");
					dfs(str, depth + 2, length, N, visited, sb);

					sb.setLength(preLength);
					visited[num] = false;
				}
			}
		}
	}
}