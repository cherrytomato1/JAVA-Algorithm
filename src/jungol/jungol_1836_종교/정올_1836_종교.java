package jungol.jungol_1836_종교;

import java.util.*;
import java.io.*;

public class 정올_1836_종교 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int M;

	private static int[] parents;
	private static int cnt;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		for(int i = 0 ; i <= N; i++){
			parents[i] = i;
		}
		cnt = N;
	}

	private static int find(int x){
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}

	private static boolean union(int x, int y){
		if(x > y){
			int temp = x ;
			x = y;
			y = temp;
		}
		int parX = find(x);
		int parY = find(y);
		if(parX == parY)    return false;
		parents[parY] = parX;
		cnt--;
		return true;
	}

	private static int solve() throws IOException{
		for(int i = 0 ; i < M ; i++){
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int cnt = 0;
		return cnt;
	}

	public static void main(String[] args) throws IOException{
		init();
		solve();
		System.out.println(cnt);
	}


}
