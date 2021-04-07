package boj.boj0094_210408_4811_g5;

import java.io.*;

public class 백준_4811번_알약 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static long[] d;

	private static int init() throws IOException{
		N = Integer.parseInt(br.readLine());
		d = new long[N + 1];
		return N;
	}
	private static long solve(){
		d[0] = 1;
		for(int i = 1; i <= N; i++){
			for(int j = 0; j < i ; j++){
				d[i] += d[j] * d[i - 1 - j];
			}
		}
		return d[N];
	}

	public static void main(String[] args) throws IOException{
		while(init() != 0){
			System.out.println(solve());
		}
	}
}
