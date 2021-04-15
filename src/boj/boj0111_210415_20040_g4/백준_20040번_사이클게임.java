package boj.boj0111_210415_20040_g4;

import java.io.*;
import java.util.*;

public class 백준_20040번_사이클게임 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st ;

	private static int N;
	private static int M;

	private static int[] p;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		p = new int[N];
		for (int i = 0; i < N ; i++) {
			p[i] = i;
		}
	}

	private static int solve() throws IOException {
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			if(!union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())))   return i;
		}
		return 0;
	}

	private static int find(int x){
		if(p[x] == x)   return x;
		return p[x] = find(p[x]);
	}

	private static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px == py)    return false;
		p[py] = px;
		return true;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
