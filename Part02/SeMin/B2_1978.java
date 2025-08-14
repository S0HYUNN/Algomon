import java.util.*;
import java.io.*;

class B2_1978 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        String[] st = br.readLine().split(" ");
        int result = 0;

        for(int i = 0; i< n; i++){
            if(getLCM(Integer.parseInt(st[i]))) result++;
        }

        System.out.println(result);
    }

    public static boolean getLCM(int a) {
        if(a == 1) return false;
        for(int i= 2; i < a / 2 +1; i++){
            if(a % i == 0) return false;
        }
        return true;
    }



}


