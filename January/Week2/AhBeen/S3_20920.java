import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Map<String, Integer> map = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			if (word.length() < M) continue;
			map.put(word, map.getOrDefault(word, 0) + 1);
		}

		List<String> words = new ArrayList<>(map.keySet());

		Collections.sort(words, (a, b) -> {
			if (!map.get(a).equals(map.get(b)))
				return map.get(b) - map.get(a);
			if (a.length() != b.length())
				return b.length() - a.length();
			return a.compareTo(b);
		});

		StringBuilder sb = new StringBuilder();
		for (String w : words) {
			sb.append(w).append('\n');
		}

		System.out.print(sb);
	}
}