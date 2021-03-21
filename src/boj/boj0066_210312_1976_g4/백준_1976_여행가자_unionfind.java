package boj.boj0066_210312_1976_g4;

import java.util.*;
import java.io.*;

public class 백준_1976_여행가자_unionfind {
	private static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int M;
	private static boolean[][] adjArr;
	private static int[] p;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adjArr = new boolean[N][N];
		p = new int[N + 1];

		//유니온 파인드 부모 Make
		for(int i = 1 ; i <= N; i++){
			p[i] = i;
		}

		for(int r = 0 ; r < N ; r++){
			st = new StringTokenizer(br.readLine());
			for (int c = 0 ; c < N ; c++){
				if(st.nextToken().equals("1"))  union(r + 1, c + 1);
			}
		}
	}

	private static int find(int x){
		if(p[x] == x)   return x;
		return p[x] = find(p[x]);
	}

	private static boolean union(int x, int y){
		int px = find(x);
		int py = find(y);

		if(px == py)   return false;
		p[py] = px;
		return true;
	}

	private static boolean solve() throws IOException{
		st = new StringTokenizer(br.readLine());
		int prev = Integer.parseInt(st.nextToken());
		for(int i = 1 ; i < M ; i++){
			int curr = Integer.parseInt(st.nextToken());
			if(union(prev, curr)) return false;
			prev = curr;
		}
		return true;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve() ? "YES" : "NO");
	}
}
