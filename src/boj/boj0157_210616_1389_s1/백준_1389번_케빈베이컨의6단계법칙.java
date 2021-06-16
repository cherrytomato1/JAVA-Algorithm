package boj.boj0157_210616_1389_s1;

import java.util.*;
import java.io.*;

public class 백준_1389번_케빈베이컨의6단계법칙 {
	private static int[][] arr;
	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		InputStream in = System.in;
		final BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N ; i++) {
			Arrays.fill(arr[i], Integer.MAX_VALUE/3);
			arr[i][i] = 0;
		}

		for (int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from][to] = 1;
			arr[to][from] = 1;
		}
	}

	private static int solve() {
		floydWarshall();
		return getMinCostNumber();
	}

	private static void floydWarshall() {
		//경출도
		for (int i = 1; i <= N ; i++) {
			for (int j = 1; j <= N ; j++) {
				for (int k = 1; k <= N ; k++) {
					arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
				}
			}
		}
	}

	private static int getMinCostNumber() {
		int minCostNumber = 0;
		int minValue = Integer.MAX_VALUE;
		for (int i = 1; i <= N ; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				cnt += arr[i][j];
			}
//			System.out.println(cnt + " " + i);
			if (minValue > cnt) {
				minValue = cnt;
				minCostNumber = i;
			}
		}
		return minCostNumber;
	}
}
