import java.io.*;
import java.util.*;

public class Main {
   static int[] bucket = new int[3];
   static boolean[][] visited = new boolean[201][201];
   static boolean[] possible = new boolean[201];
   static Queue<int[]> q = new ArrayDeque<>();

   public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      bucket[0] = Integer.parseInt(st.nextToken());
      bucket[1] = Integer.parseInt(st.nextToken());
      bucket[2] = Integer.parseInt(st.nextToken());

      bfs();
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i <= bucket[2]; i++) {
         if (possible[i]) {
            sb.append(i).append(" ");
         }
      }
      System.out.println(sb);
   }

   static void bfs() {
      q.add(new int[]{0, 0});
      visited[0][0] = true;

      while (!q.isEmpty()) {
         int[] cur = q.poll();
         int a = cur[0];
         int b = cur[1];
         int c = bucket[2] - a - b;

         if (a == 0) {
            possible[c] = true;
         }

         pour(a, b, c, 0, 1);
         pour(a, b, c, 0, 2);
         pour(a, b, c, 1, 0);
         pour(a, b, c, 1, 2);
         pour(a, b, c, 2, 0);
         pour(a, b, c, 2, 1);
      }
   }

   static void pour(int a, int b, int c, int from, int to) {
      int[] water = {a, b, c};
      
      int move = Math.min(water[from], bucket[to] - water[to]);

      water[from] -= move;
      water[to] += move;

      int na = water[0];
      int nb = water[1];

      if (!visited[na][nb]) {
         visited[na][nb] = true;
         q.add(new int[]{na, nb});
      }
   }
}