import java.util.*;
import java.io.*;

class Main {
    static int n;
    static int arr[];
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        nQueen(0);
        System.out.println(count);
    }

    static void nQueen(int depth){
        if(depth == n){
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            arr[depth] = i;
            if(check(depth)){
                nQueen(depth + 1);
            }
        }
    }

    static boolean check(int d){
        for (int i = 0; i < d; i++) {
            if(arr[d] == arr[i]) return false;
        }
        for (int i = 0; i < d; i++) {
            if(Math.abs(arr[d] - arr[i]) == Math.abs(d - i)) return false;
        }
        return true;
    }
}