package boj.boj0159_210623_14226_g5;

import java.util.*;
import java.io.*;

public class 백준_14226번_이모티콘 {
	private static int S;
	private static int maxS;
	private static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
	private static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		maxS = S * 2 + 1;
		visited = new boolean[maxS][maxS];
	}

	private static int solve() {
		int ret = 0;
		Queue<int[]> q = new ArrayDeque<>();
		visited[1][0] = true;
		q.offer(new int[]{1, 0});
		while (!q.isEmpty()) {
			for (int size = q.size(); size > 0 ; size--) {
				int[] curr = q.poll();
				if(curr[0] == S)   return ret;
				int next = curr[0] + curr[1];
				if (curr[1] != 0 && next < maxS && !visited[next][curr[1]]) {
					q.offer(new int[]{next, curr[1]});
					visited[next][curr[1]] = true;
				}
				if (!visited[curr[0]][curr[0]]) {
					q.offer(new int[]{curr[0], curr[0]});
					visited[curr[0]][curr[0]] = true;
				}
				next = curr[0] - 1;
				if (next != 0 && !visited[next][curr[1]]) {
					q.offer(new int[]{next, curr[1]});
					visited[next][curr[1]] = true;
				}
			}
			ret++;
		}
		return -1;
	}
}
