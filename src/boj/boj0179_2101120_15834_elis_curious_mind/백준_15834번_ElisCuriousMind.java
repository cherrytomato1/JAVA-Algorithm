package boj.boj0179_2101120_15834_elis_curious_mind;

import java.io.*;

/**
 * 1 2 3
 * 1
 * <p>
 * 1 2 3 4
 * 3
 * <p>
 * 1 2 3 4 5
 * 1 3 5
 * 1 4
 * 2 4
 * 2 5
 * 4
 * <p>
 * 1 2 3 4 5 6
 * 1 3 5
 * 1 3 6
 * 1 4 6
 * 2 4 6
 * 2 5
 * <p>
 * 5
 * <p>
 * 1 2 3 4 5 6 7
 * 1 3 5 7
 * 1 3 6
 * 1 4 6
 * 1 4 7
 * 2 4 6
 * 2 4 7
 * 2 5 7
 * <p>
 * 7
 * <p>
 * 1 2 3 4 5 6 7 8
 * 1 3 5 7
 * 1 3 5 8
 * 1 3 6 8
 * 1 4 6 8
 * 1 4 7
 * 2 4 6 8
 * 2 4 7
 * 2 5 7
 * 2 5 8
 * <p>
 * 9
 */
public class 백준_15834번_ElisCuriousMind {

	private static long[] d;
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		d = new long[77];
		d[3] = 1;
		d[4] = 3;
		d[5] = 4;
		d[6] = 5;

		StringBuilder sb = new StringBuilder();

		for (int i = 1; ; i++) {
			long ans = solve(Integer.parseInt(br.readLine()));
			if (ans == -1) {
				break;
			}
			sb.append("Case #").append(i).append(": ").append(ans).append("\n");
		}
		System.out.println(sb);

	}

	private static long solve(int n) {
		if (n == 0) {
			return -1;
		}
		if (n == 1 || n == 2) {
			return 0;
		}
		if (d[n] != 0) {
			return d[n];
		}
		return d[n] = solve(n - 2) + solve(n - 3);
	}
}
