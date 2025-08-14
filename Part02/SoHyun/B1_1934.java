import java.util.*;
import java.io.*;

public class B1_1934 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int d = gdc(a, b);

            System.out.println(a * b / d);
        }


    }

    public static int gdc(int a, int b) {
        if (b == 0)
            return a;

        return gdc(b, a % b);
    }
}
