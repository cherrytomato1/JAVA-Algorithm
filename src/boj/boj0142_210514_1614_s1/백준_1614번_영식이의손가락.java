package boj.boj0142_210514_1614_s1;

import java.io.*;

public class 백준_1614번_영식이의손가락 {
	private static int N;
	private static long M;

	public static void main(String[] args) throws IOException{
		final int a = 1;
		final int b;
		b = (int)solve();

		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Long.parseLong(br.readLine());
	}

	private static long solve() {
//		return M * 4 + ((N - 1) % 4 == 0 ? (N == 5 ? (M + 1) * 4 : M * 4) : (M % 2 == 1 ? (5 - N) : (N - 1)));
		long count = 4 * M;
		if((N - 1) % 4 == 0)    return count * 2 + (N == 5 ? 4 : 0);
		return count + (M % 2 == 1 ? (5 - N) : (N - 1));
	}

}
