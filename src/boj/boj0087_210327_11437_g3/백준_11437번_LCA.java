package boj.boj0087_210327_11437_g3;

import java.util.*;
import java.io.*;

public class 백준_11437번_LCA {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int M;
	private static int[] p;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		p = new int[N + 1];

		p[1] = 1;
		for(int i = 0 ; i < N - 1 ; i++){
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
//		System.out.println(Arrays.toString(p));
	}

	private static int lowerDepth;
	private static int parent;
	private static String solve() throws IOException{
		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i++){
			st = new StringTokenizer(br.readLine());
			lowerDepth = Integer.MAX_VALUE;
			parent = 1;
			findParent(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			sb.append(parent);
			sb.append("\n");
		}
		return sb.toString();
	}

	private static boolean union(int x, int y){
		if(x > y){
			int temp = x;
			x = y;
			y = temp;
		}
		p[y] = x;
		return true;
	}

	private static void findParent(int x, int y, int depth){
		if(lowerDepth < depth)  return;
		if(x == y) {
			lowerDepth = depth;
			parent = x;
		}
//		System.out.println(x + " " + y);
		if(p[x] != 1)   findParent(p[x], y, depth + 1);
		if(p[y] != 1)   findParent(x, p[y], depth + 1);
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
