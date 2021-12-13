package boj.boj0180_2101211_10974번_모든순열;

import java.io.*;

/**
 *  1 2 3
 *
 *   > 1 > 2 > 3
 *       > 3 > 2
 *         3 >
 *  > 1 > 1 (used)
 *      > 2 > 2 (used)
 *          > 3 (end)
*       > 3 > 1 (used)
 *          > 2 (end)
 *  > 2 > 1 > 1 (used)
 *          > 2 (used)
 *          ....
 *
 *  1 3 2
 *  2 1 3
 *  2 3 1
 *   ...
 *
 */
public class 백준_10974번_모든순열 {

	private static int N;
	private static final StringBuilder sb = new StringBuilder();
	private static int[] selected;
	private static boolean[] used;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		selected = new int[N];
		used = new boolean[N];
		dfs(0);
		System.out.println(sb);
	}

	/**
	 *
	 * @param index 선택할 자리.
	 *
	 *              i = 선택할 값
	 */
	private static void dfs(int index) {
		if (index >= N) {
			for (int i = 0; i < N ; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N ; i++) {
			if (!used[i]) {
				selected[index] = i + 1;

				used[i] = true;
				dfs(index + 1);
				used[i] = false;
			}
		}
	}
}
