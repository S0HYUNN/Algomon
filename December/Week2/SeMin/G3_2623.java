import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<List<Integer>> graph = new ArrayList<>();
        
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        for (int i = 0; i <= n; i++) graph.add(new ArrayList<Integer>());
        int[] arr = new int[n + 1];

        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 1; i <= m ; i++) {
            String[] abc = br.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            for (int j = 2; j <= a; j++) {
                int c = Integer.parseInt(abc[j]);
                graph.get(b).add(c);
                arr[c]++;

                b = c;
            }
        }

        for (int i = 1; i <= n ; i++ ) {
            if (arr[i] == 0) q.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        
        int cnt = 0;
        while (!q.isEmpty()) {
            int num = q.poll();
            sb.append(num + "\n");
            cnt++;
            
            for (int a : graph.get(num)) {
                arr[a]--;
                if (arr[a] == 0) q.offer(a);
            }
        }
        if(cnt != n){
            System.out.println(0);
        }
        else{
            System.out.println(sb);
        }
    }
}