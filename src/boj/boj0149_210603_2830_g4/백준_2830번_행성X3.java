package boj.boj0149_210603_2830_g4;

import java.io.*;

public class 백준_2830번_행성X3 {
	private static int[] countBit;
	private static int n;
	private static final int BIT_MAX = 32;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		countBit = new int[BIT_MAX];

		n = Integer.parseInt(br.readLine());
		for (int j = 0; j < n; j++) {
			int val = Integer.parseInt(br.readLine());
			for (int i = 0; val != 0 ; i++, val >>= 1) {
				if((val & 1) != 0)     countBit[i]++;
			}
		}
	}

	private static long solve() {
		long ret = 0;
		for (int i = 0; i < BIT_MAX ; i++) {
			ret += (long)countBit[i] * (n - countBit[i]) * (1 << i) ;
		}
		return ret;
	}
}
