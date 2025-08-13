import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_1934 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            int gcd = getGCD(num1, num2);
            int lcd = num1 * num2 / gcd;

            sb.append(lcd).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int getGCD(int num1, int num2) {
        if (num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }

        while (num2 != 0) {
            int r = num1 % num2;
            num1 = num2;
            num2 = r;
        }
        return num1;
    }
}
