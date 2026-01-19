import java.util.*;
import java.io.*;

class Main {
    static class Node {
        int start, end, cost;
        Node(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    
    static List<Node> graph = new ArrayList<>();
    static int N, M;
    static int[] parent;

    static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if (x != y) parent[y] = x;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);

        parent = new int[N + 1];

        for (int i = 1; i <= N ; i++ ) parent[i] = i;


        for (int i = 0; i < M ; i++ ) {
            String[] abc = br.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            int c = Integer.parseInt(abc[2]);
            graph.add(new Node(a, b, c));
        }

        Collections.sort(graph, (a, b) -> a.cost - b.cost);

        int res = 0;
        int cost = 0;
        
        for (Node node : graph) {
            if (find(node.start) != find(node.end)) {
                union(node.start, node.end);
                res += node.cost;
                cost = node.cost;
            }
        }

        System.out.println(res - cost);
    }
}