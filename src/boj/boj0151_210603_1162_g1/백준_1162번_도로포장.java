package boj.boj0151_210603_1162_g1;

import java.util.*;
import java.io.*;

public class 백준_1162번_도로포장 {
	private static class Vertex {
		long[] minEdge;
		List<Edge> edgeList;

		public Vertex(int K) {
			minEdge = new long[K + 1];
			for (int i = 0; i <= K ; i++) {
				minEdge[i] = Long.MAX_VALUE;
			}
			edgeList = new ArrayList<>();
		}

		public void setMinEdge(int count, long weight) {
			for (int i = 0; i <= count ; i++) {
				minEdge[i] = Math.min(weight, minEdge[i]);
			}
		}
	}

	private static class Edge {
		int to;
		long weight;
		int count;

		public Edge(int to, long weight) {
			this.to = to;
			this.weight = weight;
		}

		public Edge(int to, long weight, int count) {
			this.to = to;
			this.weight = weight;
			this.count = count;
		}
	}

	private static Vertex[] vertexes;
	private static int N;

	public static void main(String[] args) throws IOException{
		System.out.println(solve(init()));
	}

	private static int init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		vertexes = new Vertex[N + 1];
		int V = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N ; i++) {
			vertexes[i] = new Vertex(K);
		}

		while (V-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			vertexes[from].edgeList.add(new Edge(to, weight));
			vertexes[to].edgeList.add(new Edge(from, weight));
		}
		return K;
	}

	private static long solve(int K) {
		Queue<Edge> pq = new PriorityQueue<>( Comparator.comparingLong(o -> o.weight));
		vertexes[1].setMinEdge(K, 0);
		pq.offer(new Edge(1, 0, K));

		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			//다른 경로가 해당 정점을 초기화해서 탐색할 가치가 없어진 경우
			if(vertexes[curr.to].minEdge[curr.count] < curr.weight)    continue;
			if(curr.to == N)    return curr.weight;

			for (Edge e : vertexes[curr.to].edgeList) {
				long nw = e.weight + curr.weight;
				Vertex target = vertexes[e.to];

				if(target.minEdge[curr.count] > nw) {
					target.setMinEdge(curr.count, nw);
					pq.offer(new Edge(e.to, nw, curr.count));
				}

				if(curr.count <= 0 || target.minEdge[curr.count - 1] <= curr.weight)  continue;
				target.setMinEdge(curr.count - 1, curr.weight);
				pq.offer(new Edge(e.to, curr.weight, curr.count - 1));
			}
		}
		return -1;
	}
}
