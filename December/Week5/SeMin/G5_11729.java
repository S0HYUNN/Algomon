import java.util.*;
import java.io.*;

class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println((int)Math.pow(2, N) - 1);
        hanoi(N, 1, 2, 3);
        System.out.println(sb);
    }

    static void hanoi(int k, int start, int mid, int end){
        if(k == 1){
            sb.append(start + " " + end + "\n");
            return;
        }
        hanoi(k-1, start, end, mid);
        sb.append(start + " " + end + "\n");
        hanoi(k-1, mid, start, end);
    }
}