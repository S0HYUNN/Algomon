import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] belt = new int[N * 2];
		boolean[] robot = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < belt.length; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		int step = 0;

		while(true) {
			step++;

			int last = belt[N * 2 - 1];
			for(int i = N * 2 - 1; i > 0; i--) {
				belt[i] = belt[i - 1];
			}
			belt[0] = last;

			for(int i = N - 1; i > 0; i--) {
				robot[i] = robot[i - 1];
			}
			robot[0] = false;
			robot[N - 1] = false;

			for(int i = N - 2; i >= 0; i--) {
				if(robot[i] && !robot[i + 1] && belt[i + 1] > 0) {
					robot[i] = false;
					robot[i + 1] = true;
					belt[i + 1]--;
				}
			}
			robot[N - 1] = false;

			if(belt[0] > 0) {
				robot[0] = true;
				belt[0]--;
			}

			int zero = 0;
			for(int v : belt) {
				if(v == 0) zero++;
			}
			if(zero >= K) break;
		}
		System.out.println(step);
	}
}