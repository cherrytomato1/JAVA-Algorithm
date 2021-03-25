package swea.solution_D4_1251;

import java.util.*;
import java.io.*;

public class swea_1251_하나로 {
	private static class Node{
		int row;
		int col;
		public Node(int row){
			this.row = row;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static double E;
	private static Node[] nodes;
	private static boolean[] visited;
	private static double[] minEdge;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		nodes = new Node[N];
		visited = new boolean[N];
		minEdge = new double[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++){
			nodes[i] = new Node(Integer.parseInt(st.nextToken()));
			minEdge[i] = Double.MAX_VALUE;
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++){
			nodes[i].col = Integer.parseInt(st.nextToken());
		}
		E = Double.parseDouble(br.readLine());
	}

	private static double prim(){
		double ret = 0;
		minEdge[0] = 0;
		for(int v = 0 ; v < N; v++){
			double min = Double.MAX_VALUE;
			int minVertex = 0;
			for(int i = 0 ; i < N ; i++){
				if(visited[i] || min <= minEdge[i])     continue;
				min = minEdge[i];
				minVertex = i;
			}

			ret += E * min * min;
			visited[minVertex] = true;

			for(int i = 0 ; i < N; i++){
				if(visited[i])  continue;
				minEdge[i] = Math.min(minEdge[i], getDistance(nodes[minVertex], nodes[i]));
			}
		}
		return ret;
	}

	private static double getDistance(Node n1, Node n2){
		double row = Math.pow(n1.row - n2.row, 2);
		double col = Math.pow(n1.col - n2.col, 2);
		return Math.sqrt(row + col);
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T ; tc++){
			sb.append("#").append(tc).append(" ");
			init();
			sb.append(Math.round(prim())).append("\n");
		}
		System.out.println(sb);
	}
}
