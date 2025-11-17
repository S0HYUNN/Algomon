import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int n, m, cnt;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b].add(a);
        }

        int[] cnt = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int hacked = 1;
            Queue<Integer> q = new ArrayDeque<>();
            boolean[] visited = new boolean[n + 1];
            q.add(i);
            visited[i] = true;

            while (!q.isEmpty()) {
                int now = q.poll();

                for (int next : graph[now]) {
                    if (visited[next]) continue;
                    q.add(next);
                    visited[next] = true;
                    hacked++;
                }
            }
            cnt[i] = hacked;
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, cnt[i]);
        }

        for (int i = 1; i <= n; i++) {
            if (cnt[i] == max) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb.toString());
    }
}
