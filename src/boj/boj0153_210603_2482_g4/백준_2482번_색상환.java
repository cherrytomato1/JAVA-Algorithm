package boj.boj0153_210603_2482_g4;

import java.io.*;

public class 백준_2482번_색상환 {
	private static long[][] d;
	private static final int MOD = 1_000_000_003;
	private static int N;
	private static int K;

	public static void main(String[] args) throws IOException{
		System.out.println(solve());
	}

	private static int solve() throws IOException {
		InputStream in = System.in;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());

		d = new long[n + 1][k + 1];
		for (int i = 1; i <= n ; i++) {
			d[i][1] = i;
		}

		for (int i = 2; i <= n ; i++) {
			for (int j = 2; j <= i/2 && j <= k ; j++) {
				d[i][j] = (d[i - 1][j] + d[i - 2][j - 1]) % MOD;
			}
		}
		return (int)d[n][k];
	}
}

/*

	k\n     1   2   3   4   5   6   7   8   9
	1       1   2   3   4   5   6   7   8   9
	2       0   0   0   2   5   9   14  20  27
	3       0   0   0   0   0   5   10  19  31
	4       0   0   0   0   0   0   0   5   15

 */