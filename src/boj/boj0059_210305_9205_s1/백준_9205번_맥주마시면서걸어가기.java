package boj.boj0059_210305_9205_s1;

import java.util.*;
import java.io.*;


public class 백준_9205번_맥주마시면서걸어가기 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	private static class Node{
		int row ;
		int col ;
		public Node(int row, int col){
			this.row = row;
			this.col = col;
		}
	}

	static int N;
	static boolean[] visited;
	static Node[] conv;
	static Node house;
	static Node festival;
	static Deque<Node> q ;

	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		conv = new Node[N];
		visited = new boolean[N];
		q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		house = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		q.offer(house);
		for(int i = 0 ; i < N ; i++){
			st = new StringTokenizer(br.readLine());
			conv[i] = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		festival = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
	}


	static boolean bfs(){
		while(!q.isEmpty()){
			Node curr = q.poll();
			if(getDist(curr, festival) <= 1000) return true;
			for(int i = 0 ; i < N; i++){
				if(visited[i])  continue;
				if(getDist(curr, conv[i]) > 1000) continue;

				visited[i] = true;
				q.offer(conv[i]);
			}
		}
		return false;
	}

	static int getDist(Node n1, Node n2){
		return Math.abs(n1.row - n2.row) + Math.abs(n1.col - n2.col);
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++){
			init();
			sb.append(bfs() ? "happy" : "sad").append("\n");
		}
		System.out.println(sb.toString());
	}

}
