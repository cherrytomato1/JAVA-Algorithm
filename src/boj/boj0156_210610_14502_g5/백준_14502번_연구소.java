package boj.boj0156_210610_14502_g5;

import java.util.*;
import java.io.*;

public class 백준_14502번_연구소 {
	private static char[][] map;
	private static char[][] copyMap;
	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException{
		init();
		recur(0, 0, 0);
		System.out.println(maxValue);
	}

	private static void init() throws IOException{
		InputStream in = System.in;
		final BufferedReader br = new BufferedReader(new InputStreamReader(in));
		final StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N ; i++) {
			map[i] = br.readLine().replaceAll(" ", "").toCharArray();
		}
		maxValue = Integer.MIN_VALUE;
	}

	private static int maxValue;
	private static void recur(long flag, int idx, int cnt) {
		if (cnt == 3) {
			maxValue = Math.max(bfs(flag), maxValue);
		}

		for (int i = idx; i < 64; i++) {
			recur(flag | 1L << i, i, cnt + 1);
		}
	}

	private static int bfs(long flag) {
		for (int i = 0; i < 64 ; i++) {
			if((flag | 1L << i) != 0);
		}
		return 0;
	}

	private static void printMap(char[][] tempMap) {
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < M ; j++) {
				System.out.print(tempMap[i][j] + " ");
			}
			System.out.println();
		}
	}
}
