import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		sb.append((int) Math.pow(2, N) - 1).append("\n");
		hanoi(N, 1, 3, 2);

		System.out.print(sb);
	}

	static void hanoi(int n, int from, int to, int via) {
		if(n == 1) {
			sb.append(from).append(" ").append(to).append("\n");
			return;
		}

		hanoi(n - 1, from, via, to);

		sb.append(from).append(" ").append(to).append("\n");

		hanoi(n - 1, via, to, from);
	}
}