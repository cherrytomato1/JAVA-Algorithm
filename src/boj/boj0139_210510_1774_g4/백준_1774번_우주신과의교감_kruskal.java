package boj.boj0139_210510_1774_g4;

import java.util.*;
import java.io.*;

public class 백준_1774번_우주신과의교감_kruskal {
	private static class Edge implements Comparable<Edge>{
		int from;
		int to;
		double weight;

		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge e) {
			return Double.compare(this.weight, e.weight);
		}
	}

	private static List<Edge> edges;
	private static int[] p;
	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException{
		init();
		System.out.printf("%.2f", solve());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] gods = new int[N][2];
		p = new int[N];
		edges = new ArrayList<>();

		for (int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			gods[i][0] = Integer.parseInt(st.nextToken());
			gods[i][1] = Integer.parseInt(st.nextToken());
			p[i] = i;
		}

		for (int i = 0; i < N ; i++) {
			for (int j = i + 1; j < N ; j++) {
				edges.add(new Edge(i, j, getDist(gods[i], gods[j])));
			}
		}

		for (int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			edges.add(new Edge(from, to, 0.0));
		}
	}

	private static double getDist(int[] x, int[] y) {
		double val1 = Math.pow(x[0] - y[0], 2);
		double val2 = Math.pow(x[1] - y[1], 2);
		return Math.sqrt(val1 + val2);
	}

	private static double solve() {
		Collections.sort(edges);
		double ret = 0;

		int cnt = N - 1;
		for (Edge e : edges) {
			if(!union(e.from, e.to))    continue;
			ret += e.weight;
			if(--cnt == 0)          break;
		}
		return ret;
	}

	private static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px == py)    return false;
		p[py] = px;
		return true;
	}

	private static int find(int x) {
		if(p[x] == x)   return x;
		return p[x] = find(p[x]);
	}
}