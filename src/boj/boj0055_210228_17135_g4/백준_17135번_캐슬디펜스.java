package boj.boj0055_210228_17135_g4;

import java.util.*;
import java.io.*;

public class 백준_17135번_캐슬디펜스 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static boolean[][] map;
	static boolean[][] killed;
	static int[] archer;
	static int[] selected;

	static int N;
	static int M;
	static int D;

	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];
		archer = new int[3];
		selected = new int[M];

		for(int r = 0; r < N; r++){
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < M ; c++){
				map[r][c] = st.nextToken().equals("1");
			}
		}

		for(int i = M - 1 ; i >= M - 3 ; i--){
			selected[i] = 1;
		}
	}

	static int solve(){
		int res = Integer.MIN_VALUE;
		do{
			for(int i = 0, j = 0 ; i < M ; i++){
				if(selected[i] == 1) archer[j++] = i;
			}
			int cnt = game();
			res = Math.max(cnt, res);
		}while(np());
		return res;
	}

	static int check(boolean[][] killed){
		int cnt = 0;
		for(int r = 0 ; r < N ;r ++){
			for(int c = 0 ; c < M ;c++){
				if(killed[r][c]) cnt++;
			}
		}
		return cnt;
	}

	static int game(){
		killed = new boolean[N][M];
		int[][] killPos = new int[3][];
		for (int row = N; row > 0; row--) {
			for(int i = 0 ; i < 3; i++) {
				killPos[i] = null;
				kill(row, i, killPos);
			}

			for(int i = 0 ; i < 3; i++) {
				if (killPos[i] != null) {
					killed[killPos[i][0]][killPos[i][1]] = true;
				}
			}
		}

		return check(killed);
	}

	static void kill(int row, int i, int[][] killPos){
		for(int r = row - 1; r >= row - 1 - D && r >= 0; r--){
			for(int c = Math.max(archer[i] - D, 0); c < archer[i] + D && c < M; c++ ){

				int dist = Math.abs(row - r) + Math.abs(archer[i] - c);
				if(!map[r][c] || killed[r][c] || dist > D)  continue;

				if(killPos[i] == null){
					killPos[i] = new int[]{r, c, dist};
				}else if (dist < killPos[i][2] || (dist == killPos[i][2] && c < killPos[i][1])){
					killPos[i][0] = r;
					killPos[i][1] = c;
					killPos[i][2] = dist;
				}
			}
		}
	}

	static boolean np(){
		int i = M - 1;
		while(i > 0 && selected[i - 1] >= selected[i])  i--;
		if(i == 0)  return false;

		int j = M - 1;
		while(selected[i - 1] >= selected[j])   j--;
		idxSwap(i - 1, j);

		int k = M - 1;
		while(k > i){
			idxSwap(i++, k--);
		}
		return true;
	}

	static void idxSwap(int idx1, int idx2){
		int temp = selected[idx1];
		selected[idx1] = selected[idx2];
		selected[idx2] = temp;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
