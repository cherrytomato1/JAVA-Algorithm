package boj.boj0123_210423_19238_g4;

import java.util.*;
import java.io.*;

public class 백준_19238번_스타트택시 {
	private static class Passenger implements Comparable<Passenger>{
		int no;
		int startRow;
		int startCol;
		int targetRow;
		int targetCol;

		public Passenger(int no, int startRow, int startCol, int targetRow, int targetCol) {
			this.no = no;
			this.startRow = startRow;
			this.startCol = startCol;
			this.targetRow = targetRow;
			this.targetCol = targetCol;
		}

		@Override
		public int compareTo(Passenger p) {
			int val = Integer.compare(this.startRow, p.startRow);
			return val == 0 ? Integer.compare(this.startCol, p.startCol) : val;
		}
	}
	private static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	private static int N;
	private static int M;
	private static int[][] map;
	private static int gas;
	private static Passenger[] passengers;

	public static void main(String[] args) throws IOException{
		int[] start = init();
		System.out.println(solve(start));
	}

	private static int[] init() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		gas = Integer.parseInt(st.nextToken());

		passengers = new Passenger[M + 1];
		map = new int[N][N];
		for (int r = 0; r < N ; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N ; c++) {
				map[r][c] = st.nextToken().charAt(0) == '1' ? -1 : 0;
			}
		}

		st = new StringTokenizer(br.readLine());
		int[] start = new int[2];
		start[ROW] = Integer.parseInt(st.nextToken()) - 1;
		start[COL] = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 1; i <= M ; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = i;
			passengers[i] = new Passenger(i, r, c, Integer.parseInt(st.nextToken()) - 1
					, Integer.parseInt(st.nextToken()) - 1);
		}

		return start;
	}

	private static int solve(int[] start) {
		int[] pos = start;
		for (int i = 0; i < M ; i++) {
			Passenger p = findPassenger(pos);
			if(p == null)   return -1;
			pos = dropPassenger(p);
			if(pos == null) return -1;
		}
		return gas;
	}

	private static Passenger findPassenger(int[] pos) {
		if(map[pos[ROW]][pos[COL]] >= 1) {
			int p = map[pos[ROW]][pos[COL]];
			map[pos[ROW]][pos[COL]] = 0;
			return passengers[p];
		}

		Queue<int[]> q = new ArrayDeque<>();
		Queue<Passenger> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];
		visited[pos[ROW]][pos[COL]] = true;
		q.offer(pos);
		while (!q.isEmpty() && gas >= 1) {
			gas--;
			for (int size = q.size(); size > 0 ; size--) {
				pos = q.poll();
				for (int i = 0; i < 4 ; i++) {
					int nr = pos[ROW] + DIR[ROW][i];
					int nc = pos[COL] + DIR[COL][i];

					if(nr >= N || nr < 0 || nc >= N || nc < 0
							   || visited[nr][nc] || map[nr][nc] == -1) continue;
					if(map[nr][nc] != 0) pq.offer(passengers[map[nr][nc]]);
					visited[nr][nc] = true;
					q.offer(new int[]{nr, nc});
				}
			}
			if(!pq.isEmpty())   {
				Passenger p = pq.peek();
				map[p.startRow][p.startCol] = 0;
				return p;
			}
		}
		return null;
	}

	private static int[] dropPassenger(Passenger p) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		visited[p.startRow][p.startCol] = true;
		q.offer(new int[] {p.startRow, p.startCol});

		int usedGas = 0;
		while (!q.isEmpty() && gas >= 1) {
			gas--;
			usedGas++;
			for (int size = q.size(); size > 0 ; size--) {
				int[] pos = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = pos[ROW] + DIR[ROW][i];
					int nc = pos[COL] + DIR[COL][i];

					if (nr >= N || nr < 0 || nc >= N || nc < 0
							    || visited[nr][nc] || map[nr][nc] == -1) continue;
					if (nr == p.targetRow && nc == p.targetCol){
						gas += usedGas * 2;
						return new int[]{nr, nc};
					}
					visited[nr][nc] = true;
					q.offer(new int[]{nr, nc});
				}
			}
		}
		return null;
	}
}
