import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String word = br.readLine();
        
        int[] base = new int[26];
        for (char c : word.toCharArray()) {
            base[c - 'A']++;
        }

        int answer = 0;

        for (int i = 0; i < n - 1; i++) {
            String w = br.readLine();

            int[] cnt = new int[26];
            for (char c : w.toCharArray()) {
                cnt[c - 'A']++;
            }

            int diff = 0;
            for (int j = 0; j < 26; j++) {
                diff += Math.abs(base[j] - cnt[j]);
            }

            if (diff <= 2 && Math.abs(word.length() - w.length()) <= 1) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
