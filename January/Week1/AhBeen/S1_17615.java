import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String balls = br.readLine();

		int red = 0;
		int blue = 0;

		for(char c : balls.toCharArray()) {
			if(c == 'R') red++;
			else blue++;
		}

		int answer = Integer.MAX_VALUE;

		answer = Math.min(answer, red - countLeft(balls, 'R'));
		answer = Math.min(answer, red - countRight(balls, 'R'));
		answer = Math.min(answer, blue - countLeft(balls, 'B'));
		answer = Math.min(answer, blue - countRight(balls, 'B'));

		System.out.println(answer);
	}

	static int countLeft(String s, char target) {
		int count = 0;
		for(char c : s.toCharArray()) {
			if(c == target) count++;
			else break;
		}
		return count;
	}

	static int countRight(String s, char target) {
		int count = 0;
		for(int i = s.length() - 1; i >= 0; i--) {
			if(s.charAt(i) == target) count++;
			else break;
		}
		return count;
	}
}