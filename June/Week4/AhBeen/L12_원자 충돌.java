import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int m;
	static int k;

	static class Atom {
		int x;
		int y;
		int mass;
		int speed;
		int dir;

		Atom(int x, int y, int mass, int speed, int dir) {
			this.x = x;
			this.y = y;
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
		}
	}

	static List<Atom>[][] map;

	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new ArrayList[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < m; i++) {

			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int mass = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			map[x][y].add(
				new Atom(x, y, mass, speed, dir)
			);
		}

		while (k-- > 0) {

			move();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {

					if (map[i][j].size() >= 2) {
						synthesis(i, j);
					}
				}
			}
		}

		int answer = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				for (Atom a : map[i][j]) {
					answer += a.mass;
				}
			}
		}

		System.out.println(answer);
	}

	static void move() {

		List<Atom>[][] nextMap = new ArrayList[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				nextMap[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				for (Atom a : map[i][j]) {

					int nx = (a.x + dx[a.dir] * a.speed) % n;
					int ny = (a.y + dy[a.dir] * a.speed) % n;

					if (nx < 0) nx += n;
					if (ny < 0) ny += n;

					nextMap[nx][ny].add(
						new Atom(
							nx,
							ny,
							a.mass,
							a.speed,
							a.dir
						)
					);
				}
			}
		}

		map = nextMap;
	}

	static void synthesis(int x, int y) {

		int count = map[x][y].size();

		int massSum = 0;
		int speedSum = 0;

		boolean even = true;
		boolean odd = true;

		for (Atom a : map[x][y]) {

			massSum += a.mass;
			speedSum += a.speed;

			if (a.dir % 2 == 0) {
				odd = false;
			} else {
				even = false;
			}
		}

		map[x][y].clear();

		int newMass = massSum / 5;

		if (newMass == 0) {
			return;
		}

		int newSpeed = speedSum / count;

		int startDir = (even || odd) ? 0 : 1;

		for (int dir = startDir; dir < 8; dir += 2) {
			map[x][y].add(
				new Atom(
					x,
					y,
					newMass,
					newSpeed,
					dir
				)
			);
		}
	}
}