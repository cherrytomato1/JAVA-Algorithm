package swea.solution_SW_2112;

import java.util.*;
import java.io.*;

public class SWEA_2112_보호필름 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static boolean[][] map;
	private static int R;
	private static int C;
	private static int K;
	private static int res;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		res = Integer.MAX_VALUE;
		map = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C ; j++) {
				map[i][j] = st.nextToken().charAt(0) == '1';
			}
		}
	}

	/*
		부분조합 완성 후 맵 생성
	 */
	private static void recur(int idx, int flagA, int flagB, int cnt){
		if(cnt >= res)  return;
		if(idx == R){
			boolean[][] tempMap = copyMap(map);
			for (int i = 0; i < R ; i++) {
				if((flagA & 1 << i) != 0)   Arrays.fill(tempMap[i], false);
				if((flagB & 1 << i) != 0)   Arrays.fill(tempMap[i], true);
			}
			res = check(tempMap) ? cnt : res;
			return;
		}

		recur(idx + 1, flagA, flagB, cnt);
		recur(idx + 1, flagA | 1 << idx, flagB, cnt + 1);
		recur(idx + 1, flagA, flagB | 1 << idx, cnt + 1);
	}

	private static boolean[][] copyMap(boolean[][] map){
		boolean[][] tempMap = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			System.arraycopy(map[i], 0, tempMap[i], 0, C);
		}
		return tempMap;
	}

	/*
		해당 인덱스 맵 복제해가면서 재귀
	 */
	private static void recur2(int idx, boolean[][] tempMap, int cnt){
		if(cnt >= res)  return;
		if(idx == R ){
			res = check(tempMap) ? cnt : res;
			return;
		}

		boolean[] tempRow = Arrays.copyOf(tempMap[idx], tempMap[idx].length);

		Arrays.fill(tempMap[idx], false);
		recur2(idx + 1, tempMap, cnt + 1);
		Arrays.fill(tempMap[idx], true);
		recur2(idx + 1, tempMap, cnt + 1);
		tempMap[idx] = Arrays.copyOf(tempRow, tempRow.length);
		recur2(idx + 1, tempMap, cnt);
	}

	private static boolean check(boolean[][] tempMap) {
		for (int col = 0; col < C ; col++) {
			int cnt = 1;
			boolean flag = false;
			for (int row = 1; row < R ; row++) {
				boolean prev = tempMap[row - 1][col];
				boolean curr = tempMap[row][col];
				if(curr == prev) cnt++;
				else             cnt = 1;
				if(cnt == K)    flag = true;
			}
			if(!flag)       return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T ; tc++) {
			init();
//			recurr(0, 0, 0, 0);
			recur2(0, map, 0);
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
	}
}
