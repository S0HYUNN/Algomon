import java.util.*;
import java.io.*;
 
public class Main {
    static int h, w;
    static int[] height;
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] s = br.readLine().split(" ");
        h = Integer.parseInt(s[0]);
        w = Integer.parseInt(s[1]);
 
        height = new int[w];
        s = br.readLine().split(" ");
        for(int i = 0; i < w; i++) {
            height[i] = Integer.parseInt(s[i]);
        }
 
        int result = 0;
        for(int i = 1; i < w - 1; i++) {
            int left = 0;
            int right = 0;
 
            for(int j = 0; j < i; j++) {
                left = Math.max(height[j], left);
            }
 
            for(int j = i + 1; j < w; j++) {
                right = Math.max(height[j], right);
            }
 
            if(height[i] < left && height[i] < right) result += Math.min(left, right) - height[i];
        }
        System.out.println(result);
    }
}