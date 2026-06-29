import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Atom {
        int mass, velocity, dir;

        Atom(int mass, int velocity, int dir) {
            this.mass = mass;
            this.velocity = velocity;
            this.dir = dir;
        }
    }

    static final int DIR_NUM = 8;

    static int n, m, k;

    static ArrayList<Atom>[][] grid;
    static ArrayList<Atom>[][] nextGrid;

    static Pair nextPos(int x, int y, int v, int dir) {
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        int nx = (x + dx[dir] * v + n * v) % n;
        int ny = (y + dy[dir] * v + n * v) % n;

        return new Pair(nx, ny);
    }

    static void moveAll() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (Atom atom : grid[x][y]) {
                    Pair next = nextPos(x, y, atom.velocity, atom.dir);

                    nextGrid[next.x][next.y].add(
                            new Atom(atom.mass, atom.velocity, atom.dir)
                    );
                }
            }
        }
    }

    static void split(int x, int y) {
        int sumMass = 0;
        int sumVelocity = 0;

        int even = 0;
        int odd = 0;

        for (Atom atom : nextGrid[x][y]) {
            sumMass += atom.mass;
            sumVelocity += atom.velocity;

            if (atom.dir % 2 == 0) even++;
            else odd++;
        }

        int cnt = nextGrid[x][y].size();

        int newMass = sumMass / 5;
        if (newMass == 0) return;

        int newVelocity = sumVelocity / cnt;

        int startDir = (even == 0 || odd == 0) ? 0 : 1;

        for (int dir = startDir; dir < 8; dir += 2) {
            grid[x][y].add(
                    new Atom(newMass, newVelocity, dir)
            );
        }
    }

    static void compound() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int cnt = nextGrid[i][j].size();

                if (cnt == 1) {
                    grid[i][j].add(nextGrid[i][j].get(0));
                }
                else if (cnt >= 2) {
                    split(i, j);
                }
            }
        }
    }

    static void simulate() {
        nextGrid = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nextGrid[i][j] = new ArrayList<>();
            }
        }

        moveAll();
        compound();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            grid[x - 1][y - 1].add(
                    new Atom(mass, speed, dir)
            );
        }

        while (k-- > 0) {
            simulate();
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (Atom atom : grid[i][j]) {
                    ans += atom.mass;
                }
            }
        }

        System.out.println(ans);
    }
}