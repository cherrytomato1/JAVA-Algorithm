package jungol;

import java.util.*;
import java.io.*;

public class Main_1661_미로탈출로봇_dfs {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int ROW = 1;
	static final int COL = 0;
	static final int[] dr = { 0, 1, 0, -1};
	static final int[] dc = { 1, 0, -1, 0};

	static int N;
	static int M;
	
	static int[] start;
	static int[] end;
	static boolean[][] map;
	static int[][] visited;
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		visited = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		
		start = new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
		end = new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
		
		for(int i = 0 ; i < N ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = str.charAt(j) == '1';
			}
		}
	}
	static int res = Integer.MAX_VALUE;
	static void f(int row, int col, int cnt) {
		if(cnt >= res) return ;
//		if(visited[row][col] <= cnt && visited[row][col] != 0) 	return;
		
		if(row == end[ROW] && col == end[COL]) {
			res = res < cnt ? res : cnt;
			return;
		}
		
//		visited[row][col] = cnt ;
		
		for(int i = 0 ; i < 4 ; i ++) {
			int nr = row + dr[i];
			int nc = col + dc[i];

			
			if(nr >= N || nr < 0 || nc >= M || nc < 0 || map[nr][nc]) 	continue;
			if(visited[nr][nc] <= cnt + 1 && visited[nr][nc] != 0) 		continue;
			visited[nr][nc] = cnt ;
			
			f(nr, nc, cnt + 1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		input();
		f(start[ROW], start[COL], 1);
		System.out.println(res - 1);
	}
}
