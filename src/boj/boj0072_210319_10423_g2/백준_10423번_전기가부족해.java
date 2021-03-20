package boj.boj0072_210319_10423_g2;

import algorithm.MST1_Kruskal;

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

	private static boolean[] cities;
	private static int[] p;
	private static List<Edge> edges = new ArrayList<>();

	private static int find(int x){
		if(p[x] == x)   return x;
		return p[x] = find(p[x]);
	}

	private static boolean union(int x, int y){
		int parX = find(x);
		int parY = find(y);
		if(cities[parX] && cities[parY])    return false;
		p[parY] = parX;
		return true;
	}

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		cities = new boolean[N + 1];
		for(int i = 1 ; i <= N; i++){
			p[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < K ; i++){
			cities[Integer.parseInt(st.nextToken())] = true;
		}

		for(int i = 0 ; i < M ; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges.add(new Edge(from, to, weight));
		}
		Collections.sort(edges);
	}

	private static int solve(){
		int cnt = 0;
		int ret = 0;
		for(Edge edge : edges){
			if(union(edge.from, edge.to)){
				ret += edge.weight;
				if(++cnt == N) break;
			}
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
