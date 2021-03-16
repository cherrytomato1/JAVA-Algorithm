package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
	연결리스트를 통한 그래프 BFS 구현
 */
public class GraphSearchList {
	private static class Node{
		int vertex;
		Node next;
		public Node(int vertex, Node next){
			super();
			this.vertex = vertex;
			this.next = next;
		}
		public Node(int vertex){
			super();
			this.vertex = vertex;
		}
	}
	static Deque<Integer> q;
	static StringTokenizer st;

	static Node[] adjList;
	static int N;
	static int M;

	static void in() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjList = new Node[N];
		q = new ArrayDeque<>();

		for(int i = 0 ; i < M; i ++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			System.out.println(from);
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
	}

	static void bfs(int start){
		boolean[] visited = new boolean[N];;
		q.offer(start);
		visited[start] = true;

		while(!q.isEmpty()) {
			int curr = q.poll();
			System.out.println(curr);

			for(Node temp = adjList[curr]; temp != null; temp = temp.next){
				if(visited[temp.vertex])    continue;
				System.out.println("잉잉");
				visited[temp.vertex] = true;
				q.offer(temp.vertex);
			}

//			while(adjList[curr] != null){
//				int next = adjList[curr].next.vertex;
//				curr = next;
//				if(visited[curr])   continue;
//				System.out.println("낑낑");
//				visited[curr] = true;
//				q.offer(adjList[curr].vertex);
//			}
		}
	}

	static void dfs(int curr, int visited){
		visited |= 1 << curr;
		System.out.println(curr);
		System.out.println("뀨웅");

		for(Node temp = adjList[curr]; temp != null; temp = temp.next){
			if((visited & (1 << temp.vertex)) != 0)   continue;
			dfs(temp.vertex, visited);
		}
	}
}
