package boj.boj0125_210429_1052_s1;

import java.util.*;
import java.io.*;

public class 백준_1052번_물병 {
	private static int N;
	private static int K;
	private static int[] arr;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[32];
		//0 == 2 ^ 0, 1 == 2 ^ 1, ... n = 2 ^ n
		arr[0] = N;
	}

	private static int solve() {
		int ret = 0;
		for (int size = 1, cnt; ; arr[0]++, ret++) {
			size = go(size);
			cnt = check(size);
			if(cnt <= K)    return ret;
		}
	}

	private static int check(int size) {
		int ret = 0;
		for (int i = 0; i < size ; i++) {
			ret += arr[i];
		}
		return ret;
	}

	private static int go(int size) {
		for (int i = 0; i < size ; i++) {
			if(arr[i] < 2)     continue;
			arr[i + 1] += arr[i] / 2;
			arr[i] = arr[i] % 2;
			if(i == size - 1)   size++;
		}
		return size;
	}
}
