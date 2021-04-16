package boj.boj0113_210416_17404_g4;

import java.util.*;
import java.io.*;

public class 백준_17404번_RGB거리2 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int[][] d;
	private static int[][] in;

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		d = new int[N][3];
		in = new int[N][3];

		for (int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3 ; j++) {
				in[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int solve() {
		int ret = Integer.MAX_VALUE;
		for (int l = 0; l < 3; l++) {

			for (int i = 0; i < 3; i++) {
				d[0][i] = 0x3fff_ffff;
			}
			d[0][l] = in[0][l];

			dp();
			for (int i = 0; i < 3 ; i++) {
				if(i == l)  continue;
				ret = Math.min(d[N - 1][i], ret);
			}
		}
		return ret;
	}

	private static void dp(){
		for (int i = 1; i < N ; i++) {
			for (int j = 0; j < 3 ; j++) {
				int val = Integer.MAX_VALUE;
				for (int k = 0; k < 3 ; k++) {
					if(k == j) continue;
					val = Math.min(d[i - 1][k], val);
				}
				d[i][j] = val + in[i][j];
			}
		}
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
