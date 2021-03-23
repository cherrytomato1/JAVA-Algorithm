package jungol.jungol_1681_해밀턴순환회로;

import java.util.*;
import java.io.*;

public class 정올_1681_해밀턴순환회로 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int[][] arr;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine().trim());

		arr = new int[N][N];

		for(int r = 0 ; r < N; r++){
			st = new StringTokenizer(br.readLine().trim(), " ");
			for(int c = 0 ; c < N; c++){
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static int res = Integer.MAX_VALUE;
	private static void recur(int idx, int flag, int prev, int weight){
		if(weight >= res)   return;

		if(idx == N){
			if(arr[prev][0] == 0)   return;
			weight += arr[prev][0];
//			System.out.println(prev + " " + weight);
			res = Math.min(res, weight);
			return;
		}

		for(int i = 1 ; i < N ; i++){
			if((flag & 1 << i) != 0 || arr[prev][i] == 0)    continue;
			recur(idx + 1, flag | 1 << i, i,  weight + arr[prev][i]);
		}
	}

	public static void main(String[] args) throws IOException{
		init();
		recur(1, 0, 0, 0);
		System.out.println(res);
	}
}
