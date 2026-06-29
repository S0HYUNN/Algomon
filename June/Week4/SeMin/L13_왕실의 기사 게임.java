import java.io.*;
import java.util.*;

public class Main {
    static int[] dis = {-1, 0, 1, 0};
    static int[] djs = {0, 1, 0, -1};

    private static boolean isOverlapped(int a, int b, int c, int d) {
        return !(b < c || d < a);
    }

    static class Knight {
        int si, sj, h, w, power, totalDamage;

        public Knight(int si, int sj, int h, int w, int power) {
            this.si = si; 
            this.sj = sj; 
            this.h = h;   
            this.w = w;   
            this.power = power; 
            this.totalDamage = 0;
        }

        public int getEi() {
            return this.si + this.h - 1;
        }

        public int getEj() {
            return this.sj + this.w - 1;
        }

        public boolean isAlive() {
            return this.power > 0;
        }

        public boolean isPushed(Knight other, int direction) {
            return isOverlapped(this.si, this.getEi(), other.si + dis[direction], other.getEi() + dis[direction]) &&
                   isOverlapped(this.sj, this.getEj(), other.sj + djs[direction], other.getEj() + djs[direction]);
        }
        
        public boolean canMove(int direction) {
            int movedSi = this.si + dis[direction];
            int movedEi = this.getEi() + dis[direction];
            int movedSj = this.sj + djs[direction];
            int movedEj = this.getEj() + djs[direction];

            if (!(movedSi >= 1 && movedEi <= l && movedSj >= 1 && movedEj <= l)) {
                return false;
            }


            int wallCount = sumWalls[movedEi][movedEj] - sumWalls[movedEi][movedSj - 1]
                          - sumWalls[movedSi - 1][movedEj] + sumWalls[movedSi - 1][movedSj - 1];
            
            return wallCount == 0;
        }


        public void move(int direction) {
            this.si += dis[direction];
            this.sj += djs[direction];
        }

        public int getDamage() {
            return sumTraps[this.getEi()][this.getEj()] - sumTraps[this.getEi()][this.sj - 1]
                 - sumTraps[this.si - 1][this.getEj()] + sumTraps[this.si - 1][this.sj - 1];
        }


        public void descPower(int damage) {
            int actualDamage = Math.min(this.power, damage);
            this.power -= actualDamage;
            this.totalDamage += actualDamage;
        }
    }
    
    // 전역 변수 선언
    static int l, n, q;
    static int[][] board;
    static int[][] sumTraps; 
    static int[][] sumWalls; 
    static Knight[] knights;
    static boolean[] visited;

    private static boolean dfsKnight(int idx, int direction) {
        visited[idx] = true;

        if (!knights[idx].canMove(direction)) {
            return false;
        }

        for (int nextIdx = 0; nextIdx < n; nextIdx++) {
            if (!visited[nextIdx] && knights[nextIdx].isAlive() && knights[nextIdx].isPushed(knights[idx], direction)) {
                if (!dfsKnight(nextIdx, direction)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private static void moveKnight(int startIdx, int direction) {
        if (!knights[startIdx].isAlive()) {
            return;
        }

        visited = new boolean[n];
        if (!dfsKnight(startIdx, direction)) {
            return;
        }
        
        for (int idx = 0; idx < n; idx++) {
            if (visited[idx]) {
                knights[idx].move(direction);
                if (idx != startIdx) {
                    int damage = knights[idx].getDamage();
                    knights[idx].descPower(damage);
                }
            }
        }
    }

    private static void processInit(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        board = new int[l + 1][l + 1];
        sumTraps = new int[l + 1][l + 1];
        sumWalls = new int[l + 1][l + 1];
        
        for (int i = 1; i <= l; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= l; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                sumTraps[i][j] = sumTraps[i - 1][j] + sumTraps[i][j - 1] - sumTraps[i - 1][j - 1] + (board[i][j] == 1 ? 1 : 0);
                sumWalls[i][j] = sumWalls[i - 1][j] + sumWalls[i][j - 1] - sumWalls[i - 1][j - 1] + (board[i][j] == 2 ? 1 : 0);
            }
        }
        
        knights = new Knight[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            knights[i] = new Knight(r, c, h, w, k);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        processInit(br);

        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int knightIdx = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            moveKnight(knightIdx - 1, direction);
        }

        long answer = 0;
        for (int i = 0; i < n; i++) {
            if (knights[i].isAlive()) {
                answer += knights[i].totalDamage;
            }
        }
        
        System.out.println(answer);
    }
}
