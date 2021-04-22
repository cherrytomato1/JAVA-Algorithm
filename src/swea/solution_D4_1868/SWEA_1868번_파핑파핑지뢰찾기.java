package swea.solution_D4_1868;

import java.util.*;
import java.io.*;

public class SWEA_1868번_파핑파핑지뢰찾기 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static boolean[][] map;
	private static int[][] countMineMap;
	private static int mines;
	private static int remains;
	private static int N;

	private static final int[][] DIR = {{-1, -1, -1, 0, 0, 1, 1, 1}, {-1, 0, 1, -1, 1, -1, 0, 1}};

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T ; tc++) {
			init();
			sb.append("#").append(tc).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		countMineMap = new int[N][N];
		mines = 0;
		for (int r = 0; r < N; r++) {
			char[] in = br.readLine().toCharArray();
			for (int c = 0; c < N ; c++) {
				if(in[c] != '*')    continue;
				map[r][c] = true;
				mines++;
			}
		}
		remains = N * N - mines;
	}

	private static int solve() {
		int ret = 0;
		int countZero = setCountMineMap();
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < N ; c++) {
				if(countZero <= 0)  return ret + remains;
				if(countMineMap[r][c] != 0) continue;
				countZero -= bfs(r, c);
				ret++;
			}
		}
		return ret + remains;
	}

	private static int setCountMineMap() {
		int ret = 0;
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < N ; c++) {
				if(map[r][c])   {
					countMineMap[r][c] = -1;
					continue;
				}
				countMineMap[r][c] = countMine(r, c);
				ret += countMineMap[r][c] == 0 ? 1 : 0;
			}
		}
		return ret;
	}

	private static int countMine(int row, int col) {
		int ret = 0;
		for (int i = 0; i < 8 ; i++) {
			int nr = row + DIR[0][i];
			int nc = col + DIR[1][i];
			if(nr >= N || nr < 0 || nc >= N || nc < 0)  continue;
			if(map[nr][nc]) ret++;
		}
		return ret;
	}

	private static int bfs(int row, int col) {
		Queue<int[]> q = new ArrayDeque<>();
		countMineMap[row][col] = -1;
		q.offer(new int[]{row, col});

		int ret = 1;
		remains--;
		while (!q.isEmpty()) {
			int[] pos = q.poll();
			for (int i = 0; i < 8 ; i++) {
				int nr = pos[0] + DIR[0][i];
				int nc = pos[1] + DIR[1][i];

				if (nr >= N || nr < 0 || nc >= N || nc < 0 || countMineMap[nr][nc] == -1) continue;
				if (countMineMap[nr][nc] == 0) {
					q.offer(new int[]{nr, nc});
					ret++;
				}
				remains--;
				countMineMap[nr][nc] = -1;
			}
		}
		return ret;
	}
}
