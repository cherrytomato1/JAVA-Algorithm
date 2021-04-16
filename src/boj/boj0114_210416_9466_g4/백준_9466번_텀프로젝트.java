package boj.boj0114_210416_9466_g4;

import java.io.*;
import java.util.*;

public class 백준_9466번_텀프로젝트 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int[] students;
	private static boolean[] visited;
	private static int N;

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		students = new int[N + 1];
		visited = new boolean[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N ; i++) {
			students[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static int solve() {
		for (int i = 1; i <= N ; i++) {
			if(students[i] > 0) dfs(i);
		}
		int ret = 0;
		for (int i = 1; i <= N ; i++) {
			//사이클 밖 친구들 찾기
			ret += students[i] == -1 ? 1 : 0;
		}
		return ret;
	}

	/*
		student[x] == 0 -> 팀있음
		student[x] == -1 -> 팀없찐
	 */
	private static void dfs(int x){
		//이미 탐색이 끝난 친구
		if(students[x] < 1)   return;
		//자기 자신 참조 / 혼자 사이클만들기
		if(students[x] == x){
			students[x] = 0;
			return ;
		}
		//이미 방문하고 초기화가 안되었는데 또 방문한경우
		//이 경우는 사이클이 존재해서 이 사이클에 있는 모든 정점을 방문처리해야함
		if(visited[x] && students[x] >= 1){
			int go = students[x];
			students[x] = 0;
			dfs(go);
			return;
		}
		//첫방문 ^.^
		visited[x] = true;
		dfs(students[x]);
		//사이클을 찾아서 그 안에서 값이 변경되면
		if(students[x] == 0)    return;
		//못찾음
		students[x] = -1;
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T ; tc++) {
			init();
			sb.append(solve()).append("\n");
		}
		System.out.println(sb);
	}
}
