package boj.boj0180_2101125_1260_s2_dfs와bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_2606번_바이러스 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static boolean[][] array;
	private static boolean[] visited;

	private static int N;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(dfs(1) - 1);
	}

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		array = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];

		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			array[from][to] = true;
			array[to][from] = true;

		}
	}

	private static int dfs(int index) {
		if (visited[index]) {
			return 0;
		}
		visited[index] = true;

		int ret = 0;

		for (int i = 0; i <= N ; i++) {
			if (!array[index][i]) {
				continue;
			}

			ret += dfs(i);
		}
		return ret + 1;
	}

}
