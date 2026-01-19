import java.io.*;
import java.util.*;

public class Main {
	static int[] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);

		board = new int[101];
		for (int i = 1; i < board.length; i++) {
			board[i] = i;
		}

		while (n-- > 0) {
			String[] s = br.readLine().split(" ");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);

			board[x] = y;
		}
		while (m-- > 0) {
			String[] s = br.readLine().split(" ");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);

			board[x] = y;
		}


		int result = bfs(1);
		System.out.println(result);

	}

	private static int bfs(int startNode) {

		int[] check = new int[101]; 
		Queue<Integer> q = new LinkedList<>();
		q.offer(startNode); 
		check[startNode] = 0; 

		while (true) {
			int visitedNum = q.poll();

			for (int i = 1; i < 7; i++) {            	
				int newNode = visitedNum + i;

				if (newNode > 100) continue;
                
				if (check[board[newNode]] == 0) { 
					q.offer(board[newNode]);
					check[board[newNode]] = check[visitedNum] + 1;
				}
				if (board[newNode] == 100) {
					return check[100];
				}
			}

		}

	}

}