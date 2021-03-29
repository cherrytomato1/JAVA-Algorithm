package boj.boj0092_210329_2629_g2;

import java.util.*;
import java.io.*;

public class 백준_2629번_양팔저울 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	//가능한 무게들을 저장하는 배열입니다.
	private static boolean[][] d;
	//추 개수
	private static int N;
	private static int M;
	//최대의 무게값입니다.
	private static final int MAX_WEIGHT = 40000;

	private static void init() throws IOException {

		N = Integer.parseInt(br.readLine());
		// 저장할 무게값들의 배열입니다. 추의 개수, 최대 무게 수
		d = new boolean[N][MAX_WEIGHT + 1];

		st = new StringTokenizer(br.readLine());
		// 첫번째로 들어오는 추는 가능한 경우가 자기 자신밖에 없습니다.
		d[0][Integer.parseInt(st.nextToken())] = true;

		for(int i = 1 ; i < N; i++) {
			// 추 받기
			int w = Integer.parseInt(st.nextToken());
			// 최대 무게까지, 이전추들을 검사하며 가능했던 모든 경우에 대해 연산합니다.
			for(int j = 0; j <= MAX_WEIGHT; j++) {
				//현재 갱신하려고 하는 무게가 사용 불가능했던 무게라면 건너뜁니다.
				if(!d[i - 1][j])		continue;

				//가능했던 무게 중 새로 입력된 추가 반대로 놓이는 경우가 가능하다면 true
				if(j - w >= 0)			d[i][j - w] = true;
				//가능했던 무게 중 새로 입력된 추가 반대로 놓이는데 그 경우의 반대 경우도 true
				if(w - j >= 0)			d[i][w - j] = true;
				//같은 곳에 현재 추를 놓을 수 있다면 가능
				if(j + w <= MAX_WEIGHT)	d[i][j + w] = true;
				d[i][j] = true;
			}
			//현재 추를 true
			d[i][w] = true;
		}
	}

	private static String solve() throws IOException{
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < M; i++){
			int in = Integer.parseInt(st.nextToken());

			//맨 마지막 추까지 놓은 경우 가능하다면 Y 출력
			if(d[N - 1][in])	sb.append("Y ");
			else				sb.append("N ");
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
