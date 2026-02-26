import java.util.*;

class Solution {
	public int solution(String begin, String target, String[] words) {
		int answer = 0;
		Queue<String> q = new ArrayDeque<>();
		boolean[] visited = new boolean[words.length];

		q.add(begin);

		while(!q.isEmpty()) {

			int size = q.size();

			for(int s = 0; s < size; s++) {
				String str = q.poll();

				if(str.equals(target)) {
					return answer;
				}
				for(int i = 0; i < words.length; i++) {
					if(visited[i]) continue;

					String word = words[i];
					int diff = 0;

					for(int j = 0; j < word.length(); j++) {
						if(str.charAt(j) != word.charAt(j)) diff++;
						if(diff > 1) break;
					}

					if(diff == 1) {
						q.add(word);
						visited[i] = true;
					}
				}
			}
			answer++;
		}

		return 0;
	}
}