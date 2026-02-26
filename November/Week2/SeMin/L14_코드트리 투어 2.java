import java.util.*;
import java.io.*;

public class Main {
    static final int INF = Integer.MAX_VALUE; 
    static final int MAX_N = 2000; 
    static final int MAX_ID = 30005;

    static int N, M;
    static int[][] A; 
    static int[] D; // 최단 경로
    static boolean[] isMade;
    static boolean[] isCancel; 
    static int S; // 여행 상품의 현재 출발지 도시 번호
    
    
    static class Trip{
        int id;
        int revenue;
        int dest;
        long profit; // 순수익 (매출 - 최단 거리 비용)

        public Trip(int id, int revenue, int dest, long profit){
            this.id = id;
            this.revenue = revenue;
            this.dest = dest;
            this.profit = profit;
        }
    }
    
    static Queue<Trip> pq;

    static void dijkstra() {
        D = new int[N];
        Arrays.fill(D, INF);
        boolean[] visit = new boolean[N];
        D[S] = 0; 

        for (int i = 0; i < N; i++) {
            int v = -1;
            int minDist = INF;
            for (int j = 0; j < N; j++) {
                if (!visit[j] && minDist > D[j]) {
                    v = j;
                    minDist = D[j];
                }
            }
            if (v == -1) break;

            visit[v] = true;

            for (int j = 0; j < N; j++) {
                if (A[v][j] != INF && D[v] != INF && D[j] > D[v] + A[v][j]) {
                    D[j] = D[v] + A[v][j];
                }
            }
        }
    }

    static void buildLand(int n_param, int m_param, int[] arr) {
        N = n_param;
        M = m_param;
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(A[i], INF);
            A[i][i] = 0; 
        }


        for (int i = 0; i < M; i++) {
            int u = arr[i * 3];
            int v = arr[i * 3 + 1];
            int w = arr[i * 3 + 2];
            A[u][v] = Math.min(A[u][v], w);
            A[v][u] = Math.min(A[v][u], w);
        }
    }


    static void addPackage(int id, int revenue, int dest) {
        isMade[id] = true;
        long profit = (D[dest] == INF) ? Long.MIN_VALUE : (long)revenue - D[dest];
        pq.offer(new Trip(id, revenue, dest, profit));
    }

    static void cancelPackage(int id) {
        if (isMade[id]) isCancel[id] = true;
    }

     static int sellPackage() {
        while (!pq.isEmpty()) {
            Trip p = pq.peek();
            if (p.profit < 0) break;
            pq.poll();

            if (!isCancel[p.id]) {
                isCancel[p.id] = true;
                return p.id;
            }
        }
        return -1;
    }

    static void changeStart(int param) {
        S = param;
        dijkstra();

        ArrayList<Trip> tempPackages = new ArrayList<>();

        while (!pq.isEmpty()) {
            tempPackages.add(pq.poll());
        }
        for (Trip p : tempPackages) {
            p.profit = (D[p.dest] == INF) ? Long.MIN_VALUE : (long)p.revenue - D[p.dest];
            pq.offer(p);
        }
    }

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int Q = Integer.parseInt(br.readLine());

        isMade = new boolean[MAX_ID];
        isCancel = new boolean[MAX_ID];
        pq = new PriorityQueue<Trip>((Trip t1, Trip t2) ->{
            int cmp = Long.compare(t2.profit, t1.profit);
            if (cmp == 0) return t1.id - t2.id;
            return cmp;
        });

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int command = Integer.parseInt(st.nextToken());
            if(command == 100){
                int n = Integer.parseInt(st.nextToken()); // 도시 개수
                int m = Integer.parseInt(st.nextToken()); // 간선 개수
                int[] arr = new int[m* 3];
                for (int j = 0; j < m * 3; j++) {
                    arr[j] = Integer.parseInt(st.nextToken());
                }
                buildLand(n, m, arr);
                S = 0;
                dijkstra();
            }
            else if(command == 200){
                int id = Integer.parseInt(st.nextToken());
                int revenue = Integer.parseInt(st.nextToken());
                int dest = Integer.parseInt(st.nextToken());
                addPackage(id, revenue, dest);
            }
            else if(command == 300){
                int id = Integer.parseInt(st.nextToken());
                cancelPackage(id);
            }
            else if(command == 400){
                System.out.println(sellPackage());
            }
            else if (command == 500){
                int s_param = Integer.parseInt(st.nextToken());
                changeStart(s_param);
            }


        }
    }
}