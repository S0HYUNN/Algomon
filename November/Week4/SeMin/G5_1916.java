import java.util.*;
import java.io.*;

class Main {
    static List<List<Node>> graph = new ArrayList<>();
    static int N, M;

    static class Node{
        int to, cost;
        Node(int t, int c) {
            this.to = t; 
            this.cost = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] abc = br.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            int c = Integer.parseInt(abc[2]);
            graph.get(a).add(new Node(b, c));
        }

        String[] se = br.readLine().split(" ");
        int start = Integer.parseInt(se[0]);
        int end = Integer.parseInt(se[1]);

        System.out.println(dijkstra(start, end));
    }

    static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>((Node n1 , Node n2 )-> n1.cost - n2.cost);
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.to] < now.cost) continue;

            for (Node next : graph.get(now.to)) {

                int newCost = now.cost + next.cost;

                if (dist[next.to] > newCost) {
                    dist[next.to] = newCost;
                    pq.offer(new Node(next.to, newCost));
                }
            }
        }

        return dist[end];
    }
}
