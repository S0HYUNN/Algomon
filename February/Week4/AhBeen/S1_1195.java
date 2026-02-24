import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String gear1 = br.readLine();
		String gear2 = br.readLine();

		int minWidth = gear1.length() + gear2.length();

		for(int i = -gear2.length(); i <= gear1.length(); i++) {
			boolean flag = true;

			for(int j = 0; j < gear1.length(); j++) {
				int lowerIdx = j - i;
				if(lowerIdx < 0 || lowerIdx >= gear2.length()) {
					continue;
				}

				int sum = (gear2.charAt(lowerIdx) - '0') + (gear1.charAt(j) - '0');
				if(sum == 4) {
					flag = false;
					break;
				}
			}
			if(flag) {
				int cur = Math.max(i + gear2.length(), gear1.length()) - Math.min(i, 0);
				minWidth = Math.min(minWidth, cur);
			}
		}

		bw.write(String.valueOf(minWidth));
		bw.flush();
		bw.close();
	}
}