package boj.boj0025_210209_7576_s1;

import java.io.*;
import java.util.*;

public class 백준_7576번_토마토 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

	private static int R;
	private static int C;
	private static int[][] map;
	private static int targetCnt;

	private static final Queue<int[]> q = new ArrayDeque<>();

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		for (int i = 0; i < R ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)          q.offer(new int[]{i, j});
				else if (map[i][j] == 0)    targetCnt++;
			}
		}
	}

	private static int bfs(){
		int ret = 0;
		while (!q.isEmpty() && targetCnt != 0){
			ret++;
			for (int i = 0, size = q.size(); i < size ; i++) {
				int[] curr = q.poll();
				for (int j = 0; j < 4 ; j++) {
					int nr = curr[0] + DIR[0][j];
					int nc = curr[1] + DIR[1][j];
					if(nr >= R || nr < 0 || nc >= C || nc < 0 || map[nr][nc] != 0)    continue;
					map[nr][nc] = 1;
					targetCnt--;
					q.offer(new int[]{nr, nc});
				}
			}
		}
		return targetCnt == 0 ? ret : -1;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(bfs());
	}
}
