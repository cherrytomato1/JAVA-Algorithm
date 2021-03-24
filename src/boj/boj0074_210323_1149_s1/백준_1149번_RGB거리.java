package boj.boj0074_210323_1149_s1;

import java.io.*;
import java.util.*;

public class 백준_1149번_RGB거리 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st ;

	private static int N;
	private static int[][] d;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		d = new int[N][3];

		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j = 0 ; j < 3 ; j++){
				d[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int solve(){
		for(int i = 1; i < N; i++){
			for(int j = 0 ; j < 3; j++){
				int min = Integer.MAX_VALUE;
				for(int k = 0 ; k < 3; k ++){
					if(k == j)  continue;
					min = Math.min(d[i - 1][k] + d[i][j], min);
				}
				d[i][j] = min;
			}
		}
		int ret = Integer.MAX_VALUE;
		for(int i = 0 ; i < 3; i++){
			ret = Math.min(d[N - 1][i], ret);
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
