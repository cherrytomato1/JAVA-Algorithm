package algorithm;

import java.util.*;
import java.io.*;

/*
	인접행렬을 통한 그래프 BFS 구현
 */
public class GraphSearchAdjMatrix {

	static Deque<Integer> q;
	static StringTokenizer st;

	static boolean[][] graph;
	static int N;

	static void in() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new boolean[N][N];
		for(int i = 0 ; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph[from][to] = true;
			graph[to][from] = true;
		}
	}

	static void bfs(int start){
		boolean[] visited = new boolean[N];;
		q.offer(start);
		visited[start] = true;

		while(!q.isEmpty()) {
			int curr = q.poll();
			for (int i = 0; i < N; i++) {
				if (!graph[curr][i] || visited[i])  continue;
				visited[i] = true;
				q.offer(i);
			}
		}
	}

	static void dfs(int curr, int visited){
		visited |= 1 << curr;

		for(int i = 0 ; i < N ; i++){
			if((visited & 1 << curr) != 0) continue;
			dfs(i, visited);
		}
	}
}
