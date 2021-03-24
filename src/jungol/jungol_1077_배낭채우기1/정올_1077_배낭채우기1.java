package jungol.jungol_1077_배낭채우기1;

import java.util.*;
import java.io.*;

public class 정올_1077_배낭채우기1 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int W;
	private static int[] d;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		d = new int[W + 1];
	}

	private static int solve() throws IOException{
		for(int i = 0 ; i < N; i++){
			st = new StringTokenizer(br.readLine().trim());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			for(int j = weight; j <= W ; j++){
				d[j] = Math.max(d[j - weight] + value, d[j]);
			}
		}
		return d[W];
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
