import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		while(T --> 0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			Map<Integer, Integer> count = new HashMap<>();
			Map<Integer, List<Integer>> map = new HashMap<>();

			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			for(int team : arr) {
				count.put(team, count.getOrDefault(team, 0) + 1);
			}

			int rnk = 1;
			for(int i = 0; i < N; i++) {
				int team = arr[i];
				if(count.get(team) >= 6) {
					map.putIfAbsent(team, new ArrayList<>());
					map.get(team).add(rnk++);
				}
			}

			int winner = 0;
			int minS = Integer.MAX_VALUE;
			int fifth = Integer.MAX_VALUE;

			for(int team : map.keySet()) {
				List<Integer> list = map.get(team);
				int sum = 0;
				for(int i = 0; i < 4; i++) {
					sum += list.get(i);
				}
				if(sum < minS || (sum == minS && list.get(4) < fifth)) {
					minS = sum;
					fifth = list.get(4);
					winner = team;
				}
			}
			System.out.println(winner);
		}
	}
}
