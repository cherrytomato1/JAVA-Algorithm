package boj.boj0122_210423_17143_g2;

import java.util.*;
import java.io.*;

public class 백준_17143번_낚시왕 {
	private static class Shark implements Comparable<Shark> {
		int row;
		int col;
		int speed;
		int dir;
		int size;
		boolean isLived;

		private Shark(int row, int col, int speed, int dir, int size) {
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
			isLived = true;
		}

		@Override
		public int compareTo(Shark shark) {
			return Integer.compare(this.row, shark.row);
		}
	}

	private static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, 1, -1}};

	private static int R;
	private static int C;
	private static int M;
	private static Shark[] sharks;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharks = new Shark[M];

		for (int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			sharks[i] = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
					, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1
					, Integer.parseInt(st.nextToken()));
		}
	}

	private static int solve() {
		int ret = 0;
		for (int fisher = 1; fisher <= C; fisher++) {
			ret += killShark(fisher);
			moveShark();
		}
		return ret;
	}

	private static int killShark(int fisher) {
		Queue<Shark> pq = new PriorityQueue<>();

		for (int i = 0; i < M ; i++) {
			if(!sharks[i].isLived || sharks[i].col != fisher)  continue;
			pq.offer(sharks[i]);
		}
		if(pq.isEmpty())    return 0;
		pq.peek().isLived = false;
		return pq.peek().size;
	}

	private static void moveShark() {
		Shark[][] sharkVisited = new Shark[R + 1][C + 1];

		for (int i = 0; i < M ; i++) {
			Shark cur = sharks[i];
			if(!cur.isLived)     continue;
			moveShark(cur);
			Shark target = sharkVisited[cur.row][cur.col];
			if (target != null) {
				if (cur.size > target.size) target.isLived = false;
				else {
					cur.isLived = false;
					continue;
				}
			}
			sharkVisited[cur.row][cur.col] = cur;
		}
	}

	private static void moveShark(Shark cur) {
		cur.row += (cur.speed % (R * 2 - 2)) * DIR[0][cur.dir];
		cur.col += (cur.speed % (C * 2 - 2)) * DIR[1][cur.dir];

		while(cur.row < 1 || cur.col < 1 || cur.row > R || cur.col > C) {
			if (cur.row < 1) {
				cur.row = -cur.row + 2;
				cur.dir++;
			} else if (cur.row > R) {
				cur.row = R - cur.row + R;
				cur.dir--;
			} else if (cur.col < 1) {
				cur.col = -cur.col + 2;
				cur.dir--;
			} else {
				cur.col = C - cur.col + C;
				cur.dir++;
			}
		}
	}
}

