import java.util.*;
import java.io.*;

class B1_2609 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] st = br.readLine().split(" ");
        int a = Integer.parseInt(st[0]);
        int b = Integer.parseInt(st[1]);

        int gcd = getGCD(a, b);
        int lcm = getLCM(a, b);
        sb.append(gcd).append("\n");
        sb.append(lcm);
        System.out.println(sb.toString());
    }

    public static int getLCM(int a, int b) {
        return a * b / getGCD(a, b);
    }

    public static int getGCD(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }


}


