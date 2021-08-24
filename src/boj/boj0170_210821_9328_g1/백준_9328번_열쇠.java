package boj.boj0170_210821_9328_g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_9328번_열쇠 {
	private static class Position {
		int row;
		int col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	private static char[][] map;
	private static boolean[][] visited;
	private static int N;
	private static int M;

	private static int res;

	private static InputStream in = System.in;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(in));
	private static StringTokenizer st;

	private static final char WALL = '*';
	private static final char PAPER = '$';

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			init();
			solve();
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		res = 0;
		map = new char[N][];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		char[] startKeys = br.readLine().toCharArray();

		if (startKeys.length == 1 && startKeys[0] == '0') {
			visited = new boolean[N][M];
			return ;
		}

		for (char key : startKeys) {
			doorOpen(key);
		}
	}

	private static int solve() {
		final int ROW = 0;
		final int COL = 1;
		final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

		Queue<Position> q = new ArrayDeque<>();
		addStartPosition(q);

		while (!q.isEmpty()) {
			Position curr = q.poll();

			for (int i = 0; i < 4 ; i++) {
				int nr = curr.row + DIR[ROW][i];
				int nc = curr.col + DIR[COL][i];

				if (nr >= N || nr < 0 || nc >= M || nc < 0) {
					addStartPosition(q);
					continue;
				}
				Position next = new Position(nr, nc);
				move(q, next);
			}
		}
		return res;
	}

	private static void doorOpen(char key) {
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < M ; c++) {
				if (map[r][c] == key - 'a' + 'A') {
					map[r][c] = '.';
				}
			}
		}
		visited = new boolean[N][M];
	}

	private static void addStartPosition(Queue<Position> q) {
		for(int i = 0 ; i < 4; i++) {
			boolean isRow = i % 2 == 0;
			boolean isMaxIndex = i < 2;
			for (int j = 0, size = isRow ? N : M; j < size; j++) {
				int row = isRow ? j : (isMaxIndex ? N - 1 : 0);
				int col = isRow ? (isMaxIndex ? M - 1 : 0) : j;
				Position curr = new Position(row, col);

				move(q, curr);
			}
		}
	}

	private static void move(Queue<Position> q, Position curr) {
		if (!isMovable(curr)) {
			return;
		}
		char targetPosMap = map[curr.row][curr.col];

		if (targetPosMap == PAPER) {
			res++;
			//한번 먹을 수 있는 문서는 어떤경로로 와도 먹을 수 있음
			map[curr.row][curr.col] = '.';
		}
		if (targetPosMap >= 'a' && targetPosMap <= 'z') {
			doorOpen(targetPosMap);
			map[curr.row][curr.col] = '.';
			//매번 큐를 비워주지 않으면 큐에 쌓이는 좌표가 너무 많음
			while (!q.isEmpty()) {
				q.poll();
			}
		}
		visited[curr.row][curr.col] = true;
		q.add(curr);

	}

	private static boolean isMovable(Position position) {
		int row = position.row;
		int col = position.col;

		if (visited[row][col]) {
			return false;
		}

		return map[row][col] != WALL && (map[row][col] > 'Z' || map[row][col] < 'A');
	}
}
