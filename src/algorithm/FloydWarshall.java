package algorithm;

public class FloydWarshall {

	/*
		인접 배열을 입력받아 각 간선의 최소경로를 압축
	 */
	public static void floydWarshall(int[][] minDist, int n) {
		//경출도
		for (int i = 1; i <= n ; i++) {
			for (int j = 1; j <= n ; j++) {
				for (int k = 1; k <= n ; k++) {
					minDist[j][k] = Math.min(minDist[j][k], minDist[j][i] + minDist[i][k]);
				}
			}
		}
	}
}
