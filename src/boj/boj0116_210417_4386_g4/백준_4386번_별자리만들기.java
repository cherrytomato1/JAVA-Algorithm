package boj.boj0116_210417_4386_g4;

import java.util.*;
import java.io.*;

public class 백준_4386번_별자리만들기 {
	private static class Vertex{
		int idx;
		double weight;

		public Vertex(int idx, double weight) {
			this.idx = idx;
			this.weight = weight;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static boolean[] visited;
	private static double[] minEdges;

	private static double[][] vertex;
	private static double[][] adj;

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		minEdges = new double[N];
		visited = new boolean[N];
		adj = new double[N][N];
		vertex = new double[N][N];

		for (int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			vertex[i][0] = Double.parseDouble(st.nextToken());
			vertex[i][1] = Double.parseDouble(st.nextToken());
			minEdges[i] = Double.MAX_VALUE;
		}

		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N ; j++) {
				if(i == j)  continue;
				adj[i][j] = getDist(vertex[i], vertex[j]);
			}
		}
	}

	private static double getDist(double[] v1, double[] v2){
		double w = Math.pow(Math.abs(v1[0] - v2[0]), 2);
		double h = Math.pow(Math.abs(v1[1] - v2[1]), 2);
		return Math.sqrt(w + h);
	}

	private static double prim(int start) {
		double ret = 0;

		Queue<Vertex> pq = new PriorityQueue<>((o1, o2) -> (int) (o1.weight - o2.weight));
		minEdges[start] = 0;
		pq.offer(new Vertex(start, minEdges[start]));
		for (int i = 0; i < N ; i++) {
			Vertex curr;
			do{
				curr = pq.poll();
			}while(visited[curr.idx]);
			visited[curr.idx] = true;
			ret += curr.weight;

			for (int j = 0; j < N ; j++) {
				if(visited[j] || adj[curr.idx][j] == 0 || adj[curr.idx][j] >= minEdges[j])    continue;
				minEdges[j] = adj[curr.idx][j];
				pq.offer(new Vertex(j, minEdges[j]));
			}
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		double res = Math.floor(prim(0) * 100) / 100;
		System.out.println(res);
	}
}