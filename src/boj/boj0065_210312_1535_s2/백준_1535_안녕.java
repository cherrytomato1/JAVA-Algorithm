package boj.boj0065_210312_1535_s2;

import java.io.*;
import java.util.*;

public class 백준_1535_안녕 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[][] val;

	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());

		val = new int[N][2];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
			val[i][0] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
			val[i][1] = Integer.parseInt(st.nextToken());
		}
	}

	static int max;
	static void recur(int idx, int health, int happiness){
		if(health <= 0) return;

		max = max >= happiness ? max : happiness;
		for(int i = idx; i < N ; i++){
			recur(i + 1, health - val[i][0], happiness + val[i][1]);
		}
	}

	public static void main(String[] args) throws IOException{
		init();
		recur(0,100,0);
		System.out.println(max);
	}

}
