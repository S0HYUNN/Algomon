import java.util.*;
import java.io.*;

class Main {
    static List<Integer>[] graph;
    static int n, m;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        int answer = 0;
        
        for (int i = 1; i <= n ; i++ ) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m ; i++ ) {
            String[] uv = br.readLine().split(" ");
            int u = Integer.parseInt(uv[0]);
            int v = Integer.parseInt(uv[1]);
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <= n ; i++ ) {
            if(!visited[i]){
                search(i);
                answer++;
            }
        }
        System.out.println(answer);
    }

    static void search(int num){
       visited[num] = true;
        for (Integer node : graph[num]) {
            if(!visited[node]){
                search(node);
            }
        }
    }
}