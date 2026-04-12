import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long X = Long.parseLong(st.nextToken());
		long Y = Long.parseLong(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		long cost;

		long min = Math.min(X, Y);
		long max = Math.max(X, Y);
		long diff = max - min;

		if(2 * W <= S) {
			cost = (X + Y) * W;
		} else {
			if(diff % 2 == 0) {
				cost = Math.min(max * S, min * S + diff * W);
			} else {
				cost = Math.min(min * S + diff * W, (max - 1) * S + W);
			}
		}


		System.out.println(cost);
	}
}