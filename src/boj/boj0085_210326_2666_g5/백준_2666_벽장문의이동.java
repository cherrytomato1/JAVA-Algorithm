package boj.boj0085_210326_2666_g5;

import java.util.*;
import java.io.*;

public class 백준_2666_벽장문의이동 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int[] numbers;
	private static int idxMax;
	private static int[] opened;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		opened = new int[2];

		st = new StringTokenizer(br.readLine());
		opened[0] = Integer.parseInt(st.nextToken()) - 1;
		opened[1] = Integer.parseInt(st.nextToken()) - 1;

		idxMax = Integer.parseInt(br.readLine());
		numbers = new int[idxMax];

		for(int i = 0 ; i < idxMax; i++){
			numbers[i] = Integer.parseInt(br.readLine()) - 1;
		}
	}

	static int res = Integer.MAX_VALUE;
	private static void dfs(int idx, int cnt, int open1, int open2){
		if(idx == idxMax){
			res = Math.min(res, cnt);
			return ;
		}
		dfs(idx + 1, cnt + Math.abs(open1 - numbers[idx]), numbers[idx], open2);
		dfs(idx + 1, cnt + Math.abs(open2 - numbers[idx]), open1, numbers[idx]);
	}

	public static void main(String[] args) throws IOException{
		init();
		dfs(0, 0, opened[0], opened[1]);
		System.out.println(res);
	}
}

