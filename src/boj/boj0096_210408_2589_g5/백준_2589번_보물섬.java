package boj.boj0096_210408_2589_g5;

import java.util.*;
import java.io.*;

public class 백준_2589번_보물섬 {
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
	private static int N;
	private static int M;

	private static boolean[][] map;
	private static boolean[][] visited;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];

		for (int i = 0; i < N ; i++) {
			char[] in = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if(in[j] == 'L')    map[i][j] = true;
			}
		}
	}

	private static int bfs(Pos start){
		int ret = 0;
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);
		while (!q.isEmpty()){
			ret++;
			for(int i = 0, size = q.size(); i < size ;i++){
				Pos curr = q.poll();
				for (int j = 0; j < 4; j++) {
					int nr = curr.row + DIR[0][j];
					int nc = curr.col + DIR[1][j];
					if (nr >= N || nr < 0 || nc >= M || nc < 0
							    || !map[nr][nc] || visited[nr][nc]) continue;
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
				}
			}
		}
		return ret - 1;
	}

	private static int solve(){
		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!map[i][j]) continue;
				visited = new boolean[N][M];
				visited[i][j] = true;
				ret = Math.max(bfs(new Pos(i, j)), ret);
			}
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

}
