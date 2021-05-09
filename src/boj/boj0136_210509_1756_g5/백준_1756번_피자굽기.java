package boj.boj0136_210509_1756_g5;

import java.util.*;
import java.io.*;

public class 백준_1756번_피자굽기 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int[] ovens;
	private static int N;
	private static int D;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		ovens = new int[D];
		st = new StringTokenizer(br.readLine());

		ovens[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < D ; i++) {
			ovens[i] = Math.min(Integer.parseInt(st.nextToken()), ovens[i - 1]);
		}
	}

	private static int solve() throws IOException{
		st = new StringTokenizer(br.readLine());
		int top = D;
		for (int i = 0; i < N && top >= 0; i++) {
			int dough = Integer.parseInt(st.nextToken());
			top = binSearch(0, top, dough);
		}
		return top + 1;
	}

	private static int binSearch(int start, int end, int key) {
		int low = start;
		int high = end - 1;

		while (low <= high) {
			int mid = (low + high) >> 1;
			int midVal = ovens[mid];
			if(midVal < key)   high = mid - 1;
			else                low = mid + 1;
		}
		return low - 1;
	}
}