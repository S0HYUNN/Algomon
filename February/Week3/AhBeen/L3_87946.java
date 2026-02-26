class Solution {
	public int solution(int k, int[][] dungeons) {
		return dfs(k, dungeons, 0);
	}

	static int dfs(int k, int[][] dungeons, int visited) {
		int max = 0;

		for(int i = 0; i < dungeons.length; i++) {
			if((visited & (1 << i)) != 0) continue;

			if(k >= dungeons[i][0]) {
				max = Math.max(max, 1 + dfs(k - dungeons[i][1], dungeons, visited | (1 << i)));
			}
		}

		return max;
	}
}