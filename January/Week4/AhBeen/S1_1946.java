import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int doc = Integer.parseInt(st.nextToken());
				int inter = Integer.parseInt(st.nextToken());

				arr[doc - 1] = inter;
			}
			int count = 1;
			int rank = arr[0];
			for(int i = 1; i < N; i++) {
				if(rank > arr[i]) {
					count++;
					rank = arr[i];
				}
			}
			bw.write(count + "\n");
		}

		bw.flush();
		bw.close();
	}
}
