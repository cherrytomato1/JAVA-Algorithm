package boj.boj0105_210412_1197_g4;

import java.util.*;
import java.io.*;

public class 백준_1197번_최소스패닝트리 {
	private static class Node implements Comparable<Node>{
		boolean visited;
		int weight;
		List<Edge> edges = new ArrayList<>();

		@Override
		public int compareTo(Node n){
			return Integer.compare(this.weight, n.weight);
		}
	}
	private static class Edge{
		int to;
		int weight;
		public Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int V;
	private static int E;

	private static Node[] nodes;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		nodes = new Node[V + 1];
		for (int i = 1; i <= V; i++) {
			nodes[i] = new Node();
			nodes[i].weight = Integer.MAX_VALUE;
		}

		for (int i = 0; i < E ; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			nodes[from].edges.add(new Edge(to, weight));
			nodes[to].edges.add(new Edge(from, weight));
		}
	}

	private static long prim(int start){
		long ret = 0;

		Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
		nodes[start].weight = 0;
		pq.offer(new int[]{start, 0});

		for (int i = 0; i < V ; i++) {
			Node curr ;
			do{
				int[] temp = pq.poll();
				curr = nodes[temp[0]];
			}while(curr.visited);

			curr.visited = true;
			ret += curr.weight;

			for(Edge e : curr.edges){
				Node target = nodes[e.to];
				if(target.visited || e.weight >= target.weight)     continue;
				target.weight = e.weight;
				pq.offer(new int[]{e.to, target.weight});
			}
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(prim(1));
	}
}
