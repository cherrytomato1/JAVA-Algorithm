package boj.boj0141_210514_1261_g4;

import java.util.*;
import java.io.*;

public class 백준_1261번_알고스팟 {
	private static class Edge {
		int row;
		int col;
		int weight;

		public Edge(int row, int col, int weight) {
			this.row = row;
			this.col = col;
			this.weight = weight;
		}
	}
	private static int N;
	private static int M;
	private static boolean[][] map;
	private static int[][] visited;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N ; i++) {
			char[] in = br.readLine().toCharArray();
			for (int j = 0; j < M ; j++) {
				map[i][j] = in[j] == '1';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	private static int solve() {
		Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> ( o1.weight - o2.weight));
		pq.offer(new Edge(0, 0, 0));
		visited[0][0] = 0;

		final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
		final int ROW = 0;
		final int COL = 1;

		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			if(curr.row == N - 1 && curr.col == M - 1)  return curr.weight;
			for (int i = 0; i < 4 ; i++) {
				int nr = curr.row + DIR[ROW][i];
				int nc = curr.col + DIR[COL][i];
				if(nr < 0 | nr >= N | nc < 0 | nc >= M )    continue;

				int nw = curr.weight + (map[nr][nc] ? 1 : 0);
				if(visited[nr][nc] <= nw)  continue;
				visited[nr][nc] = nw;
				pq.offer(new Edge(nr, nc, nw));
			}
		}

		return -1;
	}
}
