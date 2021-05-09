package boj.boj0134_210509_17267_p5;

import java.util.*;
import java.io.*;

public class 백준_17267번_상남자 {
	private static class Pos{
		int row;
		int col;
		int left;
		int right;

		public Pos(int row, int col, int left, int right){
			this.row = row;
			this.col = col;
			this.left = left;
			this.right = right;
		}
	}

	private static int N;
	private static int M;

	private static final int LEFT = 0;
	private static final int RIGHT = 1;

	private static boolean[][] map;
	private static boolean[][] counted;
	private static int[][][] visited;

	public static void main(String[] args) throws IOException{
		System.out.println(solve(init()));
	}

	private static Pos init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];
		counted = new boolean[N][M];
		visited = new int[N][M][2];

		Pos start = null;
		for (int r = 0; r < N ; r++) {
			char[] in = br.readLine().toCharArray();
			for (int c = 0; c < M ; c++) {
				if(in[c] == '2')        start = new Pos(r, c, L, R);
				else if(in[c] == '1')   map[r][c] = true;
				visited[r][c][LEFT] = -1;
				visited[r][c][RIGHT] = -1;
			}
		}

		return start;
	}

	private static int solve(Pos start) throws IOException{
		int ret = 1;
		Queue<Pos> q = new ArrayDeque<>();
		visited[start.row][start.col][LEFT] = start.left;
		visited[start.row][start.col][RIGHT] = start.right;
		q.offer(start);

		final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

		while (!q.isEmpty()){
			Pos cur = q.poll();

			for (int i = 0; i < 4 ; i++) {
				int nr = cur.row + DIR[0][i];
				int nc = cur.col + DIR[1][i];
				int nLeft = cur.left + (i == 2 ? -1 : 0);
				int nRight = cur.right + (i == 3 ? -1 : 0);

				if(!isValid(nr, nc, nLeft, nRight)) continue;;
				if(!counted[nr][nc]){
					counted[nr][nc] = true;
					ret++;
				}

				visited[nr][nc][LEFT] = Math.max(visited[nr][nc][LEFT], nLeft);
				visited[nr][nc][RIGHT] = Math.max(visited[nr][nc][RIGHT], nRight);
				q.offer(new Pos(nr, nc, nLeft, nRight));
			}
		}
		return ret;
	}

	private static boolean isValid(int row, int col, int left, int right) {
		if(left < 0 | right < 0)   return false;
		if(row >= N | row < 0 | col >= M | col < 0 || map[row][col]) return false;
		if(visited[row][col][LEFT] >= left && visited[row][col][RIGHT] >= right) return false;

		return true;
	}
}

