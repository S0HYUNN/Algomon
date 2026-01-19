import java.util.*;
import java.io.*;

public class Main {
	static Map<String, Integer> words;
	static int[] dx = {-1, 1, 0, 0, -1, 1, 1, -1};
	static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};
	static int N;
	static int M;
	static int K;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int maxLength = 5;
		words = new HashMap<>();

		for(int i = 1; i <= maxLength; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					dfs(i, j, k, 1, String.valueOf(map[j][k]));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			String s = br.readLine();
			sb.append(words.getOrDefault(s, 0)).append("\n");
		}
		System.out.print(sb);
	}
	static void dfs(int ml, int x, int y, int depth, String word) {
		if(depth == ml) {
			words.put(word, words.getOrDefault(word, 0) + 1);
			return;
		}

		for(int i = 0; i < 8; i++) {
			int nx = (x + dx[i] + N) % N;
			int ny = (y + dy[i] + M) % M;

			dfs(ml, nx, ny, depth + 1, word + map[nx][ny]);
		}
	}
}