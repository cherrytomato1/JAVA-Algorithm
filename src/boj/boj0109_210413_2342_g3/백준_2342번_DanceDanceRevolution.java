package boj.boj0109_210413_2342_g3;

import java.util.*;
import java.io.*;

public class 백준_2342번_DanceDanceRevolution {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static final int MAX_N = 100_001;
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
		if(index == N)  return 0;
		if(left != 0 && left == right)   return INF;
		if(d[index][left][right] != 0)      return d[index][left][right];
		int leftMove = dp(index + 1, in[index], right) + move(left, in[index]);
		int rightMove = dp(index + 1, left, in[index]) + move(right, in[index]);
		return d[index][left][right] = Math.min(leftMove, rightMove);
	}

	private static int move(int from, int to){
		if(from == to)                  return 1;
		if(from == 0 || to == 0)        return 2;
		if(Math.abs(from - to) == 2)    return 4;
		return 3;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(dp(0,0,0));
	}
}
