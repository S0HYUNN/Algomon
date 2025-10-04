import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws  Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        
        int[] kmps = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            kmps[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(kmps);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(kmps[i], i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int target = Integer.parseInt(br.readLine());
            Integer idx = map.get(target);

            if (idx == null) {
                sb.append(0).append("\n");
            } else {
                sb.append(idx * (n - 1 - idx)).append("\n");
            }
        }        

        System.out.println(sb.toString());
    }
}