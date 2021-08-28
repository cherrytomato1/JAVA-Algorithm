package boj.boj0174_210825_9376_p5_탈옥;

import java.util.*;
import java.io.*;

public class 백준_9376번_탈옥실패 {

	private static class Node implements Comparable<Node>{
		int row;
		int col;
		int wallCount;

		public Node(int row, int col, int wallCount) {
			this.row = row;
			this.col = col;
			this.wallCount = wallCount;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.wallCount, o.wallCount);
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Node{");
			sb.append("row=").append(row);
			sb.append(", col=").append(col);
			sb.append(", wallCount=").append(wallCount);
			sb.append('}');
			return sb.toString();
		}
	}

	private static char[][] map;
	private static boolean[][] visited;

	private static int N;
	private static int M;
	private static int target;

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException{
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t ; i++) {
			init();
			System.out.println(solve());
		}
	}

	private static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];
		visited = new boolean[N][M];
		target = 2;

		for (int r = 0; r < N ; r++) {
			map[r] = br.readLine().toCharArray();
		}
	}

	private static int solve() {
//		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
//		Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

		final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
		final int ROW = 0;
		final int COL = 1;

		Queue<Node> pq = new PriorityQueue<>();
		if (addStartPosition(pq)) {
			return 0;
		}
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			System.out.println(curr);
			for (int i = 0; i < 4; i++) {
				int nr = curr.row + DIR[ROW][i];
				int nc = curr.col + DIR[COL][i];
				if (move(pq, nr, nc, curr.wallCount)) {
					return curr.wallCount;
				}
			}
		}
		return -1;
	}

	private static boolean addStartPosition(Queue<Node> pq) {
		for(int i = 0 ; i < 4; i++) {
			boolean isRow = i % 2 == 0;
			boolean isMaxIndex = i < 2;
			for (int j = 0, size = isRow ? N : M; j < size; j++) {
				int row = isRow ? j : (isMaxIndex ? N - 1 : 0);
				int col = isRow ? (isMaxIndex ? M - 1 : 0) : j;
				if (move(pq, row, col, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean move(Queue<Node> pq, int row, int col, int wallCount) {
		if (!isMovable(row, col)) {
			return false;
		}
		visited[row][col] = true;
		switch (map[row][col]) {
			case '#':
				wallCount++;
				break;
			case '$':
				map[row][col] = '.';
				if (--target == 0) {
					return true;
				}
		}
		pq.offer(new Node(row, col, wallCount));
		return false;
	}

	private static boolean isMovable(int row, int col) {
		if (row < 0 || row >= N || col < 0 || col >= M) {
			return false;
		}

		return map[row][col] != '*' && !visited[row][col];
	}
}
