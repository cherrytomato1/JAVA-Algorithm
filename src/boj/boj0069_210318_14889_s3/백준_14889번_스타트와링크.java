package boj.boj0069_210318_14889_s3;

import java.util.*;
import java.io.*;

public class 백준_14889번_스타트와링크 {
	private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int[][] arr;

	private static int[] flag;
	private static int[][] team;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		flag = new int[N];
		team = new int[2][N/2];

		for(int r = 0 ; r < N ; r++){
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < N ; c++){
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = N - 1  ; i >= N - N/2 ; i-- ){
			flag[i] = 1;
		}
	}

	private static int solve(){
		int ret = Integer.MAX_VALUE;
		do{
			ret = Math.min(ret, check());
		}while(nextPermutation());
		return ret;
	}

	private static int check(){
		for(int i = 0, j = 0, k = 0; i < N ; i++){
			if(flag[i] == 1)    team[0][j++] = i;
			else                team[1][k++] = i;
		}

		int ret = 0;
		for(int r = 0 ; r < N/2; r++){
			for(int c = 0 ; c < N/2; c++){
				ret += arr[team[0][r]][team[0][c]] - arr[team[1][r]][team[1][c]];
			}
		}
		return Math.abs(ret);
	}

	private static boolean nextPermutation(){
		int i = N - 1;
		while(i > 0 && flag[i-1] >= flag[i]) i--;
		if(i == 0)  return false;

		int j = N - 1;
		while(flag[i - 1] >= flag[j]) j--;
		idxSwap(i - 1, j);

		int k = N - 1;
		while(k > i){
			idxSwap(k--, i++);
		}
		return true;
	}

	private static void idxSwap(int idx1, int idx2){
		int temp = flag[idx1];
		flag[idx1] = flag[idx2];
		flag[idx2] = temp;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());

	}
}
