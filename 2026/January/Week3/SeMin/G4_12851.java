import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {-1, 1, 2};
	static int cnt = 0, res = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int k = Integer.parseInt(s[1]);
		
		bfs(n, k);
		System.out.println(res);
		System.out.println(cnt);
	}
	
	static void bfs(int start, int destination) {
		Queue<Integer> q = new LinkedList<>();
		int[] move = new int[100_001];
		q.add(start);
		
		while(!q.isEmpty()) {
			int pos = q.poll();
			
			if(res < move[pos]) return;
			if(move[pos] <= res && pos == destination) {
				res = move[pos];
				cnt++;
			}

			for(int i = 0; i < 3; i++) {
				int next = pos;
				if(i == 2) {
					next = pos * dx[i];
				}else {
					next = pos + dx[i];	
				}
				
				if (next >= 0 && next < 100001) {
					if(move[next] == 0 || move[next] >= move[pos] + 1) {
						move[next] = move[pos]+1;
						q.add(next);
					}
				}
			}
		}
	}
}