import java.util.*;
import java.io.*;

public class Main {
	static class Ant {
		char name;
		char dir;

		Ant(char name, char dir) {
			this.name = name;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N1 = Integer.parseInt(st.nextToken());
		int N2 = Integer.parseInt(st.nextToken());

		String g1 = br.readLine();
		String g2 = br.readLine();

		int T = Integer.parseInt(br.readLine());

		List<Ant> ants = new ArrayList<>();

		for(int i = g1.length() - 1; i >= 0; i--) {
			ants.add(new Ant(g1.charAt(i), 'R'));
		}

		for(int i = 0; i < g2.length(); i++) {
			ants.add(new Ant(g2.charAt(i), 'L'));
		}

		int length = ants.size();

		for(int t = 0; t < T; t++) {
			for(int i = 0; i < length - 1; i++) {
				if (ants.get(i).dir == 'R' && ants.get(i + 1).dir == 'L') {
					Collections.swap(ants, i, i + 1);
					i++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(Ant a : ants) {
			sb.append(a.name);
		}
		System.out.println(sb);
	}
}