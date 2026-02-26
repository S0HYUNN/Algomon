import java.util.*;

class Solution {
	public int solution(int[] diffs, int[] times, long limit) {
		int left = 1;
		int right = Arrays.stream(diffs).max().getAsInt();
		int answer = right;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (calcTime(mid, diffs, times, limit) <= limit) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return answer;
	}

	long calcTime(int level, int[] diffs, int[] times, long limit) {
		long total = 0;
		for (int i = 0; i < diffs.length; i++) {
			if (diffs[i] <= level) {
				total += times[i];
			} else {
				long fail = diffs[i] - level;

				if (i == 0) {
					total += times[i] + fail * times[i];
				} else {
					total += times[i] + fail * (times[i] + times[i - 1]);
				}
			}
			if (total > limit) return total;
		}
		return total;
	}

}
