import java.io.*;
import java.util.*;

class Pair{
	int x, y;
	
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static final int dx[] = {0,0,1,-1};
	static final int dy[] = {1,-1,0,0};
	static ArrayList<Pair> unionXY = new ArrayList<>();
	static boolean visit[][];
	static int map[][];
	static int n,l,r,cnt;
	static boolean isMove = false;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		n = Integer.parseInt(s[0]);
		l = Integer.parseInt(s[1]);
		r = Integer.parseInt(s[2]);
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			s = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		move();
		
		System.out.println(cnt);
        
	}
	static void move() {
		
		while(true) {
			isMove = false;
			visit = new boolean[n][n];
			
			for(int i=0; i<n; i++) {
				for(int j=0;j<n; j++) {
					if(!visit[i][j]){
						 bfs(i,j);
					}				
				}
			}
												
			if(!isMove) break; 
			else cnt++;
		}
			
	}
	
	static void bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x,y));
		visit[x][y] = true;
		unionXY.add(new Pair(x,y));
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			x = p.x;
			y = p.y;
			
			for(int k=0; k<4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if(0<=nx && nx<n && 0<=ny && ny<n) {
					if(!visit[nx][ny] && l <= Math.abs(map[x][y] - map[nx][ny]) &&  Math.abs(map[x][y] - map[nx][ny]) <= r) {
						isMove = true;
						visit[nx][ny] = true;
						unionXY.add(new Pair(nx,ny));
						q.add(new Pair(nx,ny));
					}
				}	
			}				
		}	
		
		int sum = 0;
		for(int i=0; i<unionXY.size(); i++) {
			Pair p = unionXY.get(i);
			 sum += map[p.x][p.y];
		}
		
		for(int i=0; i<unionXY.size(); i++) {
			Pair p = unionXY.get(i);
			map[p.x][p.y] = sum / unionXY.size();
		}
		unionXY.removeAll(unionXY);
	}
}