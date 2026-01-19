import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String str;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        str = br.readLine();
        int[][] map = new int[101][101];
        
        int x = 50;
        int y = 50;
        int maxX = 50;
        int maxY = 50;
        int minX = 50;
        int minY = 50;
        map[x][y] = 1;

        int dir = 2;
        for (int i = 0; i < N; i++) {
            if (str.charAt(i) == 'L') {
                dir -= 1;
                if (dir == -1) dir = 3;
            } else if (str.charAt(i) == 'R') {
                dir = (dir + 1) % 4;
            } else if (str.charAt(i) == 'F') {
                x = x + dx[dir];
                y = y + dy[dir];
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                map[x][y] = 1;
            }

        }
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                if (map[i][j] == 1) {
                    sb.append(".");
                } else {
                    sb.append("#");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}