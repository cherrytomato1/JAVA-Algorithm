package boj.boj0148_210523_16918_s1;

import java.util.*;
import java.io.*;

public class 백준_16918_봄버맨 {
	private static char[][] map;
	private static int R;
	private static int C;
	private static int N;

	public static void main(String[] args) throws IOException{
		init();
		solve();
		printMap();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for (int r = 0; r < R ; r++) {
			map[r] = br.readLine().toCharArray();
		}
	}

	private static void solve() {
		if (N == 1) return;
		if (N % 2 == 0) {
			for (int r = 0; r < R ; r++) {
				Arrays.fill(map[r], 'O');
			}
			return;
		}
		bomb();
		if (N % 4 == 3) return;
		bomb();

	}

	private static void bomb() {
		boolean[][] visited = new boolean[R][C];
		for (int r = 0; r < R ; r++) {
			for (int c = 0; c < C ; c++) {
				if(map[r][c] != 'O')   continue;
				bomb(r, c, visited);
			}
		}
		setMap(visited);
	}

	private static void bomb(int r, int c, boolean[][] visited) {
		for (int i = -1; i < 2 ; i++) {
			for (int j = -1; j < 2 ; j++) {
				if(i != 0 && j != 0)    continue;
				int nr = i + r;
				int nc = j + c;
				if(nr >= R || nr < 0 || nc >= C || nc < 0 )  continue;
				visited[nr][nc] = true;
			}
		}
	}

	private static void setMap(boolean[][] visited) {
		for (int r = 0; r < R ; r++) {
			for (int c = 0; c < C ; c++) {
				map[r][c] = visited[r][c] ? '.':'O';
			}
		}
	}

	private static void printMap() {
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < R ; r++) {
			for (int c = 0; c < C ; c++) {
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
