package boj.boj0117_210419_1043_g4;

import java.util.*;
import java.io.*;

public class 백준_1043번_거짓말 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int[] p;
	private static int[][] parties;
	private static int N;
	private static int M;

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		p = new int[N + 1];
		parties = new int[M][];

		for (int i = 1; i <= N ; i++) {
			p[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0, size = Integer.parseInt(st.nextToken()); i < size ; i++) {
			p[Integer.parseInt(st.nextToken())] = 0;
		}

		for (int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			parties[i] = new int[Integer.parseInt(st.nextToken())];
			parties[i][0] = Integer.parseInt(st.nextToken());

			for (int j = 1, size = parties[i].length; j < size ; j++) {
				parties[i][j] = Integer.parseInt(st.nextToken());
				union(parties[i][j - 1], parties[i][j]);
			}
		}
	}

	private static void union(int x, int y){
		int px = find(p[x]);
		int py = find(p[y]);
		if(px == py)    return;
		if(py == 0)     p[px] = py;
		else            p[py] = px;
	}

	private static int find(int x) {
		if(x == p[x] || x == 0) return x;
		return p[x] = find(p[x]);
	}

	private static int solve() {
		int ret = 0;
		for (int i = 0; i < M ; i++) {
			boolean flag = false;
			for (int j = 0, size = parties[i].length; j < size ; j++) {
				if(find(parties[i][j]) == 0)    flag = true;
			}
			if(!flag) ret++;
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

}
