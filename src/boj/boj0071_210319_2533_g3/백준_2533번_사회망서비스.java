package boj.boj0071_210319_2533_g3;

import java.util.*;
import java.io.*;

public class 백준_2533번_사회망서비스 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static List<List<Integer>> list;
	private static int N;
	private static boolean[] visited;

	private static void init() throws IOException{
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>(N);
		visited = new boolean[N + 1];

		for(int i = 0 ; i <= N ; i++){
			list.add(new ArrayList<>());
		}
		for(int i = 0 ; i < N - 1 ; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).add(to);
			list.get(to).add(from);
		}
	}

	private static int solve(){
		Queue<Integer> q = new ArrayDeque<>();
		int oddCnt = 1;
		int evenCnt = 0;
		boolean isOdd = false;
		visited[1] = true;
		q.offer(1);
		while(!q.isEmpty()){
			int cnt = 0;
			for(int i = 0, size = q.size(); i < size ; i++){
				int curr = q.poll();
				for(int go : list.get(curr)){
					if(visited[go])     continue;
					visited[go] = true;
					q.offer(go);
//					System.out.println(go + (isOdd ? "odd" : "even"));
					cnt++;
				}
			}
			if(isOdd)   oddCnt += cnt;
			else        evenCnt += cnt;
			isOdd = !isOdd;
		}
		return Math.min(oddCnt, evenCnt);
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}

/*
16
1 2
1 3
1 4
1 5
1 6
2 7
3 8
4 9
5 10
8 11
9 12
9 13
9 14
9 15
9 16


15
1 2
1 3
3 4
1 5
5 6
6 7
7 8
8 9
9 10
10 11
11 12
12 13
13 14
14 15

15
1 2
1 3
1 4
1 5
1 6
2 7
2 8
5 9
5 10
7 11
7 12
8 13
9 14
4 15
 */