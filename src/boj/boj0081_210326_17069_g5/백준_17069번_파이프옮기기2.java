package boj.boj0081_210326_17069_g5;

import java.util.*;
import java.io.*;

public class 백준_17069번_파이프옮기기2 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final int[][] DIR = {{-1, 0}, {0, -1}, {-1, -1}};

	private static StringTokenizer st;
	private static int N;
	private static boolean[][] map;
	private static long[][][] d;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		d = new long[N][N][3];
		for(int r = 0 ; r < N; r++){
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < N ; c++){
				map[r][c] = st.nextToken().equals("1");
			}
		}
		d[0][1][1] = 1;
	}

	private static long dp(){
		for(int r = 0 ; r < N; r++){
			for(int c = 0 ; c < N; c++){
				getCount(r, c);
			}
		}
		long ret = 0;
		for(int i = 0 ; i < 3; i++){
			ret += d[N - 1][N - 1][i];
		}

		return ret;
	}

	private static void getCount(int r, int c){
		if(map[r][c])   return;
		for(int i = 0 ; i < 2; i++){
			int pr = r + DIR[i][0];
			int pc = c + DIR[i][1];
			if(pr < 0 || pc < 0 || map[pr][pc]) continue;
			d[r][c][i] += d[pr][pc][i];
			d[r][c][i] += d[pr][pc][2];
		}

		boolean check = true;
		for(int i = 0 ; i < 3; i++){
			int pr = r + DIR[i][0];
			int pc = c + DIR[i][1];
			if(pr < 0 || pc < 0 || map[pr][pc]) {
				check = false;
				break;
			}
		}
		if(check) {
			for(int i = 0; i < 3; i ++) {
				d[r][c][2] += d[r + DIR[2][0]][c + DIR[2][1]][i];
			}
		}

	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(dp());
	}
}
