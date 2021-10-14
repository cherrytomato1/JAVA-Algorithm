package boj.boj0178_2101001_19237_g3_어른상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_19237_어른상어 {

	private static class Shark {
		private int[][][] dir;
		private int idx;
		private int row;
		private int col;
		private int currDir;
		private boolean isDead;

		public Shark(int idx, int row, int col) {
			this.idx = idx;
			this.row = row;
			this.col = col;
			this.dir = new int[4][2][4];
		}

		public void setDir(String dir, int p) {
			for (int i = 0; i < dir.length(); i++) {
				int d = dir.charAt(i) - '0';
				this.dir[p][d / 3][i] = d % 2 == 0 ? 1 : -1;
			}
		}
	}

	private static class Smell {
		private int idx;
		private int cnt;

		public Smell(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}


	private static int[][] sharkMap;
	private static Smell[][] smellMap;
	private static int N;
	private static int M;
	private static int K;

	private static List<Shark> sharkList;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		sharkList = new ArrayList<>();
		sharkMap = new int[N][N];
		smellMap = new Smell[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int point = Integer.parseInt(st.nextToken());

				if (point == 0) {
					smellMap[r][c] = new Smell(0, 0);
					continue;
				}
				sharkList.add(new Shark(point, r, c));
				smellMap[r][c] = new Smell(point, K);
			}
		}

		sharkList.sort(Comparator.comparingInt(o -> o.idx));
		st = new StringTokenizer(br.readLine());
		for (Shark shark : sharkList) {
			shark.currDir = Integer.parseInt(st.nextToken()) - 1;
		}

		for (Shark shark : sharkList) {
			for (int i = 0; i < 4; i++) {
				shark.setDir(br.readLine().replaceAll(" ", ""), i);
			}
		}
	}

	private static int solve() {
		int times = 0;
		while (M > 1 && times <= 1_000) {
			for (Shark shark : sharkList) {
				if (shark.isDead) {
					continue;
				}
				moveShark(shark);
			}
			subSmells();

			times++;
		}

		return times > 1000 ? -1 : times;
	}

	private static void moveShark(Shark shark) {
		for (int i = 0; i < 3; i++) {
			if (findSmellAndMove(shark, i)) {
				return;
			}
		}
	}


	private static boolean findSmellAndMove(Shark shark, int command) {
		int targetSmell = command == 0 ? 0 : shark.idx;

		for (int i = 0; i < 4; i++) {
			int nr = shark.row + shark.dir[shark.currDir][0][i];
			int nc = shark.col + shark.dir[shark.currDir][1][i];

			if (nr >= N || nr < 0 || nc >= N || nc < 0) {
				continue;
			}
			if (smellMap[nr][nc].idx != targetSmell && command != 2) {
				continue;
			}

			shark.row = nr;
			shark.col = nc;
			shark.currDir = shark.dir[shark.currDir][0][i] + shark.dir[shark.currDir][1][i] * 3;
			if (shark.currDir < 0) {
				shark.currDir++;
				shark.currDir *= -1;
			}

			if (sharkMap[nr][nc] == 0) {
				sharkMap[nr][nc] = shark.idx;
			} else {
				shark.isDead = true;
				M--;
			}
			return true;
		}
		return false;
	}

	private static void subSmells() {

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (--smellMap[r][c].cnt == 0) {
					smellMap[r][c].idx = 0;
				}
				if (sharkMap[r][c] == 0) {
					continue;
				}
				smellMap[r][c] = new Smell(sharkMap[r][c], K);
				sharkMap[r][c] = 0;
			}
		}
	}
}
