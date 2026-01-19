import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String S = br.readLine();

		int count = 0;
		int result = 0;

		int i = 1;

		while(i < M - 1) {
			if(S.charAt(i - 1) == 'I' && S.charAt(i) == 'O' && S.charAt(i + 1) == 'I') {
				count++;
				i += 2;

				if(count >= N) {
					result++;
				}
			} else {
				count = 0;
				i++;
			}
		}
		System.out.println(result);
	}
}