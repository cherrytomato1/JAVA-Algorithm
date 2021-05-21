package boj.boj0146_210521_17299_g3;

import java.util.*;
import java.io.*;

public class 백준_17299번_오등큰수 {
	private static final int MAX = 1_000_000;
	private static int N;
	private static int[] arr;
	private static int[] cnt;

	public static void main(String[] args) throws IOException{
		init();
		solve();
		printArr();
	}

	private static int init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N + 1];
		cnt = new int[MAX + 1];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		for (int i = 1; i <= N; i++) {
			cnt[arr[i] = Integer.parseInt(st.nextToken())]++;
		}
		return N;
	}

	private static void solve() {
		final int IDX = 0;
		final int VAL = 1;
		ArrayDeque<int[]> stk = new ArrayDeque<>();

		for (int i = 1; i <= N ; i++) {
			while (!stk.isEmpty() && cnt[arr[i]] > cnt[stk.peek()[VAL]]) {
				arr[stk.pop()[IDX]] = arr[i];
			}
			stk.push(new int[]{i, arr[i]});
			arr[i] = -1;
		}
	}

	private static void printArr() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N ; i++) {
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb);
	}
}
