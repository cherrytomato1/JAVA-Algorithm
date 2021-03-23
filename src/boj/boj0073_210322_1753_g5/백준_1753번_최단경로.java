package boj.boj0073_210322_1753_g5;

import java.util.*;
import java.io.*;

public class 백준_1753번_최단경로 {

	private static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge e){
			return Integer.compare(this.weight, e.weight);
		}
	}

	private static class Vertex implements Comparable<Vertex>{
		int vertex;
		int dist;
		public Vertex(int vertex, int dist){
			this.vertex = vertex;
			this.dist = dist;
		}
		@Override
		public int compareTo(Vertex v){
			return Integer.compare(this.dist, v.dist);
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int V;
	private static int E;
	private static int startVertex;

	private static List<List<Edge>> edgeList = new ArrayList<>();
	private static int[] dist;
	private static boolean[] visited;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startVertex = Integer.parseInt(br.readLine());
		dist = new int[V + 1];
		visited = new boolean[V + 1];

		for(int i = 0 ; i <= V ; i++){
			edgeList.add(new ArrayList<>());
			dist[i] = Integer.MAX_VALUE;
		}

		for(int i = 0 ; i < E ; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList.get(i).add(new Edge(from, to, weight));
		}
	}

	private static void dijksta(){
		Queue<Vertex> vertexQueue = new PriorityQueue<>();
		dist[startVertex] = 0 ;
		vertexQueue.offer(new Vertex(startVertex, dist[startVertex]));


//		for(int i = 1 ; i <= V; i++){
		while(!vertexQueue.isEmpty()){
			Vertex currVertex = vertexQueue.poll();
			int curr = currVertex.vertex;
			int min = currVertex.dist;
			vertexQueue.clear();

//			System.out.println(curr);

			visited[curr] = true;
			for(Edge edge : edgeList.get(curr)){
				int to = edge.to;
				int weight = edge.weight;
				if(visited[to] || weight + min >= dist[to])  continue;
				dist[to] = weight + min;
			}
			for(int i = 1; i <= V; i++){
				if(!visited[i])  vertexQueue.offer(new Vertex(i, dist[i]));
			}
		}
	}

	private static String solve(){
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i <= V ; i++){
			sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		init();
		dijksta();
		System.out.println(solve());
	}
}
