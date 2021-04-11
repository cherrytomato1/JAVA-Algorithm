package boj.boj00100_210411_2644_s2;

import java.io.*;
import java.util.*;

public class 백준_2644번_촌수계산 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int[] targets;
	private static int[] p;

	private static void init() throws IOException{
		targets = new int[2];
		N = Integer.parseInt(br.readLine());
		p = new int[N + 1];
		for (int i = 1; i <= N ; i++) {
			p[i] = i;
		}
		st = new StringTokenizer(br.readLine());
		targets[0] = Integer.parseInt(st.nextToken());
		targets[1] = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			p[y] = x;
		}
	}

	private static int find(int x, int y, int cnt){
		if(x == y)      return cnt;
		int ret = Integer.MAX_VALUE;
		if(p[y] != y)   ret = find(x, p[y], cnt + 1);
		if(p[x] != x)   ret = Math.min(find(p[x], y, cnt + 1), ret);
		return ret;
//		return p[y] != y ? find(x, p[y], cnt + 1)  : x != p[x] ? find(p[x], y, cnt + 1) : -1;
	}

	public static void main(String[] args) throws IOException{
		init();
		int res = find(targets[0], targets[1], 0 );
		System.out.println(res == Integer.MAX_VALUE ? -1 : res);
	}
}
