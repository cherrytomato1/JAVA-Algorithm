package boj.boj0129_210503_1520_g4;

import java.util.*;
import java.io.*;

public class 백준_1520번_내리막길 {
	private static int[][] map;
	private static int N;
	private static int M;
	private static int[][] visited;

	private static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	private static final int FAIL = -1;
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int res;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1 ; tc <= T; tc++) {
			init();
			dfs(0, 0);
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
	}

	public static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		res = 0;
		map = new int[N][M];
		visited = new int[N][M];

		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited[N - 1][M - 1] = 1;
	}

	public static int dfs(int row, int col) {
		if(visited[row][col] >= 1)	res += visited[row][col];
		if(visited[row][col] != 0)	return visited[row][col];

		for(int i = 0 ; i < 4 ; i++) {
			int nr = row + DIR[0][i];
			int nc = col + DIR[1][i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] >= map[row][col])	continue;
			int val = dfs(nr, nc);
			if(val >= 1)	visited[row][col] += val;
		}
		if(visited[row][col] == 0) visited[row][col]--;
		return visited[row][col];
	}
}
