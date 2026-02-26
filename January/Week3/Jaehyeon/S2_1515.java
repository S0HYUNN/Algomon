import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S2_1515 {
ㅂ
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();

        int point = 0;
        int base = 0;

        while (true) {
            base++;
            String tmp = String.valueOf(base);

            for (int i = 0; i < tmp.length(); i++) {
                if (tmp.charAt(i) == num.charAt(point)) {
                    point++;
                    if (point == num.length()) {
                        System.out.println(base);
                        return;
                    }
                }
            }
        }
    }
}
