import java.io.*;
import java.util.*;
 
public class Main {
    static int N, M;                  
    static int[] dx = {-1, 0, 1, 0};  
    static int[] dy = {0, -1, 0, 1};   
    static int[][] area;               
    static boolean[][] visited;       
    static int max;  
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); 
        M = Integer.parseInt(st.nextToken());   
        int K = Integer.parseInt(st.nextToken()); 
        area = new int[N + 1][M + 1];        
        visited = new boolean[N + 1][M + 1];  
        
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()); 
            int c = Integer.parseInt(st.nextToken());
            area[r][c] = 1;
        }
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (!visited[i][j] && area[i][j] != 0) {
                    bfs(i, j);
                }
            }
        }
        
        System.out.println(max); 
    }
    
    public static void bfs(int a, int b) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {a, b});
        visited[a][b] = true;
        int count = 2;                 
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int x = now[0] + dx[i];
                int y = now[1] + dy[i];
                
                if (x < 1 || y < 1 || x > N || y > M) continue;
                if (visited[x][y] || area[x][y] == 0) continue;

                
                q.add(new int[] {x, y});  
                visited[x][y] = true;    
                area[x][y] = count++;
                a = x;    
                b = y;        
            }
        }
        
        max = Math.max(max, area[a][b]);
    }
}