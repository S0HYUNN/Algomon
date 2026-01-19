import java.util.*;
import java.io.*;

public class Main {
	static int R, C;
	static String[][] yard;
	static boolean[][] visited;
	static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
	static int sheepTotal, wolfTotal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");

		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);

		yard = new String[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
            s = br.readLine().split("");
            for(int j = 0; j < C; j++){
			    yard[i][j] = s[j];
            }
        }
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!yard[i][j].equals("#") && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}
		System.out.println(sheepTotal + " " + wolfTotal);

	}

	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		
		visited[x][y] = true;
		
		q.add(new int[] { x, y });
		
		int sheepCnt = 0, wolfCnt = 0;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			
			if (yard[now[0]][now[1]].equals("o")) sheepCnt++;
			else if (yard[now[0]][now[1]].equals("v")) wolfCnt++;
			
			
			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if (!isRange(nx, ny) || visited[nx][ny] || yard[nx][ny].equals("#")) continue;
				visited[nx][ny] = true;
				q.add(new int[] { nx, ny });

			}
		}
		if (sheepCnt > wolfCnt)sheepTotal += sheepCnt;
		else wolfTotal += wolfCnt;			

	}

	static boolean isRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
}