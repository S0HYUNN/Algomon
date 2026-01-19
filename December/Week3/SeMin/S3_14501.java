import java.util.*;
import java.io.*;

class Main {
    static class Counsel{
        int time, money;
        Counsel(int time, int money){
            this.time = time;
            this.money = money;
        }
    }
    static int N;
    static int result = 0;
    static Counsel[] arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Counsel[N + 1];
        
        for (int i = 1; i <= N ; i++ ) {
            String[] tp = br.readLine().split(" ");
            int t = Integer.parseInt(tp[0]);
            int p = Integer.parseInt(tp[1]);
            arr[i] = new Counsel(t, p);
        }

        dfs(1, 0);
        System.out.println(result);
    }
    static void dfs(int day, int money){
        if (day > N) {
            result = Math.max(result, money);
            return;
        }
        
        dfs(day + 1, money);
        if (day + arr[day].time <= N + 1) dfs(day + arr[day].time, money + arr[day].money);
    }


    
}