package boj.boj0091_210328_16562_g3;

import java.util.*;
import java.io.*;

public class 백준_16562번_친구비 {
	private static class Node{
		int parent;
		int val;

		public Node(int parent, int minVal){
			this.parent = parent;
			this.val = minVal;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static Node[] p;
	private static int N;
	private static int M;
	private static int K;

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		p = new Node[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N ; i++) {
			p[i] = new Node(i, Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
	}

	private static int find(int x, int childVal){
		p[x].val = Math.min(p[x].val, childVal);
		if(p[x].parent == x)    return x;
		return p[x].parent = find(p[x].parent, p[x].val);
	}

	private static boolean union(int x, int y){
		if(x > y){
			int temp = x;
			x = y;
			y = temp;
		}
		int px = find(x, p[x].val);
		int py = find(y, p[x].val);

		if(px == py)        return false;
		p[py].parent = px;
		p[px].val = Math.min(p[py].val, p[px].val);
		return true;
	}

	private static int solve() {
		int ret = 0;
		for (int i = 1; i <= N ; i++) {
			if (p[i].parent != i)   continue;
			ret += p[i].val;
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		int res = solve();
		System.out.println(res > K ? "Oh no" : res);
	}
}
