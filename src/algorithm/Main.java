package algorithm;

import java.io.IOException;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		unionTest();
	}
	public static void graphTest() throws IOException{
		GraphSearchList.in();
		GraphSearchList.bfs(0);
		GraphSearchList.dfs(0, 0);
	}
	public static void unionTest(){
		UnionFind uf = new UnionFind(10);
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
}
