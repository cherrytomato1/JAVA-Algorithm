package algorithm;

import com.sun.org.apache.xpath.internal.operations.String;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MST1_Kruskal {
	private static class Edge implements Comparable{
		int from;
		int to;
		int weight;
		public Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Object o){
			return Integer.compare(this.weight, ((Edge)o).weight);
		}
	}

	//정점개수, 간선 개수
	private final int V, E;
	private final int p[];
	private final Edge[] edgeList;
	private final UnionFind uf;
	private final BufferedReader br;

	public MST1_Kruskal() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		this.V = Integer.parseInt(st.nextToken());
		this.E = Integer.parseInt(st.nextToken());
		edgeList = new Edge[E];
		p = new int[E];
		getIn();
		uf = new UnionFind(E, p);
	}

	private void getIn() throws IOException{
		for(int i = 0 ; i < E ; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		Arrays.sort(edgeList);
	}

	public int kruskal(){
		int cnt = 0;
		int ret = 0;
		for(Edge edge : edgeList){
			if(uf.union(edge.from, edge.to)){
				ret += edge.weight;
				if(++cnt == V - 1) break;
			}
		}
		return ret;
	}
}
