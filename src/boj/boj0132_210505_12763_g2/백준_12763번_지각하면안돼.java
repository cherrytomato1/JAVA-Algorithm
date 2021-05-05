package boj.boj0132_210505_12763_g2;

import java.util.*;
import java.io.*;

public class 백준_12763번_지각하면안돼 {
	private static class Vertex{
		int minTime;
		int minMoney;

		List<Edge> edges;
		public Vertex() {
			edges = new ArrayList<>();
			minTime = Integer.MAX_VALUE;
			minMoney = Integer.MAX_VALUE;
		}
	}

	private static class Edge {
		int to;
		int time;
		int money;

		public Edge (int to, int times, int money){
			this.to = to;
			this.money = money;
			this.time = times;
		}
	}

	private static int N;
	private static int M;
	private static int T;

	private static final int MONEY = 0;
	private static final int TIME = 1;
	private static final int VERTEX_INDEX = 2;

	private static Vertex[] vertexes;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(dijkstra(1));
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		vertexes = new Vertex[N + 1];

		st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N ; i++) {
			vertexes[i] = new Vertex();
		}

		for (int i = Integer.parseInt(br.readLine()); i > 0  ; i--) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int times = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());

			vertexes[from].edges.add(new Edge(to, times, money));
			vertexes[to].edges.add(new Edge(from, times, money));
		}
	}

	private static int dijkstra(int start) {
		Queue<int[]> pq = new PriorityQueue<>( (o1, o2) -> o1[MONEY] - o2[MONEY] );
		vertexes[start].minTime = 0;
		vertexes[start].minMoney = 0;
		pq.offer(new int[]{0, 0, start});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if(cur[VERTEX_INDEX] == N)      return cur[MONEY];
			for (Edge e : vertexes[cur[VERTEX_INDEX]].edges) {
				Vertex target = vertexes[e.to];

				int nextMoney = cur[MONEY] + e.money;
				int nextTime = cur[TIME] + e.time;

				if(nextMoney > M || nextTime > T)   continue;
				if(target.minMoney < nextMoney && target.minTime < nextTime)   continue;

				target.minMoney = Math.min(target.minMoney, nextMoney);
				target.minTime = Math.min(target.minTime, nextTime);
				pq.offer(new int[]{nextMoney, nextTime, e.to});
			}
		}
		return -1;
	}
}
