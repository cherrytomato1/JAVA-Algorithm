package boj.boj0174_210825_9376_p5_탈옥;

import java.util.*;
import java.io.*;

public class 백준_9376번_탈옥 {
	private static class Node implements Comparable<Node> {
		int row;
		int col;
		int wallCount;
		int index;

		public Node(int row, int col, int wallCount, int index) {
			this.row = row;
			this.col = col;
			this.wallCount = wallCount;
			this.index = index;
		}

		@Override
		public int compareTo(Node node) {
			return Integer.compare(this.wallCount, node.wallCount);
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Node{");
			sb.append("row=").append(row);
			sb.append(", col=").append(col);
			sb.append(", wallCount=").append(wallCount);
			sb.append(", index=").append(index);
			sb.append('}');
			return sb.toString();
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static char[][] map;
	private static int[][][] wallCounter;

	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException {
		int t = Integer.parseInt(br.readLine());
		while (t-- != 0) {
			Node[] prisoners = init();
//			printMap();
			System.out.println(solve(prisoners));
//			printMap();
		}

//		for (int j = 0; j < 3; j++) {
//			System.out.println( " " + wallCounter[j][2][2]);
//		}
	}

	private static Node[] init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		//테두리 포함
		map = new char[N + 2][M + 2];
		//3개의 탐색경로 별로 각각 저장
		wallCounter = new int[3][N + 2][M + 2];


		for (int i = 0; i < 4; i++) {
			boolean isRow = i % 2 == 0;
			boolean isMaxIndex = i < 2;
			for (int j = 0, size = (isRow ? N : M) + 1; j <= size; j++) {
				int row = isRow ? j : (isMaxIndex ? N + 1 : 0);
				int col = isRow ? (isMaxIndex ? M + 1 : 0) : j;
				for (int k = 0; k < 3; k++) {
					wallCounter[k][row][col] = Integer.MAX_VALUE;
				}
			}
		}

		Node[] prisoners = new Node[2];
		int prisonerIndex = 0;

		for (int r = 1; r <= N; r++) {
			char[] cols = br.readLine().toCharArray();
			for (int c = 1; c <= M; c++) {
				map[r][c] = cols[c - 1];
				for (int i = 0; i < 3; i++) {
					wallCounter[i][r][c] = Integer.MAX_VALUE;
				}
				if (map[r][c] != '$') {
					continue;
				}
				prisoners[prisonerIndex] = new Node(r, c, 0, prisonerIndex++);
			}
		}
		return prisoners;
	}

	private static int solve(Node[] prisoners) {
		final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
		final int ROW = 0;
		final int COL = 1;

		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(prisoners[0]);
		pq.offer(prisoners[1]);
		pq.offer(new Node(0, 0, 0, 2));

		int ret = Integer.MAX_VALUE;

		while (!pq.isEmpty()) {
			Node curr = pq.poll();
//			System.out.println(curr);
			for (int i = 0; i < 4; i++) {
				Node next = new Node(curr.row + DIR[ROW][i], curr.col + DIR[COL][i], curr.wallCount, curr.index);
				if (!move(pq, next)) {
					continue;
				}
				int sum = 0;
				for (int j = 0; j < 3; j++) {
					sum += wallCounter[j][next.row][next.col];
//					System.out.println(wallCounter[j][next.row][next.col]);
				}
//				System.out.println(next.row + " " + next.col);
//				System.out.println(map[next.row][next.col]);

//				ret = Math.min(sum + (map[next.row][next.col] == '#' ? -2 : 0), ret);
//				pq.offer(new Node(next.row, next.col, ))
				return sum + (map[next.row][next.col] == '#' ? -2 : 0);
			}
		}

		return ret;
	}

	private static boolean move(Queue<Node> pq, Node next) {
		int row = next.row;
		int col = next.col;
		if (row > N + 1 || row < 0 || col > M + 1 || col < 0 || map[row][col] == '*') {
			return false;
		}

		if (map[row][col] == '#') {
//			System.out.println("is wall ");
			next.wallCount++;
		}

		if (wallCounter[next.index][row][col] <= next.wallCount) {
			return false;
		}
		wallCounter[next.index][row][col] = next.wallCount;

		pq.offer(next);

		boolean flag = true;
		for (int i = 0; i < 3; i++) {
			flag &= wallCounter[i][row][col] != Integer.MAX_VALUE;
//			System.out.println(wallCounter[next.index][row][col]);
		}
//		System.out.println(flag);
		return flag;
	}

	private static void printMap() {
		for (int r = 0; r <= N + 1 ; r++) {
			for (int c = 0; c <= M + 1 ; c++) {
				System.out.print(wallCounter[2][r][c]);
			}
			System.out.println();
		}
	}
}

/*

1
5 9
****#****
*..#.#..*
****.****
*$#.#.#$*
*********

1
5 11
*#*********
*$*...*...*
*$*.*.*.*.*
*...*...*.*
*********.*

 */
