package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D2_1954_달팽이숫자 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T;
	static int N;
	static int[][] arr;
	
	/**					우  하  좌  상   */ 
	static int[] dr = { 0, 1, 0, -1};
	static int[] dc = { 1, 0, -1, 0};
	
	private static void solve(){
		int dir = 0;
		int row = 0;
		int col = 0;
		int nr = 0;
		int nc = 0;
		
		for(int cnt = 1 ; cnt <= N * N ; cnt++, nr = row + dr[dir], nc = col + dc[dir]) {
			if( nr < 0 || nr >= N || nc < 0 || nc >= N || arr[nr][nc] != 0) {
				dir = (dir + 1) % 4;
				nr = row + dr[dir];
				nc = col + dc[dir];
			}
			row = nr;
			col = nc;
			arr[row][col] = cnt;
		}
	}
	
	public static void solve(int row, int col, int dir, int cnt) {
		if(cnt > N * N) {
			return ;
		}
		arr[row][col] = cnt;
		int nr = row + dr[dir];
		int nc = col + dc[dir];
		if( nr < 0 || nr >= N || nc < 0 || nc >= N || arr[nr][nc] != 0) {
			dir = (dir + 1) % 4;
			nr = row + dr[dir];
			nc = col + dc[dir];
		}
		
		solve(nr, nc, dir, cnt+1);
	}
	
	
	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
//			solve();
			solve(0, 0, 0, 1);
			
			System.out.println("#" +tc);
			for(int r = 0 ; r < N ; r ++) {
				for (int c = 0 ; c < N ; c++) {
					System.out.print(arr[r][c] + " ");
				}
				System.out.println();
			}
		}
	}
}
