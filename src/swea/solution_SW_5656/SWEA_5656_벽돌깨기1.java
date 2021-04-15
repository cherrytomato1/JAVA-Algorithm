package swea.solution_SW_5656;

import java.io.*;
import java.util.*;

public class SWEA_5656_벽돌깨기1 {
	private static class Pos{
		int row;
		int col;
		int cnt;
		public Pos(int row, int col){
			this.row = row;
			this.col = col;
		}

		public Pos(int row, int col, int cnt){
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

	private static int N;
	private static int R;
	private static int C;

	private static int[][] init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		int[][] map = new int[R][C];
		res = Integer.MAX_VALUE;

		for (int i = 0; i < R ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return map;
	}

	private static int res = Integer.MAX_VALUE;
	private static void dropBall(int cnt, int[][] map){
		if(cnt == N){
			res = Math.min(res, countBlock(map));
//			printMap(map);
//			System.out.println(res);
			return;
		}
		for (int col = 0; col < C ; col++) {
			int row = 0;
			while(row < R && map[row][col] == 0)   row++;

			int[][] newMap = mapCopy(map);
			if(row != R) {
//				breakBlockBfs(row, col, newMap);
				breakBlockDfs(row, col, newMap);
//				printMap(newMap);
				dropBlock(newMap);
			}
			dropBall(cnt + 1, newMap);
		}
	}

	private static int[][] mapCopy(int[][] map){
		int[][] newMap = new int[R][C];
		for (int i = 0; i < R ; i++) {
			for (int j = 0; j < C ; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}

	private static void printMap(int[][] map) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("map");
	}

	private static int countBlock(int[][] newMap){
		int ret = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C ; j++) {
				if(newMap[i][j] != 0)   ret++;
			}
		}

		return ret;
	}

	private static void breakBlockDfs(int row, int col, int[][] newMap){
		int cnt = newMap[row][col];
		newMap[row][col] = 0;
		if(cnt <= 1)        return;
//		System.out.println(row + " = row, col = " + col);
		for (int i = 0; i < 4 ; i++) {
			for (int j = 1; j < cnt; j++) {
				int nr = DIR[0][i] * j;
				int nc = DIR[1][i] * j;
				if(nr >= R || nr < 0 || nc >= C || nc < 0 || newMap[nr][nc] == 0)  continue;
				breakBlockDfs(nr, nc, newMap);
			}
		}
	}

	private static void breakBlockBfs(int row, int col, int[][] newMap){
		Queue<Pos> q = new ArrayDeque<>();
		if(newMap[row][col] > 1) q.offer(new Pos(row, col, newMap[row][col]));
		newMap[row][col] = 0;

		while (!q.isEmpty()){
			Pos curr = q.poll();
			for (int i = 0; i < 4 ; i++) {
				int nr = curr.row;
				int nc = curr.col;
				for (int j = 0; j < curr.cnt - 1 ; j++) {
					nr += DIR[0][i];
					nc += DIR[1][i];
					if(nr >= R || nr < 0 || nc >= C || nc < 0 || newMap[nr][nc] == 0)  continue;
					if(newMap[nr][nc] > 1)      q.offer(new Pos(row, col, newMap[nr][nc]));
					newMap[nr][nc] = 0;
				}
			}
		}
	}

	private static void dropBlock(int[][] newMap){
		for (int col = 0; col < C ; col++) {
			for (int row = R - 1; row >= 1 ; row--) {
				if (newMap[row][col] != 0)  continue;
				int nr = row - 1;
				while (nr > 0 && newMap[nr][col] != 0) nr--;
				newMap[row][col] = newMap[nr][col];
				newMap[nr][col] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T ; tc++) {
			dropBall(0, init());
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
	}
}
