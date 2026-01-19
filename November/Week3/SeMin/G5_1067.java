import java.io.*;
import java.util.*;

public class Main{
    static int N, remove;
    static int count =0;
    static boolean[] visit; 
    static List<Integer>[] tree;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        visit = new boolean[N];
        
        tree = new ArrayList[N];
        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        } 
        
        int root = 0;
        String[] s = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            int p = Integer.parseInt(s[i]);
            if(p != -1){
                tree[p].add(i);
            }
            else{
                root = i;
            }
        }
        
        remove = Integer.parseInt(br.readLine());
        
        if(remove == root){
            System.out.println(0);
        }else{
            dfs(root);
            System.out.println(count);
        }
    }
    
    static void dfs(int n){
        visit[n] = true;
        int child = 0;
        for(int i : tree[n]){
            if(i != remove && !visit[i]){
                child++;
                dfs(i);
            }
        }
        if(child == 0){
            count++;
        }
    }
}