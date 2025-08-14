import java.util.*;
import java.io.*;

public class B5_10430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        sb.append((a+b)%c).append("\n");
        sb.append(((a%c) + (b%c)) %c).append("\n");
        sb.append((a*b)%c).append("\n");
        sb.append((a%c) * (b%c) %c);

        System.out.println(sb);

    }
}
