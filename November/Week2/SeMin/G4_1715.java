import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N ; i++ ) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int result = 0;
        while(pq.size() != 1){
            int a = pq.poll();
            int b = pq.poll();
            int num = a + b;
            result += num;
            pq.offer(num);
        }
        System.out.println(result);
        
    }
}