package boj.boj0168_210721_1238_g3;

import java.util.*;
import java.io.*;


public class 백준_1238번_파티 {
	private static class Edge implements Comparable<Edge> {
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.weight, e.weight);
		}
	}
	private static class Node {
		int[] minWeights;
		List<Edge> edgeList;

		public Node(int maxSize) {
			this.minWeights = new int[maxSize + 1];
			this.edgeList = new ArrayList<>();

			for (int i = 1; i <= maxSize ; i++) {
				minWeights[i] = Integer.MAX_VALUE;
			}
		}
	}

	private static Node[] nodes;
	private static int N;


	public static void main(String[] args) throws IOException{
		int X = init();
		System.out.println(solve(X));
	}

	private static int init() throws IOException {
		InputStream in = System.in;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		nodes = new Node[N + 1];
		for (int i = 1; i <= N ; i++) {
			nodes[i] = new Node(N);
		}
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			nodes[from].edgeList.add(new Edge(to, weight));
		}
		return X;
	}

	private static int solve(int X) {
		int ret = 0;
		for (int i = 1; i <= N ; i++) {
			int val = dijkstra(i, X, false);
			val += dijkstra(X, i, true);
			
			ret = Math.max(val, ret);
		}
		return ret;
	}

	private static int dijkstra(int start, int end, boolean flag) {
		Node target = nodes[start];
		if(flag && target.minWeights[end] != Integer.MAX_VALUE) return target.minWeights[end];

		Queue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			if (curr.to == end && !flag)     return curr.weight;
			if (target.minWeights[curr.to] < curr.weight)   continue;

			for (Edge e : nodes[curr.to].edgeList) {
				if(target.minWeights[e.to] < target.minWeights[curr.to] + e.weight) continue;
				target.minWeights[e.to] = curr.weight + e.weight;
				pq.offer(new Edge(e.to, target.minWeights[e.to]));
			}
		}
		return -1;
	}
}
