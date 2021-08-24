package boj.boj0169_210824_1242_g2;

import java.util.*;
import java.io.*;

public class 백준_1242번_소풍 {
	private static int N;
	private static int K;
	private static int M;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()) - 1;
	}

	private static int solve() {
		//break 가 for 루프 내에 있기 때문에 1 추가
		int res = 1;

		for(int target = -1 ; N != -1; res++){
			//현재 나가는 사람은 현재 위치 + k에서 전체 인원의 마지막
			target = (target + K) % N--;

			if (target == M) {
				break;
			}
			//해당 위치의 학생이 나갔으므로 다음 시작위치 -1
			if (target-- < M) {
				//동우의 위치 앞의 학생이 사라지면 동우의 위치 -1
				M--;
			}
		}
		return res;
	}
}
