import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine();

		Stack<Character> stack = new Stack<>();
		// StringBuilder sb = new StringBuilder();

		for(char ch : str.toCharArray()) {
			if(Character.isAlphabetic(ch)) {
				bw.write(ch);
			} else if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {
				while(!stack.isEmpty() && stack.peek() != '(') {
					bw.write(stack.pop());
				}
				stack.pop();
			} else {
				while(!stack.isEmpty() && priority(stack.peek()) >= priority(ch)) {
					bw.write(stack.pop());
				}
				stack.push(ch);
			}
		}

		while(!stack.isEmpty()) {
			bw.write(stack.pop());
		}
		bw.flush();
		bw.close();
	}

	static int priority(char op) {
		if(op == '*' || op == '/') return 2;
		if(op == '+' || op == '-') return 1;
		return 0;
	}
}
