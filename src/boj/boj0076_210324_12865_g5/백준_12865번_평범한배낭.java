package boj.boj0076_210324_12865_g5;

import java.util.*;
import java.io.*;

public class 백준_12865번_평범한배낭 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int K;
	private static int[][] weight;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		weight = new int[N + 1][K + 1];
	}

	private static int solve() throws IOException{
		//매 아이템 별로 반복
		for(int item = 1 ; item <= N; item++){
			st = new StringTokenizer(br.readLine());
			int iW = Integer.parseInt(st.nextToken());
			int iV = Integer.parseInt(st.nextToken());

			for(int tW = 1; tW <= K ; tW++){
				//현재 아이템을 넣는지 안넣는지에 따라 가치가 높은 것을 현재 아이템-무게 테이블에 넣는다.
				if(iW <= tW)    weight[item][tW] = Math.max(weight[item - 1][tW - iW] + iV, weight[item - 1][tW]);
				else            weight[item][tW] = weight[item - 1][tW];
			}
		}
		return weight[N][K];
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
