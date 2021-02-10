package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Tank {
	int row ;
	int col ;
	int dir ;
	
	public Tank(int dir, int row, int col) {
		this.dir = dir;
		this.row = row;
		this.col = col;
	}
}


public class Solution_D3_1873_상호의배틀필드 {
	static char[][] map;
	private static char[] order;
	private static Tank tank;
	
	private static final int[] dr = { -1, 1, 0, 0};
	private static final int[] dc = { 0, 0, -1, 1};
	
	private static int T;
	private static int H;
	private static int W;
	private static int N;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	
	private static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		
		for(int r = 0 ; r < H ; r ++ ) {
			String str = br.readLine();
			for(int c = 0 ; c < W; c++) {
				map[r][c] = str.charAt(c);
				if(map[r][c] == '^' || map[r][c] == 'v' || map[r][c] == '<' || map[r][c] == '>') {
					tank = new Tank(returnDir(map[r][c]), r, c);
				}
			}
		}
		
		N = Integer.parseInt(br.readLine());
		order = br.readLine().toCharArray();
	}
	
	private static int returnDir(char dir) {
		switch(dir) {
		case 'U' : case '^' :
			return 0;
		case 'D' : case 'v' :
			return 1;
		case 'L' : case '<' :
			return 2;
		case 'R' : case '>' :
			return 3;
		}
		return -1;
	}
	
	private static char returnDir(int dir) {
		switch(dir) {
		case 0 :
			return '^';
		case 1 :
			return 'v';
		case 2 :
			return '<';
		case 3 :
			return '>';
		}
		return '.';
	}
	
	private static void order(char order) {
		switch(order) {
		case 'U' :
		case 'D' :
		case 'L' :
		case 'R' :
			move(returnDir(order));
			break;
		case 'S' :
			shot();
		}
	}
	
	private static void move(int dir) {
		tank.dir = dir;
		int nr = tank.row + dr[tank.dir];
		int nc = tank.col + dc[tank.dir];
		
		if(nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] != '.') {
			map[ tank.row ][ tank.col ] = returnDir(tank.dir);
			return;
		}
			
		map[ tank.row ][ tank.col ] = '.';
		map[ tank.row = nr ][ tank.col = nc ] = returnDir(tank.dir);
	}
	
	private static void shot() {
		for(int i = 1, nr, nc ; ; i++) {
			nr = tank.row + dr[tank.dir] * i;
			nc = tank.col + dc[tank.dir] * i;
			
			if(nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == '#') break;
			
			if(map[nr][nc] == '*') {
				map[nr][nc] = '.';
				break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(br.readLine());
		StringBuilder sb;
		
		for(int tc = 1 ; tc <= T ; tc++) {
			input();
			for(int i = 0; i < N ; i++) {
				order(order[i]);
			}
			sb = new StringBuilder("#" + tc + " ");
			for(int i = 0; i < H ; i++) {
				sb.append(map[i]).append("\n");
			}
			System.out.print(sb);
		}
		
	}
}
