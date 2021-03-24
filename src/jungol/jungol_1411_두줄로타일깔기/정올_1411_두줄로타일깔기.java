package jungol.jungol_1411_두줄로타일깔기;

import java.io.*;

public class 정올_1411_두줄로타일깔기 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int N;
	private static int[] d;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		d = new int[N + 1];
		d[0] = 0;
		d[1] = 1;
		d[2] = 3;
	}

//	private static int dp(int x){
//		if(d[x] != 0 || x == 0)     return d[x];
//		return d[x] = (dp(x - 1) + dp(x - 2) * 2) % 20100529;
//	}

	private static int dp2(int x){
		for(int i = 3; i <= x; i++){
			d[i] = (d[i - 1] + d[i - 2] * 2) % 20100529;
		}
		return d[x];
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(dp2(N));
	}
}
