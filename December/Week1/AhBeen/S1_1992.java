import java.util.*;
import java.io.*;

public class Main {
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		System.out.println(compress(0, 0, N));
	}

	static String compress(int r, int c, int size) {
		if(isSame(r, c, size)) {
			return String.valueOf(map[r][c]);
		}

		int newSize = size / 2;

		String lt = compress(r, c, newSize);
		String rt = compress(r, c + newSize, newSize);
		String lb = compress(r + newSize, c, newSize);
		String rb = compress(r + newSize, c + newSize, newSize);

		return "(" + lt + rt + lb + rb + ")";
	}

	static boolean isSame(int r, int c, int size) {
		int first = map[r][c];

		for(int i = r; i < r + size; i++) {
			for(int j = c; j < c + size; j++) {
				if(map[i][j] != first) return false;
			}
		}
		return true;
	}
}