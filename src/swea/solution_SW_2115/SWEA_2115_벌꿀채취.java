package swea.solution_SW_2115;

import java.util.*;
import java.io.*;

public class SWEA_2115_벌꿀채취 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int M;
	private static int C;

	private static int[][] map;
	private static int[][] d;

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		d = new int[N][N];
		map = new int[N][N];
		for (int r = 0; r < N ; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N ; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int solve() {
		int ret = 0;
		setMaxValue();
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c <= N - M ; c++) {
				ret = Math.max(getMaxValue(r, c) + d[r][c], ret);
			}
		}
		return ret;
	}

	static int subsetRes ;
	private static void setMaxValue() {
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c <= N - M ; c++) {
				subsetRes = 0;
				subset(r, 0, c, 0);
				d[r][c] = subsetRes;
			}
		}
	}

	private static void subset(int row, int idx, int startIdx, int flag) {
		if (idx == M) {
			int val = 0;
			int val2 = 0;
			for (int i = 0; i < M; i++) {
				if((flag & 1 << i) == 0) continue;
				val += map[row][startIdx + i];
				val2 += map[row][startIdx + i] * map[row][startIdx + i];
			}
			if(val > C)  return ;
			subsetRes = Math.max(subsetRes, val2);
			return;
		}
		subset(row, idx + 1, startIdx, flag | 1 << idx);
		subset(row, idx + 1, startIdx, flag);
	}

	private static int getMaxValue(int row, int col) {
		int ret = 0;
		for (int r = 0; r < N ; r++) {
			for (int c = 0; c <= N - M; c++) {
				if(r == row && Math.abs(col - c) <= M) continue;
				ret = Math.max(ret, d[r][c]);
			}
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T ; tc++) {
			init();
			sb.append("#").append(tc).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);
	}
}
