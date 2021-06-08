package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	private static class MyClass {
		int a;
		public MyClass() {
			System.out.println("ㅅㅐㅇ성자 실행");
			this.myMethod();
		}

		public void myMethod() {
			System.out.println("메서드 수행");
		}
	}


	public static void main(String[] args) throws IOException {
//		unionTest();
//		kruskalTest();
//		dijkstraTest();
//		dpTest();
		lisTest();
		BinarySearchTest();
	}


	static void BinarySearchTest(){
		final int EOK = 100_000_000;
		final int EOK10 = 1_000_000_000;
		int[] arr = new int[EOK10/2];

		for(int i = 0; i < EOK10/2; i++){
			arr[i] = i;
		}

		int val = EOK * 4;
		long start = System.currentTimeMillis();
		for(int i = 0; i < EOK10/2; i++){
			if (arr[i] == val) {
				System.out.println(i);
				break;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		start = System.currentTimeMillis();
//		System.out.println(BinarySearch.binarySearch(arr, val));
		end = System.currentTimeMillis();
		System.out.println(end - start);
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
	public static void primTest() throws IOException{
		MST2_PRIM prim = new MST2_PRIM();
		System.out.println(prim.primPriorityQueue());
	}
}
