import java.util.*;
import java.io.*;

class Main {
    static Map<Integer, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T ; tc++ ) {
            Queue<Integer> min = new PriorityQueue<>();
            Queue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
            map = new HashMap<>();
            
            int k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++ ) {
                String[] s = br.readLine().split(" ");
                String command = s[0];
                int num = Integer.parseInt(s[1]);
                if(command.equals("I")) {
					max.add(num);
					min.add(num);
					map.put(num, map.getOrDefault(num, 0) + 1);
				}else {
					if(map.size() == 0) continue;
					if(num == 1) delete(max);
                    else delete(min);
				}
            }
            if(map.size() == 0) System.out.println("EMPTY");
            else{
                int res = delete(max);
                System.out.print(res);
                if(map.size() > 0) res = delete(min);
                System.out.println(" " + res);
            }
        }
    }

    static int delete(Queue<Integer> q){
        int res = 0;
        while (true) {
            res = q.poll();
            int cnt = map.getOrDefault(res, 0);
            if(cnt == 0) continue;
            if(cnt == 1) map.remove(res);
            else map.put(res, cnt - 1);
            break;
        }
        return res;
    }
}