package boj.boj0145_210520_16234_g5;

import java.util.*;
import java.io.*;

public class 백준_16234번_인구이동 {
	private static final int ROW = 0;
	private static final int COL = 1;

	private static int[][] map;
	private static int[][] unionMap;
	private static int N;
	private static int L;
	private static int R;

	public static void main(String[] args) throws IOException{
		init();
		int a;
		int b = a = 5 + 5;
		System.out.println(solve());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int solve() {
		int ret = 0;
		while (findUnion()) {
			ret++;
		}
		return ret;
	}

	private static void printMap() {
		System.out.println("map ");
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < N ; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}

		System.out.println("union map ");
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < N ; c++) {
				System.out.print(unionMap[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static boolean findUnion() {
		int unionCnt = 0;
		boolean flag = false;
		unionMap = new int[N][N];
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < N ; c++) {
				if(unionMap[r][c] != 0)   continue;
				if(bfs(r, c,  ++unionCnt))  flag = true;
			}
		}
		return flag;
	}

	private static boolean bfs(int row, int col, int unionCnt) {
		final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{row, col});
		unionMap[row][col] = unionCnt;
		int sum = map[row][col];
		int cnt = 1;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int i = 0; i < 4 ; i++) {
				int nr = curr[ROW] + DIR[ROW][i];
				int nc = curr[COL] + DIR[COL][i];

				if(!isAvailable(curr, nr, nc))    continue;
				unionMap[nr][nc] = unionCnt;
				cnt ++;
				sum += map[nr][nc];
				q.offer(new int[]{nr, nc});
			}
		}
		if(cnt == 1)    return false;
		setMap(sum/cnt, unionCnt);
		return true;
	}

	private static boolean isAvailable(int[] curr, int nr, int nc) {
		if(nr < 0 || nr >= N || nc < 0 || nc >= N)  return false;
		if(unionMap[nr][nc] != 0)   return false;
		int sub = Math.abs(map[curr[ROW]][curr[COL]] - map[nr][nc]);
		return sub >= L && sub <= R;
	}

	private static void setMap(int avg, int unionCnt) {
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < N ; c++) {
				if(unionMap[r][c] != unionCnt) continue;
				map[r][c] = avg;
			}
		}
	}

}
