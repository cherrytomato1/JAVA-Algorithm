package boj.boj0120_210421_1194_g1;

import java.util.*;
import java.io.*;

public class 백준_1194번_달이차오른다가자 {
	private static class Pos {
		int row;
		int col;
		int keyFlag;

		public Pos(int row, int col, int keyFlag) {
			this.row = row;
			this.col = col;
			this.keyFlag = keyFlag;
		}
	}

	private static int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	private static char[][] map;
	private static boolean[][][] visited;

	private static int N;
	private static int M;

	private static Pos init() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][1 << ('F' - 'A' + 1)];

		Pos start = null;
		for (int r = 0; r < N ; r++) {
			map[r] = br.readLine().toCharArray();
			for (int c = 0; c < M ; c++) {
				if(map[r][c] == '0')    start = new Pos(r, c, 0);
			}
		}
		return start;
	}

	private static int bfs(Pos start) {
		int ret = 0;
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);
		visited[start.row][start.col][0] = true;

		while (!q.isEmpty()) {
			ret++;
			for (int size = q.size(); size > 0 ; size--) {
				Pos curr = q.poll();
				for (int i = 0; i < 4 ; i++) {
					int nr = curr.row + DIR[0][i];
					int nc = curr.col + DIR[1][i];
					int nf = curr.keyFlag;
					if(!isValid(nr, nc, nf))    continue;
					if (map[nr][nc] == '1')     return ret;
					if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f')  nf |= 1 << (map[nr][nc] - 'a');
					visited[nr][nc][nf] = true;
					q.offer(new Pos(nr, nc, nf));
				}
			}
		}
		return -1;
	}

	private static boolean isValid(int row, int col, int flag) {
		if (row >= N || row < 0 || col >= M || col < 0)     return false;
		if (map[row][col] == '#' || visited[row][col][flag]) return false;
		if (map[row][col] >= 'A' && map[row][col] <= 'F'
				    && (flag & 1 << (map[row][col] - 'A')) == 0)  return false;
		return true;
	}

	public static void main(String[] args) throws IOException{
		System.out.println(bfs(init()));
	}
}
