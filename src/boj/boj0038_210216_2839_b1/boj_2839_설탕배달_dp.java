package boj.boj0038_210216_2839_b1;

import java.io.*;

public class boj_2839_설탕배달_dp {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;

	private static int[] d;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		d = new int[N + 1];
		if( N >= 3) d[3] = 1;
		if( N >= 5) d[5] = 1;
	}

	private static int dp(int x){
		for(int i = 1 ; i <= N ; i++){
			int min = Integer.MAX_VALUE;
			if( i >= 5) min = Math.min(d[i - 5], min);
			if( i >= 3) min = Math.min(d[i - 3], min);
			d[i] = min == Integer.MAX_VALUE ? min : (min + 1);
		}
		return d[x] == Integer.MAX_VALUE ? -1 : d[x];
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(dp(N));
	}
}
