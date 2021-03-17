package boj.boj0067_210317_1260_s2;

import java.util.*;
import java.io.*;

public class 백준_1260_DFS와BFS {


	private static final StringBuilder sb = new StringBuilder();

	private static PriorityQueue<Integer>[] pq;
	private static int N;
	private static int M;

	private static int init() throws IOException{
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		pq = new PriorityQueue[N];

		for(int i = 0 ; i < M ; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
		}
		return start;
	}

	private static void bfs(int start){
		Deque<Integer> q = new ArrayDeque<>();
		int visited = 1 << start;
		q.offer(start);
		while(!q.isEmpty()){
			int curr = q.poll();
			sb.append(curr).append(" ");
//			System.out.println(graph[curr].next.vertex);
//			for(Node temp = graph[curr].next; temp != null; temp = temp.next){
//
//				int vertex = temp.vertex;
//				//System.out.println(vertex);
//				if((visited & 1 << vertex) != 0)   continue;
//				visited |= 1 << vertex;
//
//				q.offer(vertex);
//			}
		}
	}
	public static void main(String[] args) throws IOException{
		int start = init();
		bfs(start);
		System.out.println(sb.toString());
	}
}
