package swea.solution_D3_5607;

import java.util.*;
import java.io.*;

public class SWEA_5607_조합 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final long MOD = 1_234_567_891;
	private static final int INPUT_MAX = 1_000_000;

	private static long[] fac;
	private static int N;
	private static int R;

	private static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
	}

	private static void setFactorial() {
		fac = new long[INPUT_MAX + 1];
		fac[0] = 1;
		for (int i = 1; i <= INPUT_MAX ; i++) {
			fac[i] = (fac[i - 1] * i) % MOD;
		}
	}

	private static long comb(int n, int r) {
		long ret = (exp(fac[n - r], MOD - 2) * exp(fac[r], MOD - 2)) % MOD;
		return (fac[n] * ret) % MOD;
	}

	private static long exp(long x, long y) {
		if(y == 0)  return 1;
		if(y == 1)  return x;
		long ret = exp(x, y/2);
		ret = (ret * ret) % MOD;
		return y % 2 == 1 ? (ret * x) % MOD : ret;
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		setFactorial();
		for (int tc = 1; tc <= T ; tc++) {
			init();
			sb.append("#").append(tc).append(" ").append(comb(N, R)).append("\n");
		}
		System.out.println(sb);
	}
}
