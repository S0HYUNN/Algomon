import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nmkx = br.readLine().split(" ");

        int N = Integer.parseInt(nmkx[0]);
        int M = Integer.parseInt(nmkx[1]);
        int K = Integer.parseInt(nmkx[2]);
        int X = Integer.parseInt(nmkx[3]);

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] uv = br.readLine().split(" ");
            int u = Integer.parseInt(uv[0]);
            int v = Integer.parseInt(uv[1]);
            graph.get(u).add(v);
        }

        int[] distance = new int[N + 1];
        Arrays.fill(distance, -1);
        distance[X] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph.get(current)) {
                if (distance[next] == -1) { 
                    distance[next] = distance[current] + 1; 
                    queue.add(next);
                }
            }
        }

        boolean found = false;
        for (int i = 1; i <= N; i++) {
            if (distance[i] == K) { 
                System.out.println(i);
                found = true;
            }
        }

        if (!found)  System.out.println(-1);

    }
}