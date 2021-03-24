package boj.boj0075_210324_1463_s3;

import java.io.*;

public class 백준_1463번_1로만들기 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static int[] d;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		d = new int[N + 1];
	}

//	private static int bt(int x){
//		if(x == 1 || d[x] != 0)     return d[x];
//		int ret = Integer.MAX_VALUE;
//		if(x % 3 == 0)              ret = Math.min(dp(x/3) + 1, ret);
//		if(x % 2 == 0)              ret = Math.min(dp(x/2) + 1, ret);
//		return d[x] = Math.min(dp(x - 1) + 1, ret);
//	}

	private static int dp(int x){
		for(int i = 2; i <= x; i++){
			int temp = d[i - 1] + 1;
			if(i % 3 == 0)    temp = Math.min(d[i/3] + 1, temp);
			if(i % 2 == 0)    temp = Math.min(d[i/2] + 1, temp);
			d[i] = temp;
		}
		return d[x];
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(dp(N));
	}
}
