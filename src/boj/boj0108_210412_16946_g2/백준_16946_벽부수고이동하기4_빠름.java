package boj.boj0108_210412_16946_g2;

import java.io.*;
import java.util.*;

public class 백준_16946_벽부수고이동하기4_빠름 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

	private static int N;
	private static int M;

	private static boolean[][] map;
	private static int[][] mapCnt;
	private static int[][] mapFlag;

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];
		mapCnt = new int[N][M];
		mapFlag = new int[N][M];

		for (int i = 0; i < N; i++) {
			char[] in = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = in[j] == '1';
			}
		}
	}

	private static String solve() {
		int flag = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] || mapFlag[i][j] != 0)  continue;
				list.add(cntRoad(i, j, ++flag));
			}
		}

		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] && mapCnt[i][j] == 0) cntWall(i, j, list);
			}
		}
		return printMap();
	}

	private static int cntRoad(int row, int col, int flag){
		Queue<int[]> q = new ArrayDeque<>();
		mapFlag[row][col] = flag;
		q.offer(new int[]{row, col});
		int cnt = 0;
		while(!q.isEmpty()){
			cnt++;
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + DIR[0][i];
				int nc = curr[1] + DIR[1][i];
				if(nr >= N || nr < 0 || nc >= M || nc < 0
						   || map[nr][nc] || mapFlag[nr][nc] != 0)   continue;
				mapFlag[nr][nc] = flag;
				q.offer(new int[]{nr, nc});
			}
		}
		return cnt;
	}

	private static void cntWall(int row, int col, List<Integer> list){
		int cnt = 1;
		int[] flags = new int[4];

		loop :
		for (int i = 0; i < 4 ; i++) {
			int nr = row + DIR[0][i];
			int nc = col + DIR[1][i];
			if (nr >= N || nr < 0 || nc >= M || nc < 0 || map[nr][nc]) continue;
			for (int j = 0; j < i ; j++) {
				if(mapFlag[nr][nc] == flags[j]) continue loop;
			}
			flags[i] = mapFlag[nr][nc];
			cnt += list.get(mapFlag[nr][nc] - 1);
		}
//		System.out.println(cnt);
		mapCnt[row][col] = cnt;
	}

	private static String printMap() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < M ; j++) {
				if(!map[i][j])  sb.append(0);
				else            sb.append(mapCnt[i][j] % 10);
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
