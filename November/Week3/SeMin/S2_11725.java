import java.io.*;
import java.util.*;

public class Main{
    static int N;
    static boolean visit[];
    static int parent[];
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        visit = new boolean[N+1];
        parent = new int[N+1];
      
        for(int i=0; i <= N;i++){
            arr.add(new ArrayList<>());
        }
        
        for(int i =0; i < N-1; i++){
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            arr.get(x).add(y);
            arr.get(y).add(x);
        }
        
        dfs(1);
        
        for(int i = 2; i < parent.length; i++){
            System.out.println(parent[i]);
        }
    
    }
    
    public static void dfs(int num){
        visit[num] = true;
       
         for (int i : arr.get(num)) {
            if (!visit[i]) {
                dfs(i);
                parent[i] = num;
            }
        }
             
    }
}