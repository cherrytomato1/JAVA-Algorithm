package boj.boj0098_210409_14888_s1;

import java.util.*;
import java.io.*;

public class 백준_14888번_연산자끼워넣기 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;


	private static int N;
	private static int[] numbers;
	private static int[] operCounters;

	private static int max = Integer.MIN_VALUE;
	private static int min = Integer.MAX_VALUE;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());

		numbers = new int[N];
		operCounters = new int[4];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N ; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < 4 ; i++){
			operCounters[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void perm(int idx, int sum){
		if(idx == N){
			max = Math.max(sum, max);
			min = Math.min(sum, min);
			return;
		}

		for(int i = 0; i < 4; i++){
			if(operCounters[i] == 0)    continue;
			operCounters[i]--;
			perm(idx + 1, calc(sum, numbers[idx], i));
			operCounters[i]++;
		}
	}

	private static int calc(int a, int b, int op){
		switch (op){
			case 0 :
				return a + b;
			case 1 :
				return a - b;
			case 2 :
				return a * b;
			default:
				return a / b;
		}
	}

	public static void main(String[] args) throws IOException{
		init();
		perm(1, numbers[0]);
		System.out.println(max);
		System.out.println(min);
	}
}
