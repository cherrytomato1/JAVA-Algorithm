package boj.boj0097_210409_8972_g4;

import java.util.*;
import java.io.*;

public class 백준_8972번_미친아두이노 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final int[][] DIR = {{0, 1, 1, 1, 0, 0, 0, -1, -1 ,-1},
										{0, -1, 0, 1, -1, 0, 1, -1, 0, 1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	private static StringTokenizer st;

	private static int R;
	private static int C;

	private static int[][] map;
	private static int[] jonsu;
	private static char[] commands;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			char[] in = br.readLine().toCharArray();
			for (int j = 0; j < C ; j++) {
				if(in[j] == 'R')        map[i][j]++;
				else if(in[j] == 'I')   jonsu = new int[]{i, j};
			}
		}

		commands = br.readLine().toCharArray();
	}

	private static int solve(){
		for(int i = 0, size = commands.length; i < size; i++){

			moveJongsu(commands[i] - '0');
			if(!checkmap(true))  return ++i;
			findCrazy();
			if(!checkmap(false))  return ++i;
//			printMap();
		}
		printMap();
		return -1;
	}

	private static boolean checkmap(boolean type){
		if(map[jonsu[0]][jonsu[1]] != 0)    return false;
		if(type)                            return true;
		for (int i = 0; i < R ; i++) {
			for (int j = 0; j < C ; j++) {
				if(map[i][j] <= 1)  continue;
				map[i][j] = 0;
			}
		}
		return true;
	}

	private static void moveJongsu(int order){
//		System.out.print(order);
		int nr = jonsu[ROW] + DIR[ROW][order];
		int nc = jonsu[COL] + DIR[COL][order];
//		if(nr < 0 || nr >= R || nc < 0 || nc >= C)  return;
		jonsu[ROW] = nr;
		jonsu[COL] = nc;
	}

	private static void findCrazy(){
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < R ; i++) {
			for (int j = 0 ; j < C ; j++){
				if(map[i][j] == 1)  q.add(new int[]{i, j});
			}
		}

		while(!q.isEmpty()){
			int[] crazy = q.poll();
			moveCrazy(crazy[ROW], crazy[COL]);
		}
	}

	private static void moveCrazy(int r, int c){
		int dist = Integer.MAX_VALUE;
		int nr = r;
		int nc = c;

//		System.out.println(map[r][c] + " is crazy");
		for(int i = 1; i < 10 ; i++){
			int nextDist = getDistance(r + DIR[ROW][i], c + DIR[COL][i]);
			if(nextDist > dist) continue;
			dist = nextDist;
			nr = r + DIR[ROW][i];
			nc = c + DIR[COL][i];
		}
//		System.out.println(nr +" , " + nc + "  || " + r + " , " + c);
		map[r][c]--;
		map[nr][nc]++;
	}

	private static int getDistance(int r, int c){
		return Math.abs(jonsu[ROW] - r) + Math.abs(jonsu[COL] - c);
	}

	private static void printMap(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R ; i++) {
			for (int j = 0; j < C ; j++) {
				if(jonsu[ROW] == i && jonsu[COL] == j){
					sb.append("I");
					continue;
				}
				sb.append(map[i][j] == 0 ? "." : "R");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException{
		init();
		int res = solve();
		if(res != -1)   System.out.println("kraj " + res);

	}
}
/*

3 3
...
.I.
...


 */