package boj.boj0158_210618_1941_g3;

import java.io.*;

public class 벡준_1941번_소문난칠공주 {
	private static char[][] map;
	private static boolean[] grouped;
	private static int res;

	private static final int MAX_MAP = 5;
	private static final int MAX_DEPTH = 7;
	private static final int[][] DIR = {{1, -1, 0, 0}, {0, 0, 1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws IOException{
		init();
		solve();
		System.out.println(res);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[MAX_MAP][];
		grouped = new boolean[1 << (MAX_MAP * MAX_MAP)];
		for (int i = 0; i < MAX_MAP; i++) {
			map[i] = br.readLine().toCharArray();
		}
	}

	private static void solve() {
		for (int i = 0; i < MAX_MAP; i++) {
			for (int j = 0; j < MAX_MAP; j++) {
				dfs(i, j, 1, 1 << (i * MAX_MAP + j), 0);
			}
		}
	}

	private static int[] dfs(int row, int col, int depth, int flag, int countOfS) {
		countOfS += map[row][col] == 'S' ? 1 : 0;
//		if (countOfS >= 4)     return;
		if(depth == MAX_DEPTH || row < 0 || row >= MAX_MAP || col < 0 || col >= MAX_MAP)     {
			return new int[]{countOfS, flag};
		}
//		if (depth == 7 && !grouped[flag]) {
//			grouped[flag] = true;
//			res++;
//			System.out.println(flag);
//
//			return;
//		}
		for (int i = 0; i < 4 ; i++) {
			int nr = row + DIR[ROW][i];
			int nc = col + DIR[COL][i];
			int nf = 1 << (nr * MAX_MAP + nc);
			if ((flag & nf) != 0)   continue;

			for (int j = depth + 1; j < MAX_DEPTH ; j++) {
				int[] ret = dfs(nr, nc, j, flag | nf, countOfS);
				flag = ret[1];
				int cnt = ret[0];

				for (int k = 0; k < 4 ; k++) {
					if(i == k) continue;
					nr = row + DIR[ROW][k];
					nc = col + DIR[COL][k];
					nf = 1 << (nr * MAX_MAP + nc);
					if ((flag & nf) != 0)   continue;
					if(cnt + dfs(nr, nc, depth + 1, flag | nf, countOfS)[0] < 4)   continue;
					if(grouped[flag | nf])  continue;
					res++;
					grouped[flag | nf] = true;
				}
			}
		}
		return new int[]{countOfS, flag};
	}


}
