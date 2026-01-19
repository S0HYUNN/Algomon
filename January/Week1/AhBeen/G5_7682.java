import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String s = br.readLine();
			if (s.equals("end")) break;

			char[][] board = new char[3][3];
			int xCount = 0;
			int oCount = 0;

			for (int i = 0; i < 9; i++) {
				board[i / 3][i % 3] = s.charAt(i);
				if (s.charAt(i) == 'X') xCount++;
				if (s.charAt(i) == 'O') oCount++;
			}

			boolean xWin = win(board, 'X');
			boolean oWin = win(board, 'O');
			boolean valid = true;

			if (!(xCount == oCount || xCount == oCount + 1)) valid = false;
			else if (xWin && oWin) valid = false;
			else if (xWin && xCount != oCount + 1) valid = false;
			else if (oWin && xCount != oCount) valid = false;
			else if (!xWin && !oWin && xCount + oCount != 9) valid = false;

			System.out.println(valid ? "valid" : "invalid");
		}
	}

	static boolean win(char[][] b, char c) {
		for (int i = 0; i < 3; i++) {
			if (b[i][0] == c && b[i][1] == c && b[i][2] == c) return true;
			if (b[0][i] == c && b[1][i] == c && b[2][i] == c) return true;
		}
		if (b[0][0] == c && b[1][1] == c && b[2][2] == c) return true;
		if (b[0][2] == c && b[1][1] == c && b[2][0] == c) return true;
		return false;
	}
}
