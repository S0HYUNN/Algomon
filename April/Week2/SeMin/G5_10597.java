import java.util.*;
import java.io.*;

class Main {
    static String str;
    static int n;
    static boolean[] visited = new boolean[51];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        n = str.length();

        backtracking(0, 0, "");
    }
    static void backtracking(int idx, int max, String ans){
        if(n == idx){
            for (int i = 1; i <= max; i++) {
                if(!visited[i]) return;
            }
            System.out.println(ans);
            System.exit(0);
        }

        String s = str.substring(idx, idx + 1);
        int num = Integer.parseInt(s);
        
        if(!visited[num]){
            visited[num] = true;
            backtracking(idx + 1, (num > max)? num : max, ans + " " + s);
            visited[num] = false;            
        }

        if(idx < n - 1){
            s = str.substring(idx, idx + 2);
            num = Integer.parseInt(s);
            if(num < 51 && !visited[num]){
                visited[num] = true;
                backtracking(idx + 2, (num > max)? num : max, ans + " " + s);
                visited[num] = false;            
            }
        }
    }
}