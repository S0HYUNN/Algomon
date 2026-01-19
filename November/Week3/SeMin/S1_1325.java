import java.util.*;
import java.io.*;

class Main {
    static List<Integer>[] graph;
    static int N, M;
    static int[] count; 
    static boolean[] visited;

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);

        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N ; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M ; i++) {
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            graph[b].add(a);
        }

        count = new int[N + 1];

        for (int i = 1; i <= N ; i++ ) {
            visited = new boolean[N + 1];
            dfs(i, i);
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, count[i]);
        }

        for (int i = 1; i <= N; i++) {
            if (count[i] == max) System.out.print(i + " ");
        }
    }

    static void dfs(int start, int cur) {
        visited[cur] = true;

        for (int next : graph[cur]) {
            if (!visited[next]) {
                count[start]++;    // next는 cur을 통해 해킹됨 → score 증가
                dfs(start, next);
            }
        }
    }
}