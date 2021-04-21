package boj.boj0121_210421_2458_g4;

import java.util.*;
import java.io.*;

public class 백준_2458번_키순서 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static boolean[][] adjArr;
	private static int N;
	private static int M;

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjArr = new boolean[N][N];

		for (int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			adjArr[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}
	}

	private static int solve() {
		int ret = 0;
		floydWarshall();
		for (int i = 0; i < N ; i++) {
			boolean flag = true;
			for (int j = 0; j < N; j++) {
				if(i == j)  continue;
				flag &= (adjArr[i][j] || adjArr[j][i]);
			}
			if(flag)    ret++;
		}
		return ret;
	}

	private static void floydWarshall() {
		//경로
		for (int k = 0; k < N ; k++) {
			//출발
			for (int i = 0; i < N; i++) {
				//도착
				for (int j = 0; j < N; j++) {
					adjArr[i][j] |= adjArr[i][k] && adjArr[k][j];
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{

		init();
		System.out.println(solve());
	}
}
