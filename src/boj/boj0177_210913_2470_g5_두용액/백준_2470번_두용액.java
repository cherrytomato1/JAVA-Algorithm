package boj.boj0177_210913_2470_g5_두용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_2470번_두용액 {

	private static int N;
	private static long[] arr;

	public static void main(String[] args) throws IOException {
		init();
		long[] ans = solve();
		System.out.println(ans[0] + " " + ans[1]);
	}

	private static void init() throws IOException{
		InputStream in = System.in;
		final BufferedReader br = new BufferedReader(new InputStreamReader(in));
		N = Integer.parseInt(br.readLine());

		arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N ; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
	}

	private static long[] solve() {
		long minDiff = Long.MAX_VALUE;
		long[] ret = new long[2];

		Arrays.sort(arr);


		for (int i = 0; i < N ; i++) {
			long key = arr[i];
			long lowVal = arr[lowerBound(-key)];
			long uppVal = arr[upperBound(-key)];

			long resVal = Math.min(Math.abs(key + lowVal), Math.abs(key + uppVal));

			if (minDiff <= resVal) {
				continue;
			}

			minDiff = resVal;
			ret[0] = key;
			ret[1] = resVal == Math.abs(key + lowVal) ? lowVal : uppVal;

		}

		return ret;
	}

	private static int lowerBound(long key) {
		int low = 0;
		int high = N - 1;

		while (low < high) {
			int mid = (low + high) >> 1;
			long midVal = arr[mid];

			if (midVal < key) {
				low = mid + 1;
				continue;
			}
			high = mid - 1;
		}

		return low;
	}

	private static int upperBound(long key) {
		int low = 0;
		int high = N - 1;

		while (low < high) {
			int mid = (low + high) >> 1;
			long midVal = arr[mid];

			if (midVal <= key) {
				low = mid + 1;
				continue;
			}
			high = mid - 1;
		}
		return high;
	}
}
