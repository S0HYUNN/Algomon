import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] info = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			info[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> line = new LinkedList<>();

		for (int i = N; i >= 1; i--) {
			line.add(info[i - 1], i);
		}

		StringBuilder sb = new StringBuilder();
		for (int v : line) {
			sb.append(v).append(" ");
		}
		System.out.println(sb);
	}
}
