import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int length = Integer.parseInt(br.readLine());
		String str = br.readLine();

		int x = 0;
		int y = 0;
		int dir = 2;

		Set<String> visited = new HashSet<>();
		visited.add("0,0");

		int minX = 0;
		int maxX = 0;
		int minY = 0;
		int maxY = 0;

		for(char s : str.toCharArray()) {
			if(s == 'L') {
				dir = (dir + 3) % 4;
			} else if(s == 'R') {
				dir = (dir + 1) % 4;
			} else if(s == 'F') {
				x += dx[dir];
				y += dy[dir];
				visited.add(x + ","+ y);

				minX = Math.min(minX, x);
				maxX = Math.max(maxX, x);
				minY = Math.min(minY, y);
				maxY = Math.max(maxY, y);
			}
		}

		int H = maxX - minX + 1;
		int W = maxY - minY + 1;

		char[][] map = new char[H][W];
		for(int i = 0; i < H; i++) {
			Arrays.fill(map[i], '#');
		}

		for(String s : visited) {
			String[] p = s.split(",");
			int r = Integer.parseInt(p[0]) - minX;
			int c = Integer.parseInt(p[1]) - minY;
			map[r][c] = '.';
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < H; i++) {
			for(int j =0; j < W; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}
}