import java.util.*;
import java.io.*;

class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        method("");
    }

    static void method(String result){
        if(result.length() == n){
            System.out.println(result);
            System.exit(0);
        }
        for (int i = 1; i <= 3; i++) {
            if(check(result + i)){
                method(result + i);
            }
        }
    }

    static boolean check(String s){
        int len = s.length() / 2;
        for (int i = 1; i <= len; i++) {
            String front = s.substring(s.length() - i);
            String end = s.substring(s.length() - 2 * i, s.length() - i);

            if(front.equals(end)) return false;
        }
        return true;   
    }
}