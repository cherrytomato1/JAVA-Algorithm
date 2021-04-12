package boj.boj0102_210411_2502_s1;

import java.io.*;
import java.util.*;

public class 백준_2502_떡먹는호랑이 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int day;
	private static int val;

	private static int[][] d;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		day = Integer.parseInt(st.nextToken());
		val = Integer.parseInt(st.nextToken());
		d = new int[day + 1][2];
		d[1][0] = 1;
		d[2][1] = 1;
	}

	private static int[] dp(int n){
		if(d[n][0] != 0 || d[n][1] != 0)    return d[n];
		d[n][0] = dp(n - 1)[0] + dp(n - 2)[0];
		d[n][1] = dp(n - 1)[1] + dp(n - 2)[1];
		return d[n];
	}

	private static String solve(){
		dp(day);
		int x = d[day][0];
		int y = d[day][1];

//      무작정 구하기
//		for (int i = 1; i * y <= val  ; i++) {
//			for (int j = 1; j <= i ; j++) {
//				if(i * y + j * x == val)    return j + "\n" + i;
//			}
//		}

		for (int i = 1; i <= val ; i++) {
			int day1Sum = x * i;
			int day2Sum = val - day1Sum;
			if(day2Sum % y == 0)    return i + "\n" + day2Sum/y;
		}
		return null;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
