package boj.boj0071_210319_2533_g3;

import java.util.*;
import java.io.*;

public class 백준_2533번_사회망서비스_dfs {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static List<List<Integer>> list;
	private static int N;
	private static boolean[] visited;

	private static void init() throws IOException {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>(N);
		visited = new boolean[N + 1];

		for(int i = 0 ; i <= N ; i++){
			list.add(new ArrayList<>());
		}
		for(int i = 0 ; i < N - 1 ; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).add(to);
			list.get(to).add(from);
		}
	}
	static int cnt;
	private static boolean dfs(int node){
		visited[node] = true;
		boolean isEarly = false;
		for(int go : list.get(node)){
			if(visited[go]) continue;
			if(!dfs(go)) isEarly = true;
		}
		if(isEarly) cnt++;
		return isEarly;
	}

	public static void main(String[] args) throws IOException{
		init();
		dfs(1);
		System.out.println(cnt);
	}
}
