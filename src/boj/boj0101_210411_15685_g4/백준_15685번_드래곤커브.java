package boj.boj0101_210411_15685_g4;

import java.io.*;
import java.util.*;

public class 백준_15685번_드래곤커브 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static final int[][] DIR = {{0, -1, 0, 1}, {1, 0, -1, 0}};
	private static final int ROW_MAX = 101;
	private static final int COL_MAX = 101;

	private static int N;
	private static boolean[][] visited;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		visited = new boolean[ROW_MAX][COL_MAX];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			makeCurve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
	}

	private static void makeCurve(int col, int row, int dir, int gen){
		List<Integer> dirList = makeDir(dir, gen);
		visited[row][col] = true;
		for (int d : dirList) {
			visited[row += DIR[0][d]][col += DIR[1][d]] = true;
		}
	}

	private static List<Integer> makeDir(int dir, int gen){
		List<Integer> list = new ArrayList<>();
		list.add(dir);
		for (int i = 0; i < gen; i++) {
			for (int j = list.size() - 1; j >= 0; j--) {
				list.add((list.get(j) + 1) % 4);
			}
		}
		return list;
	}

	private static int solve(){
		int ret = 0;
		for (int i = 0; i < ROW_MAX - 1 ; i++) {
			for (int j = 0; j < COL_MAX - 1 ; j++) {
				if(visited[i][j] && visited[i][j + 1]
						   && visited[i + 1][j] && visited[i + 1][j +1]) ret ++;
			}
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}