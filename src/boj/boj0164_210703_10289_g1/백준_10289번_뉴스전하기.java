package boj.boj0164_210703_10289_g1;

import java.io.*;
import java.util.*;

public class 백준_10289번_뉴스전하기 {
	private static List<List<Integer>> childList;
	private static int[] d;
	private static int N;
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(dfs(0));
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		d = new int[N];
		childList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			childList.add(new ArrayList<>());
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (int i = 1; i < N ; i++) {
			childList.get(Integer.parseInt(st.nextToken())).add(i);
		}
	}

	private static int dfs(int node) {
		if(childList.get(node).size() == 0) return 0;
		if(d[node] != 0)        return d[node];

		Queue<Integer> pq = new PriorityQueue<>();
		for (int child : childList.get(node)) {
			pq.offer(dfs(child));
		}
		int maxValue = 0;

		for (int i = pq.size(); !pq.isEmpty() ; i--) {
			maxValue = Math.max(pq.poll() + i, maxValue);
		}
		return d[node] = maxValue;
	}
}
