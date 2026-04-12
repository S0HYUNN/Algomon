import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	static int count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		dfs(0);
		System.out.println(count);
	}

	static void dfs(int depth) {
		if(depth == N) {
			count++;
			return;
		}

		for(int i = 0; i < N; i++) {
			arr[depth] = i;

			if(isAble(depth)) {
				dfs(depth + 1);
			}
		}
	}

	static boolean isAble(int col) {
		for(int i = 0; i < col; i++) {
			if(arr[col] == arr[i]) {
				return false;
			}

			if(Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
				return false;
			}
		}
		return true;
	}
}