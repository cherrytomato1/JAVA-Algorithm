package boj.boj0107_210412_2098_g1;

import java.util.*;
import java.io.*;

public class 백준_2098번_외판원순회 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static final int MAX = 0x3fff_ffff;
	private static int N;
	private static int start;

	private static int[][] adj;
	private static int[][] d;

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		adj = new int[N][N];
		// 정점의 수 N을 담을 수 있는 배열 크기할당
		// ex) N == 4 , 모든 정 방문시 0b1111  1 << N == 0b1_0000
		d = new int[N][1 << N];

		//인접행렬 받기
		for (int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N ; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	/*
		@params curr   현재 방문한 정점
		@params flag    현재까지 방문한 기록들 ( 현재 정점 포함 )
	 */
	private static int TSP(int curr, int flag){
		// 모든 정점 방문 시 (플래그값이 최댓값)
		// 처음 시작했던 정점에 도달 가능 시 그 값 반환( top - down ) 아니면 불가능값 MAX 반환
		if(flag + 1 == 1 << N ) return adj[curr][start] == 0 ? MAX : adj[curr][start];
		// 현재 정점 및 그 정점에 도달하는 경로의 최소 가중치가 이미 초기화되어 있을 경우
		if(d[curr][flag] != 0)  return d[curr][flag];

		// 해당 정점 및 경로에 대한 최소가중치설정 변수
		int ret = MAX;
		for (int i = 0; i < N; i++) {
			//이미 경로에 포함되어 있거나, 방문 불가능한 경우 통과
			if((flag & 1 << i) != 0 || adj[curr][i] == 0)    continue;
			//지금까지 최소가중치와 선택정점 및 선택 경로 + 선택정점과 현재 정점 거리 중 최솟값으로 갱신
			ret = Math.min(ret, TSP(i, flag | 1 << i) + adj[curr][i]);
		}
		//갱신된 값을 메모제이션 및 반환
		return d[curr][flag] = ret;
	}

	public static void main(String[] args) throws IOException {
		init();
		start = 0;
		System.out.println(TSP(start, 1 << start));
	}
}
