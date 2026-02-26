import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);

        int[] belt = new int[N * 2];
        boolean[] robot = new boolean[N];

        String[] s = br.readLine().split(" ");
        
        for (int i = 0 ; i < N * 2; i++) {
            belt[i] = Integer.parseInt(s[i]);
        }

        int level = 0;

        while (true) {
            level++;

            int tmp = belt[N * 2 - 1];
            for (int i = N * 2 - 1; i > 0 ; i--) {
                belt[i] = belt[i - 1];
            }
            belt[0] = tmp;
            
            for (int i = N - 1; i > 0 ; i--) {
                robot[i] = robot[i - 1];
            }

            robot[0] = false;
            robot[N - 1] = false;

            for (int i = N - 1; i > 0; i--) {
				if(robot[i-1] && !robot[i] && belt[i] > 0) {
					robot[i-1] = false;
					robot[i] = true;
					belt[i]--;
					robot[N-1] = false;
				}
			}
            
            if(belt[0] > 0) {
				robot[0] = true;
				belt[0]--;
			}
			
			int cnt = 0;
			for (int i = 0; i < N * 2; i++) {
				if(belt[i] == 0) cnt++;
			}
			if(cnt >= K) break;
        }
        System.out.println(level);
        
    }
}