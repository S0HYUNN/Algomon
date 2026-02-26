import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] dist = new int[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				if (i == j) dist[i][j] = 0;
				else if (line.charAt(j) == 'Y') dist[i][j] = 1;
				else dist[i][j] = 1000000;
			}
		}
		int[][] arr = floyd(dist, N);

		int result = 0;

		for(int i = 0; i < N; i++) {
			int count = 0;
			for(int j = 0; j < N; j++) {
				if(i != j && arr[i][j] <= 2) {
					count++;
				}
			}
			result = Math.max(result, count);
		}
		System.out.println(result);
	}

	static int[][] floyd(int[][] dist, int N) {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		return dist;
	}
}
