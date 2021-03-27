package boj.boj0086_210327_1806_g4;

import java.util.*;
import java.io.*;

public class 백준_1806번_부분합 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int S;

	private static int[][] d;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		d = new int[N][N];
	}

	private static int dp() throws IOException{
		int ret = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++){
			d[i][0] = Integer.parseInt(st.nextToken());
			if(d[i][0] >= S)    ret = 0;
			for(int j = 1; j < i; j++){
				d[i][j] = d[i - 1][j - 1] + d[i][0];
				if(d[i][j] >= S)    ret = Math.min(ret, j);
			}
		}
		return ret != Integer.MAX_VALUE ? ret + 1 : 0;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(dp());
	}
}
