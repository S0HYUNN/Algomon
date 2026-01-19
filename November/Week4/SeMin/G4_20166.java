import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static final int[] dx = {-1, 1, 0, 0, -1, 1, 1, -1};
    static final int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};
    static String[][] map;
    static Map<String, Integer> words;

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NMK = br.readLine().split(" ");
        N = Integer.parseInt(NMK[0]);
        M = Integer.parseInt(NMK[1]);
        int K = Integer.parseInt(NMK[2]);

        map = new String[N][M];
        
        for (int i = 0; i < N; i++ ) {
            String[] w = br.readLine().split("");
            for (int j = 0; j < M ; j++) {
                map[i][j] = w[j];
            }
        }

        int maxLen = 5;
        words = new HashMap<>();
        
        for(int r = 1; r <= maxLen; r++){
            for(int i = 0; i < N; i++){
                for (int j = 0; j < M; j++) {
                    dfs(i, j, r, 1, map[i][j]);
                }
            }
        }
        

        for (int i = 0; i < K ; i++ ) {
            String like = br.readLine();
            int num = words.getOrDefault(like, 0);
            System.out.println(num);
        }

        
    }

    static void dfs(int x, int y, int r, int depth, String str) {
        if(depth == r){
            words.put(str, words.getOrDefault(str, 0 ) + 1);
            return;
        }


        for(int i = 0; i < 8; i++){
            int nx = (x + dx[i] + N) % N;
            int ny = (y + dy[i] + M) % M;

            dfs(nx, ny, r, depth + 1, str + map[nx][ny]);
        }
    }
}