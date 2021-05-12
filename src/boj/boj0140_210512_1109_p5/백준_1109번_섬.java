package boj.boj0140_210512_1109_p5;

import java.util.*;
import java.io.*;

public class 백준_1109번_섬 {
	private static class Pos {
		int row;
		int col;
		public Pos(int row, int col) {
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
	private static int[] countOfDepth;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException{
		init();
		if (!setIslandNo()) System.out.println("-1");
		else                System.out.println(setCountOfDepthToString(findChildIsland(1)));
//		printMap();
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
		countOfDepth = new int[50];

		for (int i = 0; i < N ; i++) {
			char[] in = br.readLine().toCharArray();
			for (int j = 0; j < M ; j++) {
				map[i][j] = in[j] == 'x' ? 100 : 0;
			}
		}
	}

	private static boolean setIslandNo(){
		int idx = 1;
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < M ; c++) {
				if(map[r][c] != 100)    continue;
				setIslandNo(++idx, new Pos(r, c));
			}
		}
		visited = new boolean[idx + 1];
		return idx != 1;
	}

	private static void setIslandNo(int idx, Pos pos) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(pos);
		map[pos.row][pos.col] = idx;
		while (!q.isEmpty()) {
			Pos curr = q.poll();
			for (int i = 0; i < 8 ; i++) {
				int nr = curr.row + DIR[ROW][i];
				int nc = curr.col + DIR[COL][i];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] != 100)    continue;
				map[nr][nc] = idx;
				q.offer(new Pos(nr, nc));
			}
		}
	}


	private static int findChildIsland(int islandIdx) {
		Queue<Pos> q = setQueue(islandIdx);
		List<Integer> childList = new ArrayList<>();
		Set<Integer> childSet = new HashSet<>();

		if(islandIdx == 1 && q.isEmpty())   {
			addChild(childSet, childList, map[0][0], islandIdx);
		}

		int depth = 0;
		while (!q.isEmpty()){
			Pos curr = q.poll();

			for (int i = 0; i < 4 ; i++) {
				int nr = curr.row + DIR[ROW][i];
				int nc = curr.col + DIR[COL][i];

				if(nr < 0 || nr >= N || nc < 0 || nc >= M)      continue;
				if(map[nr][nc] < 0 || map[nr][nc] == islandIdx) continue;
				addChild(childSet, childList, map[nr][nc], islandIdx);

				if(map[nr][nc] != 0)    continue;
				map[nr][nc] = -1;
				q.offer(new Pos(nr, nc));
			}
		}

		for (int child : childList) {
			depth = Math.max(findChildIsland(child), depth);
		}
		countOfDepth[depth]++;
		return depth + 1;
	}

	private static Queue<Pos> setQueue(int idx) {
		Queue<Pos> q = new ArrayDeque<>();
		if(visited[idx])    return q;
		visited[idx] = true;

		if (idx == 1) {
			for (int r = 0; r < N ; r++) {
				if(map[r][0] == 0)      q.offer(new Pos(r, 0));
				if(map[r][M - 1] == 0)  q.offer(new Pos(r, M - 1));
			}
			for (int c = 0; c < M; c++) {
				if(map[0][c] == 0)      q.offer(new Pos(0, c));
				if(map[N - 1][c] == 0)  q.offer(new Pos(N - 1, c));
			}
			return q;
		}
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c < M ; c++) {
				if (map[r][c] == idx)   q.offer(new Pos(r, c));
			}
		}
		return q;
	}

	private static void addChild(Set<Integer> set, List<Integer> list, int val, int idx) {
		if(val <= 0 || val == idx || set.contains(val))  return;
		set.add(val);
		list.add(val);
	}

	private static String setCountOfDepthToString(int maxDepth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < maxDepth - 1 ; i++) {
			sb.append(countOfDepth[i]).append(" ");
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