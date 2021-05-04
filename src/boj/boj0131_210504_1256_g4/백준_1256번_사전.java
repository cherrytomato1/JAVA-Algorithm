package boj.boj0131_210504_1256_g4;

import java.util.*;
import java.io.*;

public class 백준_1256번_사전 {
	private static long d[][];
	private static int N;
	private static int M;
	private static long K;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(makeTable(N, M) < K ? -1 : findString(N, M, K));
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());

		d = new long[N + 1][M + 1];
	}

	private static long makeTable(int n, int m) {
		if(d[n][m]!= 0)         return d[n][m];
		if(n == 0 || m == 0)    return d[n][m] = 1;
		long val = makeTable(n - 1, m) + makeTable(n, m - 1);
		return d[n][m] = val > K ? K + 1 : val;
	}

	private static String findString(int a, int z, long idx) {
		if (a == 0 || z == 0) {
			StringBuilder sb = new StringBuilder();
			while (a-- > 0) sb.append("a");
			while (z-- > 0) sb.append("z");
			return sb.toString();
		}

		if(idx <= d[a - 1][z])  return "a" + findString(a - 1, z, idx);
		return  "z" + findString(a, z - 1, idx - d[a - 1][z]);
	}
}
