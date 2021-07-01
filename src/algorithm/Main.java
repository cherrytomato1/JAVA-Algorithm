package algorithm;

import algorithm.sort.MergeSort;

import java.io.*;
import java.util.*;

public class Main {
	private static class Fruit {
		int value;

		@Override
		public Object clone() throws CloneNotSupportedException{
			Object clone = super.clone();
			return clone;
		}

	}

	private static class Apple extends Fruit implements Cloneable {
		@Override
		public Apple clone() throws CloneNotSupportedException{
			Apple clone = (Apple)super.clone();
			return clone;
		}
	}

	public class UserException extends RuntimeException{
		public UserException(String msg){
			super(msg);
		}
	}

	public class SearchException extends RuntimeException{
		public SearchException(String msg){
			super(msg);
		}
	}



	@FunctionalInterface
	static interface Test {
//		int test(int t);
//		int test(int t, int v);
//		boolean equals(Object obj);
		int hashCode();

		String toString();
		Object clone() throws CloneNotSupportedException;
//		int hashhash();
	}

	private class LoginExcpetion extends IOException{
		public LoginExcpetion(String msg) {
			super(msg);
		}
	}

	public void myService(String param){
		try{
			switch(param){
				case "login" :
					login();
					break;
				case "register" :
					register();
					break;
				case "search" :
					search();
					break;
			}
		} catch(UserException e){
			e.printStackTrace();
//			UserExceptionHandler(e);
		} catch(SearchException e){
			e.printStackTrace();
//			SearchExceptionHandler(e);
		}
	}

	private void login(){
		try{
			//..
		} catch(Exception e){
			throw new UserException("로그인 예외");
		}
	}

	private void register(){
		try{
			//..
		} catch(Exception e){
			throw new UserException("등록 예외");
		}
	}

	private void search(){
		try{
			//..
		} catch(Exception e){
			throw new SearchException("검색 예외");
		}
	}






	public static void main(String[] args) {
//		unionTest();
//		kruskalTest();
//		dijkstraTest();
//		dpTest();
//		lisTest();

//		BinarySearchTest();
		Object O = new Object();
		Fruit m = new Fruit();


		int[] arr = new int[10];
		for (int i = 0; i < 10 ; i++) {
			arr[i] = Math.abs(i - 10);
		}
		System.out.println(Arrays.toString(arr));
		MergeSort.mergeSort(arr);
		System.out.println(Arrays.toString(arr));

		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str = br.readLine();
		} catch(IOException e){
			throw new RuntimeException("예외 발생 !");
		}

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
