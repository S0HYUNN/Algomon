import java.util.*;
import java.io.*;

class Jewelry {
    int weight;
    int value; 
 
    Jewelry(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = br.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);
        Jewelry[] jewelries = new Jewelry[N];
        
        for (int i = 0 ; i < N ; i++) {
            String[] mv = br.readLine().split(" ");
            int m = Integer.parseInt(mv[0]);
            int v = Integer.parseInt(mv[1]);
            jewelries[i] = new Jewelry(m, v);
        }

        Arrays.sort(jewelries, (Jewelry o1, Jewelry o2)->{
                if (o1.weight == o2.weight) return o2.value - o1.value;
                return o1.weight - o2.weight;
            
        });

        int[] bags = new int[K];
        
        for (int i = 0 ; i < K ; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int num = 0;
        long answer = 0;
        for (int i = 0; i < K; i++) {
            while (num < N && jewelries[num].weight <= bags[i]) {
                pq.offer(jewelries[num++].value);
            }
            if (!pq.isEmpty()) answer += pq.poll();
        }
        System.out.println(answer);
    }
}