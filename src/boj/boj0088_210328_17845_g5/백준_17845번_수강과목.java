package boj.boj0088_210328_17845_g5;

import java.util.*;
import java.io.*;

public class 백준_17845번_수강과목 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int K;
	private static int[] d;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		d = new int[N + 1];
	}

	private static int solve() throws IOException{
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int value = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			for (int j = N; j >= time ; j--) {
				d[j] = Math.max(d[j], d[j - time] + value);
			}
		}
		return d[N];
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}

