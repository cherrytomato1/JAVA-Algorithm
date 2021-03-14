package boj.boj0066_210312_1976_g4;

import java.util.*;
import java.io.*;

public class 백준_1976_여행가자 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int M;
	static boolean[][] arr;
	static boolean[] visited;
	static int[] plan;

	static Queue<Integer> q = new ArrayDeque<>();

	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		arr = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];
		plan = new int[M];
		for(int i = 1 ; i <= N ; i++){
			st = new StringTokenizer(br.readLine());

			for(int j = 1 ; j <= N ; j++){
				arr[i][j] = st.nextToken().equals("1");
			}
		}


		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M; i++){
			plan[i] = Integer.parseInt(st.nextToken());
		}
//		System.out.println(Arrays.toString(plan));

//		for(int i = 0 ; i <= N ; i++){
//			for(int j = 0 ; j <= N ;j++){
//				System.out.print(arr[i][j] ? 1 : 0);
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
	}

	public static boolean bfs(){
		int idx = 0;
		visited[plan[idx]] = true;
		q.offer(plan[idx]);
		if(M - 1== idx)    return true;
		int next = plan[++idx];

		while(!q.isEmpty()){
			int curr = q.poll();

			if(arr[curr][next]) {
//				System.out.println(curr + "good!" + next);
				if(++idx == M)    {
//					System.out.println(idx);
					return true;
				}

				visited = new boolean[N + 1];
				visited[next] = true;

				q.clear();
				q.offer(next);

				next = plan[idx];
				continue;
			}
			for(int i = 1 ; i <= N ; i++){
				if(visited[i] || !(arr[curr][i] && arr[i][curr]))  continue;
//				System.out.println(idx + " " + curr + " -> " + i + "  for : "+ next);
				visited[i] = true;
				q.offer(i);
			}
		}
//		System.out.println(idx);
		return false;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(bfs() ? "YES" : "NO");
	}
}

/*

4
4
0 0 0 1
0 0 1 0
0 1 0 1
1 0 1 0
1 2 1 3
 */