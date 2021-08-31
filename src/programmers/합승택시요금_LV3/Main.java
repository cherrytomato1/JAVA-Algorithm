package programmers.합승택시요금_LV3;

public class Main {


	public static void main(String[] args) {
		Solution solution = new Solution();

	}

	private static class Solution{
		private int[][] minDist;

		private final int MAX_DIST = 0xfff_ffff;

		public int solution(int n, int s, int a, int b, int[][] fares) {
			setAdjArray(n, fares);

			setMinDist(n);

			return findMinPath(a, b, s, n);
		}

		private void setAdjArray(int n, int[][] fares) {
			minDist = new int[n + 1][n + 1];

			for (int i = 1; i <= n ; i++) {
				for (int j = 1; j <= n ; j++) {
					if (i == j) {
						continue;
					}
					minDist[i][j] = MAX_DIST;
				}
			}

			for (int[] edge: fares) {
				int from = edge[0];
				int to = edge[1];
				int weight = edge[2];

				minDist[from][to] = weight;
				minDist[to][from] = weight;
			}
		}

		private void setMinDist(int n) {
			//경출도
			for (int i = 1; i <= n ; i++) {
				for (int j = 1; j <= n ; j++) {
					for (int k = 1; k <= n ; k++) {
						minDist[j][k] = Math.min(minDist[j][k], minDist[j][i] + minDist[i][k]);
					}
				}
			}
		}

		private int findMinPath(int a, int b, int start, int n) {
			int ret = MAX_DIST;

			for (int i = 1; i <= n ; i++) {
				ret = Math.min(ret, minDist[start][i] + minDist[i][a] + minDist[i][b]);
			}

			return ret;
		}
	}

}
