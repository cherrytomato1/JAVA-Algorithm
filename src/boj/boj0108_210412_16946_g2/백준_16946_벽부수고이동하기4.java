package boj.boj0108_210412_16946_g2;

import java.io.*;
import java.util.*;

public class 백준_16946_벽부수고이동하기4 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

	private static int N;
	private static int M;

	private static boolean[][] map;
	private static int[][] mapCnt;
	private static boolean[][] visited;

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];
		mapCnt = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			char[] in = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = in[j] == '1';
			}
		}
	}

	private static String solve() {
		int flag = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] || visited[i][j])  continue;
				cntRoad(i, j, ++flag);
			}
		}
		return printMap();
	}

	private static void cntRoad(int row, int col, int flag){
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> wallList = new ArrayList<>();
		visited[row][col] = true;
		q.offer(new int[]{row, col});
		int cnt = 0;
		while(!q.isEmpty()){
			cnt++;
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + DIR[0][i];
				int nc = curr[1] + DIR[1][i];
				if(nr >= N || nr < 0 || nc >= M || nc < 0
						   || visited[nr][nc])   continue;
				visited[nr][nc] = true;
				if(map[nr][nc])     wallList.add(new int[]{nr, nc});
				else                q.offer(new int[]{nr, nc});
			}
		}
		setRoadCnt(wallList, cnt);
	}

	private static void setRoadCnt(List<int[]> list, int cnt){
		for (int[] pos : list) {
			mapCnt[pos[0]][pos[1]] += cnt;
			visited[pos[0]][pos[1]] = false;
		}
	}

	private static String printMap() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < M ; j++) {
				if(!map[i][j])  sb.append(0);
				else            sb.append((mapCnt[i][j] + 1) % 10);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
