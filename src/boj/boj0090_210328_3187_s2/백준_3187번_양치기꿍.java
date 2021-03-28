package boj.boj0090_210328_3187_s2;

import java.util.*;
import java.io.*;

public class 백준_3187번_양치기꿍 {
	private static class Pos{
		int row;
		int col;
		public Pos(int row, int col){
			this.row = row;
			this.col = col;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

	private static int R;
	private static int C;
	private static char[][] map;
	private static boolean[][] isVisited;

	private static int livedWolfCount;
	private static int livedSheepCount;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		isVisited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
	}

	private static void solve(){
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(isVisited[r][c] || map[r][c] == '#') continue;
				bfs(new Pos(r, c));
			}
		}
	}

	private static void bfs(Pos start){
		int sheepCount = 0;
		int wolfCount = 0;

		Queue<Pos> q = new ArrayDeque<>();
		isVisited[start.row][start.col] = true;
		q.offer(start);

		while(!q.isEmpty()){
			Pos curr = q.poll();
			int row = curr.row;
			int col = curr.col;

			sheepCount += map[row][col] == 'k' ? 1 : 0;
			wolfCount += map[row][col] == 'v' ? 1 : 0;

			for (int i = 0; i < 4 ; i++) {
				int nr = row + DIR[0][i];
				int nc = col + DIR[1][i];
				if(nr >= R || nr < 0 || nc >= C || nc < 0 ||
						   isVisited[nr][nc] || map[nr][nc] == '#')     continue;

				isVisited[nr][nc] = true;
				q.offer(new Pos(nr, nc));
			}
		}
		if(sheepCount > wolfCount)  livedSheepCount += sheepCount;
		else                        livedWolfCount += wolfCount;
	}

	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.printf("%d %d", livedSheepCount, livedWolfCount);
	}

}
