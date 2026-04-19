import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] box = new int[N][M];
        int[] rowCount = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = 0;
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] > 0) cnt++;
            }
            rowCount[i] = cnt;
        }

        int answer = Integer.MAX_VALUE;

        // 조커 박스 선택
        for (int joker = 0; joker < N; joker++) {
            int moves = 0;
            boolean[] usedColor = new boolean[M];

            for (int i = 0; i < N; i++) {
                if (i == joker) continue;

                // 색 2개 이상
                if (rowCount[i] >= 2) moves++;
                // 색 없음
                else if (rowCount[i] == 0) continue;
                // 색 1개
                else {
                    int color = -1;

                    for (int j = 0; j < M; j++) {
                        if (box[i][j] > 0) {
                            color = j;
                            break;
                        }
                    }

                    if (usedColor[color]) moves++; 
                    else usedColor[color] = true;
                }
            }

            answer = Math.min(answer, moves);
        }

        System.out.println(answer);
    }
}