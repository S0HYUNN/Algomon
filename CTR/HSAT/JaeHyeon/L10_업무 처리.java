import java.util.*;
import java.io.*;

public class Main {
    
    public static int H, K, R;
    public static List<List<Integer>> list = new ArrayList<>();
    public static List<List<Integer>> list2 = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < Math.pow(2, H); i++) {
            st = new StringTokenizer(br.readLine(), " ");
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < K; j++) {
                temp.add(Integer.parseInt(st.nextToken()));
            }
            list.add(temp);
        }

        for (int i = 1; i <= H; i++) {
            for (int j = 0; j < list.size() / 2; j++) {
                if (i % 2 == 1) list2.add(merge(list.get(2 * j + 1), list.get(2 * j)));
                else list2.add(merge(list.get(2 * j), list.get(2 * j + 1)));
            }

            list = list2;
            list2 = new ArrayList<>();
        }

        int total = 0;
        for (int i = 0; i < R - H && i < list.get(0).size(); i++) {
            total += list.get(0).get(i);
        }

        System.out.println(total);
    }

    public static List<Integer> merge(List<Integer> l1, List<Integer> l2) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < l1.size(); i++) {
            ans.add(l1.get(i));
            ans.add(l2.get(i));
        }

        return ans;
    }
}