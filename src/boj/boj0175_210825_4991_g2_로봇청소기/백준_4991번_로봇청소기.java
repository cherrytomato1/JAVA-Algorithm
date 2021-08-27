package boj.boj0175_210825_4991_g2_로봇청소기;

import java.util.*;
import java.io.*;

public class 백준_4991번_로봇청소기 {
	private static int N;
	private static int M;

	private static char[][] map;
	private static boolean[][] visited;

	private static List<int[]> targetList;
	private static int[][] adjArray;

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		while (init()) {
			sb.append(solve()).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		if ((M = Integer.parseInt(st.nextToken())) == 0) {
			return false;
		}
		N = Integer.parseInt(st.nextToken());

		targetList = new ArrayList<>();

		map = new char[N][M];
		visited = new boolean[N][M];
		int targetCount = 0;

		for (int r = 0; r < N; r++) {
			char[] in = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				map[r][c] = in[c];
				if (in[c] == '*') {
					map[r][c] = (char)(++targetCount + '0');
					targetList.add(new int[]{r, c, targetCount});
				} else if (in[c] == 'o') {
					map[r][c] = '0';
					targetList.add(new int[]{r, c, 0});
				}
			}
		}

		adjArray = new int[targetCount + 1][targetCount + 1];
		return true;
	}

	private static void printMap() {
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < M ; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}

	}

	private static int solve() {
		for (int[] target : targetList) {
			if (!setMinDist(target)) {
				return -1;
			}
		}
		return perm(1, 0, 0);
	}

	private static boolean setMinDist(int[] startTarget) {
		Queue<int[]> q = new ArrayDeque<>();
		visited = new boolean[N][M];
		int dist = 0;
		int targetCount = 0;
		q.offer(startTarget);

		while (!q.isEmpty()) {
			dist++;
			for (int size = q.size(); size > 0; size--) {
				int[] pos = q.poll();
				targetCount += move(startTarget[2], dist, pos, q);
			}
		}

		return targetCount == targetList.size() -1;
	}

	private static int move(int startIdx, int dist, int[] pos, Queue<int[]> q) {
		final int[][] DIR = {{1, -1, 0, 0}, {0, 0, 1, -1}};
		final int ROW = 0;
		final int COL = 1;

		int ret = 0;

		for (int i = 0; i < 4; i++) {
			int nr = pos[ROW] + DIR[ROW][i];
			int nc = pos[COL] + DIR[COL][i];

			if (nr >= N || nr < 0 || nc >= M || nc < 0
					    || map[nr][nc] == 'x' || visited[nr][nc]) {
				continue;
			}
			if (map[nr][nc] != '.' && map[nr][nc] != '0' + startIdx) {
				adjArray[startIdx][map[nr][nc] - '0'] = dist;
				ret++;
			}
			q.offer(new int[]{nr, nc});
			visited[nr][nc] = true;
		}
		return ret;
	}

	private static int perm(int flag, int prev, int value) {
		if ((1 << targetList.size()) - 1 == flag) {
			return value;
		}
		int ret = Integer.MAX_VALUE;

		for (int[] target : targetList) {
			int idx = target[2];

			if ((flag & 1 << idx) != 0) {
				continue;
			}
			ret = Math.min(perm(flag | 1 << idx, idx, value + adjArray[prev][idx]), ret);
		}
		return ret;
	}
}

