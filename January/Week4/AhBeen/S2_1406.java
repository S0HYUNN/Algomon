import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String line = br.readLine();
		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();
		int N = line.length();

		for(int i = 0; i < N; i++) {
			left.push(line.charAt(i));
		}

		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();

			switch (cmd) {
				case "L":
					if(!left.isEmpty()) {
						char l = left.pop();
						right.push(l);
					}
					break;
				case "D":
					if(!right.isEmpty()) {
						char r = right.pop();
						left.push(r);
					}
					break;
				case "B":
					if(!left.isEmpty()) {
						left.pop();
					}
					break;
				case "P":
					left.push(st.nextToken().charAt(0));
					break;
			}
		}
		for(char c : left) {
			bw.write(c);
		}
		while(!right.isEmpty()) {
			bw.write(right.pop());
		}
		bw.flush();
		bw.close();
	}
}
