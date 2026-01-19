import java.io.*;
import java.util.*;

class Main {
	public static final int BLANK = -1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] line = new int[N];
		Arrays.fill(line, BLANK);

		String[] s = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			int leftCount = Integer.parseInt(s[i]);

			int count = 0;
			for (int j = 0; j < N; j++) {
				if(line[j] == BLANK) {
					if (count == leftCount) {
						line[j] = i + 1;
						break;
					}
					count++;
				}
			}
		}


		for (int i = 0; i < N; i++) {
			System.out.print(line[i] + " ");
		}
	}
}