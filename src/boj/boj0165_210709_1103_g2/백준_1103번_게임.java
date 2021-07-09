package boj.boj0165_210709_1103_g2;

import java.util.*;
import java.io.*;

public class 백준_1103번_게임 {
	private static final int[][] DIR = {{1, -1, 0, 0}, {0, 0, 1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	private static char[][] map;
	private static int[][] d;
	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(dfs(0, 0));
	}

	private static void init() throws IOException {
		InputStream in = System.in;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];
		d = new int[N][M];

		for (int r = 0; r < N ; r++) {
			map[r] = br.readLine().toCharArray();
		}
	}

	private static int dfs(int row, int col) {
		if(row >= N || row < 0 || col >= M || col < 0 || map[row][col] == 'H')  return 0;
		if(d[row][col] != 0 )       return d[row][col];
		d[row][col] = -1;

		int maxReturnValue = 0;
		int mapValue = map[row][col] - '0';
		for (int i = 0; i < 4; i++) {
			int nr = row + DIR[ROW][i] * mapValue;
			int nc = col + DIR[COL][i] * mapValue;
			int ret = dfs(nr, nc);
			if(ret == -1)  return ret;
			maxReturnValue = Math.max(ret, maxReturnValue);
		}

		return d[row][col] = maxReturnValue + 1;
	}

}
