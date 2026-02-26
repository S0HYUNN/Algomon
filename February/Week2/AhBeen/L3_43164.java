import java.util.*;

class Solution {
	static List<String> path = new ArrayList<>();
	static String[] answer;
	static boolean finished = false;

	public String[] solution(String[][] tickets) {
		boolean[] visited = new boolean[tickets.length];

		Arrays.sort(tickets, (a, b) -> {
			if(a[0].equals(b[0])) {
				return a[1].compareTo(b[1]);
			}
			return a[0].compareTo(b[0]);
		});

		dfs(tickets, visited, "ICN", 0);

		return answer;
	}

	static void dfs(String[][] tickets, boolean[] visited, String cur, int depth) {
		path.add(cur);

		if(depth == tickets.length) {
			answer = path.toArray(new String[0]);
			finished = true;
			return;
		}

		for(int i = 0; i < tickets.length; i++) {
			if(!visited[i] && tickets[i][0].equals(cur)) {
				visited[i] = true;
				dfs(tickets, visited, tickets[i][1], depth + 1);
				visited[i] = false;

				if(finished) return;
			}
		}

		path.remove(path.size() - 1);
	}
}
