package swea.solution_SW_1953;

import java.util.*;
import java.io.*;

public class SWEA_1953_탈주범검거 {
	private static class Pos{
		int row;
		int col;
		public Pos(int row, int col){
			this.row = row;
			this.col = col;
		}
	}

	private static final int[][][] DIR =
			{{{0, 0, 0, 0}, {0, 0, 0, 0}}
			,{{-1, 1, 0, 0}, {0, 0, -1, 1}}
			,{{-1, 1, 0, 0}, {0, 0, 0, 0}}
			,{{0, 0, 0, 0}, {0, 0, -1, 1}}
			,{{-1, 0, 0, 0}, {0, 0, 0, 1}}
			,{{0, 1, 0, 0}, {0, 0, 0, 1}}
			,{{0, 1, 0, 0}, {0, 0, -1, 0}}
			,{{-1, 0, 0, 0}, {0, 0,-1, 0}}};

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int R;
	private static int C;
	private static int limit;
	private static int[][] map;
	private static boolean[][] visited;
	private static Pos start;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		limit = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int solve() throws IOException{
		int ret = 1;
		Queue<Pos> q = new ArrayDeque<>();
		visited[start.row][start.col] = true;
		q.offer(start);

		for (int time = 1; time < limit && !q.isEmpty(); time++) {
			for (int size = q.size() - 1; size >= 0 ; size--) {
				Pos curr = q.poll();
				int tunnel = map[curr.row][curr.col];
				for (int i = 0; i < 4 ; i++) {
					int nr = curr.row + DIR[tunnel][0][i];
					int nc = curr.col + DIR[tunnel][1][i];
					if(!canGo(curr, new Pos(nr, nc)) || !canGo(new Pos(nr, nc), curr)
							   || visited[nr][nc]) continue;
					ret++;
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
				}
			}
		}
		return ret;
	}
	public static boolean canGo(Pos from, Pos to) {
		int row = from.row;
		int col = from.col;
		if(row >= R || row < 0 || col >= C || col < 0)  return false;
		int tunnel = map[row][col];
		for (int i = 0; i < 4 ; i++) {
			int nr = row + DIR[tunnel][0][i];
			int nc = col + DIR[tunnel][1][i];
			if(nr >= R || nr < 0 || nc >= C || nc < 0 || map[nr][nc]== 0)   continue;
			if(nr == to.row && nc == to.col)    return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T ; tc++) {
			init();
			sb.append("#").append(tc).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);
	}
}
