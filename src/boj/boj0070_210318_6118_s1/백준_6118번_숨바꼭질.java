package boj.boj0070_210318_6118_s1;

import java.util.*;
import java.io.*;

public class 백준_6118번_숨바꼭질 {
	private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final List<PriorityQueue<Integer>> list = new ArrayList<>();
	private static int[][] arr;
	private static boolean[] visited;

	private static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][2];
		visited = new boolean[N + 1];

		for(int i = 0 ; i < N + 1 ; i++){
			list.add(new PriorityQueue<>());
		}
		for(int i = 0 ; i < M ; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).offer(to);
			list.get(to).offer(from);
		}
	}

	private static int bfs(int start){
		Queue<Integer> q = new ArrayDeque<>();
		int dist = 0;
		visited[start] = true;
		q.offer(start);
		while(!q.isEmpty()){
			dist++;
			for(int i = 0, size = q.size(); i < size ; i++){
				int curr = q.poll();
				while(list.get(curr).iterator().hasNext()){
					int go = list.get(curr).poll();
					if(visited[go])     continue;
					arr[dist][0] = arr[dist][0] == 0 ? go : Math.min(arr[dist][0], go);
					arr[dist][1] ++;
					visited[go] = true;
					q.offer(go);
				}
			}
		}
		return --dist;
	}

	public static void main(String[] args) throws IOException{
		init();
		int res = bfs(1);
		System.out.println(arr[res][0] + " " + res + " " + arr[res][1]);
	}
}
