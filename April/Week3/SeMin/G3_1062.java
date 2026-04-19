import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int k;
    static String[] words;
    static boolean[] alphaArr = new boolean[26];
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        alphaArr['a' - 'a'] = true;
        alphaArr['n' - 'a'] = true;
        alphaArr['t' - 'a'] = true;
        alphaArr['i' - 'a'] = true;
        alphaArr['c' - 'a'] = true;

        if (k < 5) {
            System.out.println(max);
        } else {
            dfs(0, 0);
            System.out.println(max);
        }
    }

    public static void dfs(int idx, int cnt) {
        if (cnt + 5 == k) {
            countWord();
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (alphaArr[i]) {
                continue;
            }

            alphaArr[i] = true;
            dfs(i + 1, cnt + 1);
            alphaArr[i] = false;
        }
    }

    public static void countWord() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char[] alphas = words[i].toCharArray();
            boolean flag = true;
            for (char c: alphas) {
                if (!alphaArr[c - 'a']) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                cnt++;
            }
        }
        max = Math.max(max, cnt);
    }
}