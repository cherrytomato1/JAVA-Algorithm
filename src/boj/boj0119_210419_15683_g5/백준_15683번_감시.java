package boj.boj0119_210419_15683_g5;

import java.util.*;
import java.io.*;

public class 백준_15683번_감시 {
	private static class Camera {
		int row;
		int col;
		int type;

		public Camera(int row, int col, int type) {
			this.row = row;
			this.col = col;
			this.type = type;
		}
	}
	private static final int[] typeFlag = {0b0001, 0b0101, 0b1001, 0b1101, 0b1111};
	private static final int[][] DIR =  {{0, -1, 0, 1}, {1, 0, -1, 0}};

	private static int N;
	private static int M;
	private static List<Camera> cameraList;
	private static int cameraCount;
	private static int[][] map;
	private static int res;

	private static void init() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cameraList = new ArrayList<>();
		map = new int[N][M];
		res = N * M;

		for (int r = 0; r < N ; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M ; c++) {
				char in = st.nextToken().charAt(0);
				if(in == '6')   {
					map[r][c] = -1;
					res--;
				}
				else if (in >= '1') cameraList.add(new Camera(r, c, in - '1'));
			}
		}
		cameraCount = cameraList.size();
	}

	private static void recur(int idx, int cnt) {
		if (idx == cameraCount) {
			res = Math.min(cnt, res);
			return;
		}

		Camera curr = cameraList.get(idx);
		for (int i = 0; i < 4 ; i++) {
			recur(idx + 1, cnt - countMap(curr, i, idx, true));
			countMap(curr, i, idx, false);
		}
	}

	private static int countMap(Camera curr, int d, int idx, boolean flag) {
		int ret = mapMasking(curr.row, curr.col, idx, flag);
		for (int i = 0; i < 4 ; i++) {
			if((typeFlag[curr.type] & 1 << (i + d) % 4) == 0)    continue;
			int row = curr.row;
			int col = curr.col;
			while (true) {
				row += DIR[0][i];
				col += DIR[1][i];
				if(row >= N || row < 0 || col >= M || col < 0 || map[row][col] == -1)    break;
				ret += mapMasking(row, col, idx, flag);
			}
		}
		return ret;
	}

	private static int mapMasking(int row, int col, int idx, boolean flag) {
		int ret = 0;
		if(flag)    {
			if(map[row][col] == 0)   ret++;
			map[row][col] |= 1 << idx;
		}
		else       map[row][col] &= ~(1 << idx);
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		recur(0, res);
		System.out.println(res);
	}
}
