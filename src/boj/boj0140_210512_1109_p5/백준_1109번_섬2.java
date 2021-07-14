package boj.boj0140_210512_1109_p5;

import java.io.*;
import java.util.*;

public class 백준_1109번_섬2 {
	private static class Pos {
		int idx;
		int row;
		int col;
		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public Pos(int row, int col, int idx) {
			this.idx = idx;
			this.row = row;
			this.col = col;
		}
	}
	private static final int[][] DIR = {{-1, 1, 0, 0, -1, -1, 1, 1}, {0, 0, -1, 1, -1, 1, -1, 1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] parents;

	private static List<Pos> islandList;

	public static void main(String[] args) throws IOException{
		init();
		if (!setIslandNo()) {
			System.out.println("-1");
			return;
		}
//		printMap();
		islandList.forEach(island -> findParentIsland(island));

		int[] islandDepth = new int[parents.length];
		int maxDepth = setIslandDepth(islandDepth);
		System.out.println(getCountOfDepthToString(islandDepth, maxDepth));
//		System.out.println(Arrays.toString(parents));
	}

	private static void printMap() {
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < M ; c++) {
				System.out.printf("%3d", map[r][c]);
			}
			System.out.println();
		}
	}

	private static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		islandList = new LinkedList<>();

		for (int i = 0; i < N ; i++) {
			char[] in = br.readLine().toCharArray();
			for (int j = 0; j < M ; j++) {
				map[i][j] = in[j] == 'x' ? 'x' : 0;
			}
		}
	}

	private static boolean setIslandNo(){
		int idx = 1;
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < M ; c++) {
				if(map[r][c] != 'x')    continue;
				setIslandNo(idx, new Pos(r, c, idx++));
			}
		}
		parents = new int[idx];
		return idx != 1;
	}

	private static void setIslandNo(int idx, Pos pos) {
		islandList.add(pos);

		Queue<Pos> q = new ArrayDeque<>();
		q.offer(pos);
		map[pos.row][pos.col] = idx;
		while (!q.isEmpty()) {
			Pos curr = q.poll();
			for (int i = 0; i < 8 ; i++) {
				int nr = curr.row + DIR[ROW][i];
				int nc = curr.col + DIR[COL][i];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] != 'x')    continue;
				map[nr][nc] = idx;
				q.offer(new Pos(nr, nc));
			}
		}
	}


	private static void findParentIsland(Pos pos) {
		int[] islandCount = new int[islandList.size() + 1];
		boolean[][] visited = new boolean[N][M];
//		System.out.println("find");

		Queue<Pos> q = new ArrayDeque<>();
		q.offer(pos);
		visited[pos.row][pos.col] = true;

		while (!q.isEmpty()){
			Pos curr = q.poll();

			for (int i = 0; i < 4 ; i++) {
				int nr = curr.row + DIR[ROW][i];
				int nc = curr.col + DIR[COL][i];

				if(nr < 0 || nr >= N || nc < 0 || nc >= M)      return;
				if(visited[nr][nc])   continue;
				visited[nr][nc] = true;
				if(map[nr][nc] != pos.idx && map[nr][nc] != 0) {
					islandCount[map[nr][nc]]++;
					continue;
				}
				q.offer(new Pos(nr, nc));
			}
		}
//		System.out.println("go");
		int maxCount = 0;
		int parentsIsland = 0;
		for (int i = 1; i < islandCount.length; i++) {
			if(islandCount[i] < maxCount)  continue;

			maxCount = islandCount[i];
			parentsIsland = i;
		}
		parents[pos.idx] = parentsIsland;
	}

	private static int setIslandDepth(int[] islandDepth) {
		int maxDepth = 0;
		for (int i = 1; i < islandDepth.length ; i++) {
			setIslandDepth(i, 0, islandDepth);
		}

		for (int i = 1; i < islandDepth.length ; i++) {
			maxDepth = Math.max(maxDepth, islandDepth[i]);
		}

		return maxDepth;
	}

	private static void setIslandDepth(int idx, int depth, int[] islandDepth) {
		islandDepth[idx] = Math.max(islandDepth[idx], depth);
		if(parents[idx] == 0)       return;

		setIslandDepth(parents[idx], depth + 1, islandDepth);
	}

	private static String getCountOfDepthToString(int[] islandDepth, int maxDepth) {
		int[] countOfDepth = new int[maxDepth + 1];
		for (int depth : islandDepth ) {
			countOfDepth[depth]++;
		}
		countOfDepth[0]--;

		StringBuilder sb = new StringBuilder();
		for (int j : countOfDepth) {
			sb.append(j).append(" ");
		}
		return sb.toString();
	}
}

/*
9 13
xxx.x...xxxxx
xxxx....x...x
........x.x.x
..xxxxx.x...x
..x...x.xxx.x
..x.x.x...x..
..x...x...xxx
...xxxxxx....
x............
5 1

9 13
xxx.x...xxxxx
xxxx....x...x
........x.x.x
..xxxxx.x...x
..x...x.xxx.x
..x.x.x...x.x
..x...x...xxx
...xxxxxx....
x............
4 2

5 5
xxxxx
xxxxx
xxxxx
xxxxx
xxxxx
1

5 5
.x.x.
xxxxx
x.x.x
xxxxx
.x.x.

5 4
xxxx
x..x
....
xx.x
x.x.

3 3
x.x
.x.
x.x
1

3 3
.x.
x.x
.x.
1

15 9
xxxxxxxxx
x.......x
x.xxxxx.x
x.x...x.x
x.x.x.x.x
x.x...x.x
x.xxxxx.x
x...x...x
x.xxxxx.x
x.x...x.x
x.x.x.x.x
x.x...x.x
x.xxxxx.x
x.......x
xxxxxxxxx
1 1 1

1 1
x
1

11 10
xxxxxxxxxx
x........x
x.xxxxxx.x
x.x....x.x
x.x.xx.x.x
x.x....x.x
x.xxxxxx.x
x........x
x.x.xx.x.x
x........x
xxxxxxxxxx
4 1 1

3 3
x..
.x.
..x
1

3 3
x.x
x.x
x.x
2

1 3
x.x
2

6 5
xxxxx
.x..x
x...x
x.x.x
x...x
xxxxx
1 1

7 5
x.x.x
.x.x.
x...x
x.x.x
x...x
.x.x.
x.x.x
1

50 50
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
..x............................................x..
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

50 50
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
x................................................x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x................................................x
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx





50 50
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.
.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x.x


50 50
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
x................................................x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x.x............................................x.x
x.x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x.x
x.x.x........................................x.x.x
x.x.x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x.x.x
x.x.x.x....................................x.x.x.x
x.x.x.x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x.x.x.x
x.x.x.x.x................................x.x.x.x.x
x.x.x.x.x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x.x.x.x.x
x.x.x.x.x.x............................x.x.x.x.x.x
x.x.x.x.x.x.xxxxxxxxxxxxxxxxxxxxxxxxxx.x.x.x.x.x.x
x.x.x.x.x.x.x........................x.x.x.x.x.x.x
x.x.x.x.x.x.x.xxxxxxxxxxxxxxxxxxxxxx.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x....................x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.xxxxxxxxxxxxxxxxxx.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x................x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.xxxxxxxxxxxxxx.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x............x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.xxxxxxxxxx.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x........x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.xxxxxx.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.x....x.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x.xxxxxx.x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.x........x.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x.xxxxxxxxxx.x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.x............x.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x.xxxxxxxxxxxxxx.x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.x................x.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x.xxxxxxxxxxxxxxxxxx.x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.x....................x.x.x.x.x.x.x.x
x.x.x.x.x.x.x.xxxxxxxxxxxxxxxxxxxxxx.x.x.x.x.x.x.x
x.x.x.x.x.x.x........................x.x.x.x.x.x.x
x.x.x.x.x.x.xxxxxxxxxxxxxxxxxxxxxxxxxx.x.x.x.x.x.x
x.x.x.x.x.x............................x.x.x.x.x.x
x.x.x.x.x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x.x.x.x.x
x.x.x.x.x................................x.x.x.x.x
x.x.x.x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x.x.x.x
x.x.x.x....................................x.x.x.x
x.x.x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x.x.x
x.x.x........................................x.x.x
x.x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x.x
x.x............................................x.x
x.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.x
x................................................x
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
 */