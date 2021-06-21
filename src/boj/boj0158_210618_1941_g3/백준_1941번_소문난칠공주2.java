package boj.boj0158_210618_1941_g3;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class 백준_1941번_소문난칠공주2 {
	private static char[][] map;
	private static int res;

	private static final int MAX_MAP = 5;
	private static final int MAX_DEPTH = 7;
	private static final int[][] DIR = {{1, -1, 0, 0}, {0, 0, 1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws IOException{
		init();
		dfs(0, 0, 0, 0);
		System.out.println(res);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[MAX_MAP][];
		for (int i = 0; i < MAX_MAP; i++) {
			map[i] = br.readLine().toCharArray();
		}
	}

	private static void dfs(int depth, int idx, int flag, int countOfY) {
		if (countOfY >= 4)  return;
		if (depth == MAX_DEPTH && isAvailable(flag, idx))   res++;

		for (int i = idx; i < MAX_MAP * MAX_MAP ; i++) {
			if((flag & 1 << i) != 0)    continue;
			dfs(depth + 1, i, flag | 1 << i, countOfY + (map[i / MAX_MAP][i % MAX_MAP] == 'Y' ? 1 : 0));
		}
	}

	private static boolean isAvailable(int flag, int idx) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{idx / MAX_MAP, idx % MAX_MAP});
		int cnt = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			if(++cnt > MAX_DEPTH)       return true;

			for (int i = 0; i < 4 ; i++) {
				int nr = curr[ROW] + DIR[ROW][i];
				int nc = curr[COL] + DIR[COL][i];
				if (nr < 0 || nr >= MAX_MAP || nc < 0 || nc >= MAX_MAP)  continue;
				int nf = 1 << (nr * MAX_MAP + nc);
				if ((flag & nf) == 0)  continue;
				flag &= ~nf;

				queue.offer(new int[]{nr, nc});
			}
		}
		return false;
	}
}
