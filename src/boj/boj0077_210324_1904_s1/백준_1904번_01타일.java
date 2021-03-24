package boj.boj0077_210324_1904_s1;

import java.io.*;

public class 백준_1904번_01타일 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int N;
	private static int[] d;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		d = new int[N + 1];
		if(N >= 1)      d[1] = 1;
		if(N >= 2)      d[2] = 2;
	}

	private static int dp(){
		for(int i = 3 ; i <= N; i++){
			d[i] = (d[i - 1] + d[i - 2]) % 15746;
		}
		return d[N];
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(dp());
	}
}
