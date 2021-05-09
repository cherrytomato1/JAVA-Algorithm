package boj.boj0135_210509_17298_g4;

import java.util.*;
import java.io.*;

public class 백준_17298번_오큰수 {
	public static void main(String[] args) throws IOException{
		System.out.println(makeArr(solve()));
	}

	private static int[] solve() throws IOException {
		final int IDX = 0;
		final int VAL = 1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] res = new int[N];

		ArrayDeque<int[]> stk = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N ; i++) {
			res[i] = -1;
			int in = Integer.parseInt(st.nextToken());
			while (!stk.isEmpty() && stk.peek()[VAL] < in){
				res[stk.pop()[IDX]] = in;
			}
			stk.push(new int[]{i, in});
		}
		return res;
	}

	private static String makeArr(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int j : arr) {
			sb.append(j).append(" ");
		}
		return sb.toString();
	}
}
