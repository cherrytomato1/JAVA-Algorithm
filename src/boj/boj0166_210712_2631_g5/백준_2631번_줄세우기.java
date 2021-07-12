package boj.boj0166_210712_2631_g5;

import java.io.*;

public class 백준_2631번_줄세우기 {
	private static int N;
	private static int[] seq;
	private static int[] dp;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		InputStream in = System.in;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		N = Integer.parseInt(br.readLine());

		seq = new int[N];
		dp = new int[N];

		for (int i = 0; i < N ; i++) {
			seq[i] = Integer.parseInt(br.readLine());
		}
	}

	private static int solve() {
		int maxLength = 0;
		for (int i = 0; i < N; i++) {
			int idx = lowerBound(0, maxLength, dp, seq[i]);
//			System.out.println(idx);
			dp[idx] = seq[i];
			if(idx == maxLength)    maxLength++;
		}

		return N - maxLength;
	}

	private static int lowerBound(int start, int end, int[] arr, int key) {
		int low = start;
		int high = end - 1;

		while (low <= high) {
			int mid = (high + low) >> 1;
			int midValue = arr[mid];
			if(midValue < key)      low = mid + 1;
			else                    high = mid - 1;
		}

		return low;
	}
}
