import java.io.*;
import java.util.*;

class Town{
	int to;
	int cost;
	
	public Town(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}
	
}

public class Main {

	static int n,m,x;
	static List<Town>[] nList;
	static List<Town>[] rList;
	static int INF = 1_000_000_000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nmx = br.readLine().split(" ");
		
		n = Integer.parseInt(nmx[0]);
		m = Integer.parseInt(nmx[1]);
		x =  Integer.parseInt(nmx[2]);
		
		nList = new ArrayList[n+1];
		rList = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			nList[i] = new ArrayList<>();
			rList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			String[] ftc = br.readLine().split(" ");
			int from = Integer.parseInt(ftc[0]);
			int to = Integer.parseInt(ftc[1]);
			int cost = Integer.parseInt(ftc[2]);
			
			nList[from].add(new Town(to, cost));
			rList[to].add(new Town(from, cost));
		}
		
		int[] go = dijkstra(nList, x);
		int[] back = dijkstra(rList, x);
		
		int res = Integer.MIN_VALUE;
		for(int i = 1; i <= n; i++) {
			int dis = go[i] + back[i];
			
			if(dis > res) { 
				res = dis;
			}
		}
		
		System.out.println(res);
				
	}
	
	static int[] dijkstra(List<Town>[] list, int start) {
		Queue<Town> q = new PriorityQueue<>((Town t1 , Town t2)-> t1.cost - t2.cost );
		boolean[] check = new boolean[n+1];
		int[] dp = new int[n+1];
		Arrays.fill(dp, INF);
		
		q.add(new Town(start, 0));
		dp[start]=0;
		
		while(!q.isEmpty()) {
			Town pos = q.poll();
			
			int to = pos.to;
			
			if(check[to]) continue;
			
			check[to] = true;
			
			for(Town next : list[to]) {
				if(dp[to] + next.cost < dp[next.to]) {
					dp[next.to] = dp[to] + next.cost;
					q.add(new Town(next.to, dp[next.to]));
				}
			}
		}
		
		return dp;
		
	}
}