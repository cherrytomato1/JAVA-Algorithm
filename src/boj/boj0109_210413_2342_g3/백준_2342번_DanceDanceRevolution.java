package boj.boj0109_210413_2342_g3;

import java.util.*;
import java.io.*;

public class 백준_2342번_DanceDanceRevolution {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static final int MAX_N = 100_000;
	private static final int INF = 0x3fff_ffff;

	private static int[] in;
	private static int N;
	private static int[][][] d;

	private static void init() throws IOException {
		in = new int[MAX_N];
		st = new StringTokenizer(br.readLine());

		int getOrder;
		while ((getOrder = Integer.parseInt(st.nextToken())) != 0) {
			in[N++] = getOrder;
		}
		d = new int[N][5][5];
	}

	private static int dp(int index, int left, int right) {
		if(left == right)   return INF;
		System.out.println(left + " " + right + " == " + index);
		if(d[index][left][right] != 0)      return d[index][left][right];
		if(index == 0)  return d[index][left][right] = move(0, right) + move(0, left);
		int ret = Math.min(dp(index - 1, in[index - 1], right)
				, dp(index - 1, left, in[index - 1]));
		System.out.println("ret "  + ret);
		return d[index][left][right] = ret + move(in[index - 1], in[index]);
	}

	private static int move(int from, int to){
		System.out.println(from + " to " + to);
		if(from == to)                  return 1;
		if(from == 0 || to == 0)        return 2;
		if(Math.abs(from - to) == 2)    return 4;
		return 3;
	}

	public static void main(String[] args) throws IOException{
		init();
		int ret = Math.min(dp(N - 1, in[N - 1], in[N - 2]), dp(N - 1, in[N - 2], in[N - 1]));
//		int ret = dp(N - 1, in[N - 1], in[N - 2]);
		System.out.println(ret);
	}
}
