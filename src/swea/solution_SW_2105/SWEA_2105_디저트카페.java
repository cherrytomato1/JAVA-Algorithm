package swea.solution_SW_2105;

import java.util.*;
import java.io.*;

public class SWEA_2105_디저트카페 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static final int[][] DIR = {{-1, -1, 1, 1}, {-1, 1, 1, -1}};

	private static int N;
	private static int[][] map;
	private static boolean[] visited;
	private static int[] start;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[101];
		res = 0;
		for (int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int res;
	private static void solve() {
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N ; j++) {
				start = new int[]{i, j};
				dfs(i, j, 0, 2, true, 3);
				dfs(i, j, 0, 3, false, 3);
			}
		}
	}

	/*
		@params row, col    현재 탐색중인 좌표
		@params cnt         탐색중인 깊이
		@params dir         현재 탐색중인 방향
		@params type        시계방향인지 반시계 방향인지
		@params dirCnt      남은 방향전환 횟수
	 */
	private static void dfs(int row, int col, int cnt, int dir, boolean type, int dirCnt){
		if(row >= N || row < 0 || col >= N || col < 0)  return;
		if(cnt != 0 && row == start[0] && col == start[1]){
			res = Math.max(res, cnt);
			return;
		}
		if(visited[map[row][col]])  return;
		visited[map[row][col]] = true;
		dfs(row + DIR[0][dir], col + DIR[1][dir], cnt + 1, dir, type, dirCnt);
		if(dirCnt != 0) {
			dir = (dir + 4 + (type ? -1 : 1)) % 4;
			dfs(row + DIR[0][dir], col + DIR[1][dir], cnt + 1, dir, type, dirCnt - 1);
		}
		visited[map[row][col]] = false;
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T ; tc++) {
			init();
			solve();
			sb.append("#").append(tc).append(" ").append(res == 0 ? -1 : res).append("\n");
		}
		System.out.println(sb);
	}
}
