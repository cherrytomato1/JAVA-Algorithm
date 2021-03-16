package algorithm;

import java.util.*;
import java.io.*;

public class GraphSearchList_2 {

	private static class Node {
		int vertex;
		Node next;

		public Node() {
		}

		public Node(int vertex) {
			this.vertex = vertex;
		}
	}

	private static class NodeHead extends Node {
		Node tail;

		public NodeHead() {
			super();
		}

		public void addTail(Node n) {
			if (tail == null) {
				this.next = n;
				this.tail = n;
				System.out.println("new tail " + n.vertex);
				return;
			}
			System.out.println("tail add " + n.vertex);
			tail.next = n;
			tail = n;
		}
	}

	private static final StringBuilder sb = new StringBuilder();

	private static NodeHead[] graph;
	private static int N;
	private static int M;

	private static int init() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		graph = new NodeHead[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new NodeHead();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].addTail(new Node(to));
			graph[to].addTail(new Node(from));
		}
		return start;
	}

	private static void bfs(int start) {
		Deque<Integer> q = new ArrayDeque<>();
		int visited = 1 << start;
		q.offer(start);
		while (!q.isEmpty()) {
			int curr = q.poll();
			sb.append(curr).append(" ");
			System.out.println(graph[curr].next.vertex);
			for (Node temp = graph[curr].next; temp != null; temp = temp.next) {

				int vertex = temp.vertex;
				//System.out.println(vertex);
				if ((visited & 1 << vertex) != 0) continue;
				visited |= 1 << vertex;

				q.offer(vertex);
			}
		}
	}
}
