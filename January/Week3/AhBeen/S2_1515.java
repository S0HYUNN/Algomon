import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int pointer = 0;
		int N = 0;

		while(true) {
			N++;
			String sN = String.valueOf(N);
			for(int i = 0; i < sN.length(); i++) {
				if(sN.charAt(i) == str.charAt(pointer)) {
					pointer++;
				}

				if(pointer == str.length()) {
					System.out.println(N);
					return;
				}
			}
		}
	}
}