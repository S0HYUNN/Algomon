import java.util.*;
import java.io.*;

class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println((int)Math.pow(2, N) - 1);
        hanoi(N, 1, 2, 3);
        System.out.println(sb);
    }

    static void hanoi(int n, int start, int mid, int to){
        if (n == 1) {
            sb.append(start + " " + to + "\n");
            return;
        } 
        
        hanoi(n - 1, start, to, mid);
        sb.append(start + " " + to + "\n");
        hanoi(n - 1, mid, start, to);
    }
}