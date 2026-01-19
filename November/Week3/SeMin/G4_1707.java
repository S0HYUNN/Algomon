import java.util.*;
import java.io.*;

class Main {
    static List<Integer>[] graph;
    static int[] colors; 
    static final int RED = 1;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < K ; tc++ ) {
            String[] ve = br.readLine().split(" ");
            int V = Integer.parseInt(ve[0]);
            int E = Integer.parseInt(ve[1]);

            graph = new ArrayList[V + 1];
            colors = new int[V + 1];
            for (int i = 1; i <= V ; i++ ) {
                graph[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < E ; i++ ) {
                String[] uv = br.readLine().split(" ");
                int u = Integer.parseInt(uv[0]);
                int v = Integer.parseInt(uv[1]);
                graph[u].add(v);
                graph[v].add(u);
            }

            boolean rst = false;
            for (int i = 1; i <= V ; i++ ) {
                if(colors[i] == 0){
                    rst = isBipartiteGraph(i, RED);
                }
                if(!rst) break;
            }
            if(rst) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static boolean isBipartiteGraph(int start, int color){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        colors[start] = color;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph[cur]) {
                if(colors[cur] == colors[next]) return false;
                if(colors[next] == 0){
                    colors[next] = colors[cur] * - 1;
                    q.offer(next);
                }
            }
        }
        return true;
    }
}