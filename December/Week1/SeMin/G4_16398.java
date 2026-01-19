import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int start, end, cost;
        Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static int n;
    static long res;
    static int[] parent;
    static List<Node> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = i + 1; j < n; j++) {
                int cost = Integer.parseInt(input[j]);
                graph.add(new Node(i, j, cost));
            }
        }
        
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Collections.sort(graph, (a, b) -> a.cost - b.cost);

        for (Node node : graph) {
            if (find(node.start) != find(node.end)) {
                union(node.start, node.end);
                res += node.cost;
            }
        }

        System.out.println(res);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) parent[y] = x;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}