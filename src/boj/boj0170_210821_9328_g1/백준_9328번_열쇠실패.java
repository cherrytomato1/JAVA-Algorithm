package boj.boj0170_210821_9328_g1;

import java.util.*;
import java.io.*;

public class 백준_9328번_열쇠실패 {
	private static class Position {
		int row;
		int col;
		int flag;

		public Position(int flag) {
			this.flag = flag;
		}

		public Position(int row, int col, int flag) {
			this.row = row;
			this.col = col;
			this.flag = flag;
		}

		public Position(Position position) {
			this.row = position.row;
			this.col = position.col;
			this.flag = position.flag;
		}

		@Override
		public String toString() {

			final StringBuilder sb = new StringBuilder("Position{");
			sb.append("row=").append(row);
			sb.append(", col=").append(col);
			sb.append(", flag=");
			for (int i = 0; i < 26 ; i++) {
				if ((flag & 1 << i) != 0) {
					sb.append((char)(i + 'a'));
				}
			}
			sb.append('}');
			return sb.toString();
		}
	}

	private static char[][] map;
	private static Position[][] visitedPosition;
	private static int N;
	private static int M;

	private static int res;

	private static InputStream in = System.in;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(in));
	private static StringTokenizer st;

	private static final char WALL = '*';
	private static final char PAPER = '$';

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			int startFlag = init();
			solve(startFlag);
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}

	private static int init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		res = 0;
		map = new char[N][];
		visitedPosition = new Position[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		char[] startKeys = br.readLine().toCharArray();

		if (startKeys.length == 1 && startKeys[0] == '0') {
			return 0;
		}

		int startFlag = 0;
		for (char key : startKeys) {
			startFlag |= 1 << key - 'a';
		}
		return startFlag;
	}

	private static int solve(int startFlag) {
		final int ROW = 0;
		final int COL = 1;
		final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

		Queue<Position> q = new ArrayDeque<>();
		addStartPosition(q, startFlag);

		while (!q.isEmpty()) {
			Position curr = q.poll();

//			printMap(curr);
//			System.out.println( ++temp);

			for (int i = 0; i < 4 ; i++) {
				int nr = curr.row + DIR[ROW][i];
				int nc = curr.col + DIR[COL][i];

				if (nr >= N || nr < 0 || nc >= M || nc < 0) {
					addStartPosition(q, curr.flag);
					continue;
				}
				Position next = new Position(nr, nc, curr.flag);
				move(q, next);
			}
		}
		return res;
	}

	private static void addStartPosition(Queue<Position> q, int flag) {
		for(int i = 0 ; i < 4; i++) {
			boolean isRow = i % 2 == 0;
			boolean isMaxIndex = i < 2;
			for (int j = 0, size = isRow ? N : M; j < size; j++) {
				int row = isRow ? j : (isMaxIndex ? N - 1 : 0);
				int col = isRow ? (isMaxIndex ? M - 1 : 0) : j;
				Position curr = new Position(row, col, flag);

				move(q, curr);
			}
		}
	}

	private static void move(Queue<Position> q, Position curr) {
		if (!isMovable(curr)) {
			return;
		}
		char targetPosMap = map[curr.row][curr.col];

		int indexValue = curr.row * M + curr.col;
		if (targetPosMap == PAPER) {
			res++;
			//한번 먹을 수 있는 문서는 어떤경로로 와도 먹을 수 있음
			map[curr.row][curr.col] = '.';
		}
		if (targetPosMap >= 'a' && targetPosMap <= 'z') {
			curr.flag |= 1 << targetPosMap - 'a';
		}
		visitedPosition[curr.row][curr.col].flag |= curr.flag;

		q.add(curr);

	}

	private static boolean isMovable(Position position) {
		int row = position.row;
		int col = position.col;
		int flag = position.flag;

		if (map[row][col] == WALL) {
			return false;
		}

		if (map[row][col] <= 'Z' && map[row][col] >= 'A' && (flag & 1 << map[row][col] - 'A') == 0) {
			return false;
		}

		if (visitedPosition[row][col] == null) {
			visitedPosition[row][col] = new Position(flag);
			return true;
		}

		return (visitedPosition[row][col].flag & flag) != flag;
	}

	private static void printMap(Position curr) {
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < M ; c++) {
				if (curr.row == r && curr.col == c) {
					System.out.print('^');
					continue;
				}
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
		System.out.println(curr);
	}
}


/*


1
5 17
*****************
.............**$*
*B*A*P*C**X*Y*.X.
*y*x*a*p**$*$**$*
*****************
cz

1
3 5
*****
.x*$Z
***$*
0

1
7 7
*ABCDE*
X.....F
W.$$$.G
V.$$$.H
U.$$$.J
T.....K
*SQPML*
irony

1
4 5
*A***
*$*a.
**$**
**A**
0

1
3 3
aaa
a$a
aaa
0

1
3 54
******************************************************
AbCdEfGhIjKlMnOpQrStUvWxYz*$ZyXwVuTsRqPoNmLkJiHgFeDcBa
******************************************************
0
 */