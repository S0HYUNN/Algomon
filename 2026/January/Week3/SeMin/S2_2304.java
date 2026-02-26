import java.util.*;
import java.io.*;

public class Main {
    static int N, sum;
    static List<Info> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            int L = Integer.parseInt(s[0]);
            int H = Integer.parseInt(s[1]);

            list.add(new Info(L, H));
        }

        Collections.sort(list, (o1, o2) -> o1.x - o2.x);

        sum = 0;   
        int pivot = 0; 

        Info highC = list.get(0); 
        for (int i = 1; i < list.size(); i++) {
            Info curC = list.get(i);

            if (highC.high <= curC.high) {   
                sum += (curC.x - highC.x) * highC.high;
                highC = curC;
                pivot = i;
            }
        }

        
        highC = list.get(list.size() - 1);
        for (int i = 1; i < list.size() - pivot; i++) {
            Info curC = list.get(list.size() - 1 - i);

            if (highC.high <= curC.high) {
                sum += (highC.x - curC.x) * highC.high;
                highC = curC;
            }
        }

        sum += list.get(pivot).high;

        System.out.println(sum);
    }
}

class Info {
    int x, high;

    public Info(int x, int high) {
        this.x = x;
        this.high = high;
    }
}
