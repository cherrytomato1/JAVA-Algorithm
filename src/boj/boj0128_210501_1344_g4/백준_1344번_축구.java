package boj.boj0128_210501_1344_g4;

import java.io.*;

public class 백준_1344번_축구 {
	private static double[][][] d;
	private static final int ROUND_MAX = 18;
	private static double a;
	private static double b;
	private static final int[] primes = {2, 3, 5, 7, 11, 13, 17};

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		a = (double) Integer.parseInt(br.readLine())/100;
		b = (double) Integer.parseInt(br.readLine())/100;


		d = new double[ROUND_MAX + 1][ROUND_MAX + 1][ROUND_MAX + 1];
		for (int i = 0; i < 18 ; i++) {

		}
		d[0][0][0] = 1.0;
	}

	private static double solve() {
		for (int i = 1; i <= ROUND_MAX; i++) {
			for (int j = 0; j <= i; j++) {
				for (int k = 0; k <= i; k++) {
					d[i][j][k] += d[i - 1][j][k] * (1 - a) * (1 - b)
						+ (j == 0 ? 0 : (d[i - 1][j - 1][k] * a * (1 - b)))
		                + (k == 0 ? 0 : (d[i - 1][j][k - 1] * (1 - a) * b))
		                + (j == 0 || k == 0 ? 0 : (d[i - 1][j - 1][k - 1] * a * b));
				}
			}
		}

		double ret = 0;
		for (int i = 0; i <= 18 ; i++) {
			for (int idx : primes) {
				ret += d[18][idx][i];
				ret += d[18][i][idx];
				ret += d[18][idx][idx];
				//한번 더한건 0으로 만들기
				d[18][idx][i] = d[18][idx][idx] = d[18][i][idx] = 0;
			}
		}
		return ret;
	}


}
