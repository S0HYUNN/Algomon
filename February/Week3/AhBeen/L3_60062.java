import java.util.*;

class Solution {
	static int answer = Integer.MAX_VALUE;
	static int n;
	static int[] weak;
	static int[] dist;
	public int solution(int n, int[] weak, int[] dist) {
		this.n = n;
		this.dist = dist;

		int length = weak.length;
		this.weak = new int[length * 2];

		for(int i = 0; i < length; i++) {
			this.weak[i] = weak[i];
			this.weak[i + length] = weak[i] + n;
		}

		permute(new boolean[dist.length], new ArrayList<>());

		return answer == Integer.MAX_VALUE ? -1 : answer;
	}

	static void permute(boolean[] visited, List<Integer> friendList) {

		if(friendList.size() >= answer) return;

		if(friendList.size() > 0) {
			check(friendList);
		}

		if(friendList.size() == dist.length) return;

		for(int i = 0; i < dist.length; i++) {
			if(!visited[i]) {

				visited[i] = true;
				friendList.add(dist[i]);

				permute(visited, friendList);

				friendList.remove(friendList.size() - 1);
				visited[i] = false;
			}
		}
	}

	static void check(List<Integer> friendList) {

		int length = weak.length / 2;

		for(int s = 0; s < length; s++) {

			int friendIdx = 0;
			int coverage = weak[s] + friendList.get(friendIdx);

			boolean success = true;

			for(int i = s; i < s + length; i++) {

				if(weak[i] > coverage) {

					friendIdx++;

					if(friendIdx >= friendList.size()) {
						success = false;
						break;
					}

					coverage = weak[i] + friendList.get(friendIdx);
				}
			}

			if(success) {
				answer = Math.min(answer, friendIdx + 1);
			}
		}
	}
}