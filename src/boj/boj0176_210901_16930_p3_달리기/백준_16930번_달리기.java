package boj.boj0176_210901_16930_p3_달리기;

import java.util.*;
import java.io.*;

public class 백준_16930번_달리기 {
	private static class Pos {
		int row;
		int col;
		int weight;

		public Pos(int row, int col, int weight) {
			this.row = row;
			this.col = col;
			this.weight = weight;
		}
	}

	private static char[][] map;
	private static int[][] visited;

	private static int N;
	private static int M;
	private static int K;

	private static Pos end;


	public static void main(String[] args) throws IOException {
		Pos start = init();
		System.out.println(solve(start));
	}

	private static Pos init() throws IOException {
		final InputStream in = System.in;
		final BufferedReader br = new BufferedReader(new InputStreamReader(in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new int[N][M];
		map = new char[N][];

		for (int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
			Arrays.fill(visited[r], Integer.MAX_VALUE);
		}


		st = new StringTokenizer(br.readLine(), " ");
		Pos start = new Pos(Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken()) - 1, 0);

		end = new Pos(Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken()) - 1, 0);

		return start;
	}

	private static int solve(Pos start) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);

		while (!q.isEmpty()) {
			Pos curr = q.poll();
			for (int i = 0; i < 4; i++) {
				if (move(curr, i, q)) {
					return curr.weight + 1;
				}
			}
		}

		return -1;
	}


	private static boolean move(Pos curr, int d, Queue<Pos> q) {
		final int[][] DIR = {{1, -1, 0, 0}, {0, 0, 1, -1}};
		final int ROW = 0;
		final int COL = 1;

		for (int i = 1, size = K; i <= size; i++) {
			int nr = curr.row + DIR[ROW][d] * i;
			int nc = curr.col + DIR[COL][d] * i;

			if (nr == end.row && nc == end.col) {
				return true;
			}

			if (!isMovable(nr, nc) || visited[nr][nc] <= curr.weight) {
				break;
			}

			if (visited[nr][nc] == curr.weight + 1) {
				continue;
			}

			visited[nr][nc] = curr.weight + 1;
			q.offer(new Pos(nr, nc, curr.weight + 1));
		}

		return false;
	}

	private static boolean isMovable(int row, int col) {
		if (row < 0 || row >= N || col < 0 || col >= M) {
			return false;
		}

		return map[row][col] == '.';
	}
}
