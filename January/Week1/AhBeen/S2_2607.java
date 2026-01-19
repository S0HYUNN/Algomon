import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String first = br.readLine();
		int count = 0;

		for (int i = 0; i < N - 1; i++) {
			String word = br.readLine();

			int[] cnt = new int[26];

			for (char c : first.toCharArray()) {
				cnt[c - 'A']++;
			}

			for (char c : word.toCharArray()) {
				cnt[c - 'A']--;
			}

			int diff = 0;
			for (int v : cnt) {
				diff += Math.abs(v);
			}
			if (diff <= 2 && Math.abs(first.length() - word.length()) <= 1) {
				count++;
			}
		}

		System.out.println(count);
	}
}