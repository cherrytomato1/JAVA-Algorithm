package boj.boj0099_210411_16724_g2;

import java.util.*;
import java.io.*;

public class 백준_16724번_피리부는사나이 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static Map<Character, int[]> dirMap;
	private static final int[][] VALUES = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static final char[] KEYS = {'U', 'D', 'L', 'R'};

	private static int N;
	private static int M;
	private static char[][] map;
	private static int[][] visited;

	private static void init() throws IOException {
		dirMap = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			dirMap.put(KEYS[i], VALUES[i]);
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N ; i++) {
			map[i] = br.readLine().toCharArray();
		}
	}

	private static int solve(){
		int ret = 0 ;
		int cnt = 0 ;
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] != 0) continue;
				ret += dfs(i, j, ++cnt) ? 0 : 1;
			}
		}
		return ret;
	}

	private static boolean dfs(int row, int col, int cnt){
		if(visited[row][col] == cnt)    return false;
		if(visited[row][col] != 0)      return true;
		visited[row][col] = cnt;

		int nr = row + dirMap.get(map[row][col])[0];
		int nc = col + dirMap.get(map[row][col])[1];
		return dfs(nr, nc, cnt);
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
