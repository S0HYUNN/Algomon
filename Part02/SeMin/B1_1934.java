import java.io.*;
import java.util.*;

class B1_1934 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] st = br.readLine().split(" ");
            int num1 = Integer.parseInt(st[0]);
            int num2 = Integer.parseInt(st[1]);

            int gcd = getGCD(num1, num2);
            int lcd = num1 * num2 / gcd;

            sb.append(lcd).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int getGCD(int a, int b) {
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