package jungol;

import java.util.*;
import java.io.*;

public class Main_1661_미로탈출로봇 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int ROW = 1;
	static final int COL = 0;
	static final int[] dr = { -1, 1, 0, 0};
	static final int[] dc = { 0, 0, -1, 1};
	
	static int N;
	static int M;
	
	static int[] end;
	static boolean[][] map;
	static Queue<int[]> q = new ArrayDeque<>();
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		
		q.offer(new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1});
		end = new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
		
		for(int i = 0 ; i < N ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = str.charAt(j) == '1';
			}
		}
	}
	
	static int f() {
		int cnt = 0;
		while(!q.isEmpty()) {
			cnt++;
			for(int size = q.size(); size > 0 ; size--) {
				int[] pos = q.poll();
				
				for(int i = 0 ; i < 4 ; i++) {
					int nr = pos[ROW] + dr[i];
					int nc = pos[COL] + dc[i];
					
					if(nr >= N || nr < 0 || nc >= M || nc < 0 || map[nr][nc])	continue;
					if(nr == end[ROW] && nc == end[COL])	return cnt;
					
					map[nr][nc] = true;
					q.offer(new int[] { nc, nr });
					
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException{
		input();
		System.out.println(f());
	}
}
