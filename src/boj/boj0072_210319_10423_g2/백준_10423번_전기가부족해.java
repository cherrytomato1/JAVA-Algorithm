package boj.boj0072_210319_10423_g2;

import java.util.*;
import java.io.*;

public class 백준_10423번_전기가부족해 {

	private static class Edge implements Comparable{
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Object o){
			return Integer.compare(this.weight, ((Edge)o).weight);
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int M;
	private static int K;

	private static int[] p;
	private static Queue<Edge> edges = new PriorityQueue<>();

	private static int find(int x){
		if(p[x] == x)   return x;
		return p[x] = find(p[x]);
	}

	private static boolean union(int x, int y){
		int parX = find(x);
		int parY = find(y);
		if(parX == parY)    return false;
		p[parY] = parX;
		return true;
	}

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		for(int i = 1 ; i <= N; i++){
			p[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		int prev = Integer.parseInt(st.nextToken());
		for(int i = 1 ; i < K ; i++){
			union(prev, Integer.parseInt(st.nextToken()));
		}

		for(int i = 0 ; i < M ; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges.add(new Edge(from, to, weight));
		}
	}

	private static int solve(){
		int ret = 0;
		int cnt = 0;
		while(!edges.isEmpty() && cnt != N){
			Edge curr = edges.poll();
			if(union(curr.from, curr.to)){
				ret += curr.weight;
				cnt++;
			}
		}
		return ret;
	}


	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
