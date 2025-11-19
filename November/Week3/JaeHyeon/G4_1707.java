import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int k;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        for (int t = 0; t < k; t++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            @SuppressWarnings("unchecked")
            List<Integer>[] graph = new ArrayList[v + 1];
            int[] color = new int[v + 1];
            for (int i = 0; i <= v; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int src = Integer.parseInt(st.nextToken());
                int dst = Integer.parseInt(st.nextToken());
                graph[src].add(dst);
                graph[dst].add(src);
            }

            for (int i = 1; i <= v; i++) {
                dfs(graph, color, i, 0);
            }

            boolean isBinary = true;
            for (int i = 1; i <= v; i++) {
                for (int next : graph[i]) {
                    if (color[i] == color[next]) {
                        isBinary = false;
                        break;
                    }
                }
            }
            System.out.println(isBinary ? "YES" : "NO");
        }
    }

    static int RED = 1;
    static int BLUE = 2;

    static void dfs(List<Integer>[] graph, int[] color, int nodeNum, int depth) {
        if (color[nodeNum] != 0) {
            return;
        }
        if (depth % 2 == 0) {
            color[nodeNum] = RED;
        } else {
            color[nodeNum] = BLUE;
        }

        for (int next : graph[nodeNum]) {
            dfs(graph, color, next, depth + 1);
        }
    }
}
