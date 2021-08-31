package algorithm;

import algorithm.sort.MergeSort;

import java.io.*;
import java.util.*;


public class Main {


	public static void main(String[] args) {
//		unionTest();
//		kruskalTest();
//		dijkstraTest();
//		dpTest();
//		lisTest();

//		BinarySearchTest();
//		/trieTest();

		Integer[] arr = {1,2,3};
		Arrays.sort(arr, new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});





		Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);









		Arrays.sort(arr, (o1, o2) -> Integer.compare(o1, o2));









		Tetst t = (o1, o2) -> o1 + o2;
	}

//	@FunctionalInterface
	public interface Tetst {
		int add(int a, int b);

//		int sub();

		@Override
		boolean equals(Object o);

		int hashCode();

//		static int adder(int a, int b){
//			return a + b;
//		}
	}

	public <T extends Collections> void test(List<T> testList) {

	}

	static void enumTest() {
		System.out.println(Food.CAKE.ordinal());
		System.out.println(Food.values().getClass());

		Food food = Food.CAKE;
		switch (food) {
			case CAKE: //...
			case PIZZA: //...
			case COOKIE: //...
		}
		EnumSet enumSet = EnumSet.noneOf(Food.class);
	}

	static void trieTest() {
		Trie trie = new Trie();
		System.out.println(trie.contains("Test"));
		trie.insertNode("Test");
		System.out.println(trie.contains("Test"));
		System.out.println(trie.contains("Tes"));
		trie.insertNode("Te");
		System.out.println(trie.contains("Tes"));
		System.out.println(trie.contains("Te"));
	}


	static void mergeSortTest() {
		int[] arr = new int[10];
		for (int i = 0; i < 10 ; i++) {
			arr[i] = Math.abs(i - 10);
		}
		System.out.println(Arrays.toString(arr));
		MergeSort.mergeSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	static void BinarySearchTest(){

		//6억 배열 선언
		final int arraySize = 600_000_000;
		int[] arr = new int[arraySize];

		for(int i = 0; i < arr.length; i++){
			arr[i] = i;
		}

		//4.5억을 가르키고 있는 인덱스 값 찾기
		int targetVal = 450_000_000;

		long start = System.currentTimeMillis();
		//선형 탐색
		for(int i = 0; i < arr.length; i++){
			if (arr[i] == targetVal) {
				System.out.println("탐색 성공! 인덱스 : " + i);
				break;
			}
		}
		long end = System.currentTimeMillis();
		//선형 탐색 수행시간 출력
		System.out.println("수행시간 : " + (end - start));

		start = System.currentTimeMillis();
		//이진 탐색
		System.out.println("탐색 성공! 인덱스 : " + MyBinarySearch.binarySearch(arr, 0, arr.length - 1, targetVal));
		end = System.currentTimeMillis();

		//이진 탐색 수행시간 출력
		System.out.println("수행시간 : " + (end - start));


		targetVal = 5;
		for (int i = 0; i < arr.length ; i++) {
			arr[i] = i/3;
		}
		for (int i = 0; arr[i] <= 7 ; i++) {
			System.out.print("[" + arr[i] + "], ");
		}
		System.out.println();

		//각각의 결과 확인
		System.out.println(MyBinarySearch.binarySearch(arr, 0, arr.length - 1, targetVal));
		System.out.println(MyBinarySearch.lowerBound(arr, 0, arr.length - 1, targetVal));
		System.out.println(MyBinarySearch.upperBound(arr, 0, arr.length - 1, targetVal));
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
