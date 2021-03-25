package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
//		unionTest();
//		kruskalTest();
//		dijkstraTest();
//		dpTest();
		lisTest();
	}

	private static void lisTest() throws IOException{
		LIS lis = new LIS();
//		System.out.println(lis.getLIS());
		System.out.println(lis.getLIS2BinarySearch());
	}

	private static void dpTest() {
		DynamicProgramming dp = new DynamicProgramming(100);
		System.out.println(dp.DPgo(60));
	}

	private static void dijkstraTest() throws IOException{
		Dijkstra dijk = new Dijkstra();
		System.out.println(dijk.dijkstraTest());
	}


	public static void graphTest() throws IOException{
		GraphSearchList.in();
		GraphSearchList.bfs(0);
		GraphSearchList.dfs(0, 0);
	}
	public static void unionTest(){
		UnionFind uf = new UnionFind(10, new int[10]);
		uf.union(0, 1);
		uf.union(3, 5);
		uf.union(0, 2);
		uf.union(0, 5);
		uf.union(4, 6);
		uf.union(3, 6);
		System.out.println(Arrays.toString(uf.getParents()));
		uf.find(5);
		System.out.println(Arrays.toString(uf.getParents()));
	}
	public static void kruskalTest() throws IOException{
		MST1_Kruskal krsk = new MST1_Kruskal();
		System.out.println(krsk.kruskal());
	}
}
