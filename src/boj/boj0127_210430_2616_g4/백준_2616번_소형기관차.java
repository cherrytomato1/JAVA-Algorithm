package boj.boj0127_210430_2616_g4;

import java.util.*;
import java.io.*;
public class 백준_2616번_소형기관차 {
	private static int N;
	private static int M;
	private static int[] arr;
	private static int[][] d;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(dp());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new int[N + 1];
		for (int i = 1; i <= N ; i++) {
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		d = new int[4][N + 1];
	}

	private static int dp() {
		for (int i = 1; i <= 3 ; i++) {
			for (int j = i * M; j <= N ; j++) {
				d[i][j] = Math.max(d[i - 1][j - M] + arr[j] - arr[j - M], d[i][j - 1]);
			}
		}
		return d[3][N];
	}
}
