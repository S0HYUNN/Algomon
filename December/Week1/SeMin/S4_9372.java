import java.util.*;
import java.io.*;


class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
  
        for (int tc = 0; tc < T; tc++ ) {
            List<List<Integer>> graph = new ArrayList<>();
            
            String[] nm = br.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            boolean[] visited = new boolean[n + 1];
            for (int i = 0; i <= n ; i++ ) {
                graph.add(new ArrayList<>());
            }

            
            for (int i = 0; i < m; i++ ) {
                String[] ab = br.readLine().split(" ");
                int a = Integer.parseInt(ab[0]);
                int b = Integer.parseInt(ab[1]);
                
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            int result = 0;
            
            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            visited[1] = true;

            while (!q.isEmpty()) {
                int now = q.poll();

                for (int next : graph.get(now)) {
                    if(!visited[next]){
                        result++;
                        q.add(next);
                        visited[next] = true;
                    }
                    
                }
            }
            System.out.println(result);
        }
    }
}