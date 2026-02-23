import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String king = st.nextToken();
		String stone = st.nextToken();

		int kX = king.charAt(0) - 'A';
		int kY = king.charAt(1) - '0';
		int sX = stone.charAt(0) - 'A';
		int sY = stone.charAt(1) - '0';

		int count = Integer.parseInt(st.nextToken());

		for (int i = 0; i < count; i++) {
			String op = br.readLine();

			int dx = 0, dy = 0;
			if (op.equals("R")) { dx = 1; dy = 0; }
			else if (op.equals("L")) { dx = -1; dy = 0; }
			else if (op.equals("B")) { dx = 0; dy = -1; }
			else if (op.equals("T")) { dx = 0; dy = 1; }
			else if (op.equals("RT")) { dx = 1; dy = 1; }
			else if (op.equals("LT")) { dx = -1; dy = 1; }
			else if (op.equals("RB")) { dx = 1; dy = -1; }
			else if (op.equals("LB")) { dx = -1; dy = -1; }

			int nkX = kX + dx;
			int nkY = kY + dy;

			if (nkX < 0 || nkX > 7 || nkY < 1 || nkY > 8) continue;

			if (nkX == sX && nkY == sY) {
				int nsX = sX + dx;
				int nsY = sY + dy;
				if (nsX < 0 || nsX > 7 || nsY < 1 || nsY > 8) continue;

				sX = nsX;
				sY = nsY;
			}

			kX = nkX;
			kY = nkY;
		}

		bw.write("" + (char)('A' + kX) + kY + "\n");
		bw.write("" + (char)('A' + sX) + sY + "\n");
		bw.flush();
	}
}