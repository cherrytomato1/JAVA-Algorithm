package swea.solution_D4_1249;

import java.io.*;
import java.util.*;

public class SWEA_1249_보급로 {
	private static class Pos{
		int row;
		int col;
		int weight;
		public Pos(int row, int col, int weight){
			this.row = row;
			this.col = col;
			this.weight = weight;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

	private static int N;
	private static int[][] map;
	private static int[][] visited;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new int[N][N];

		for (int i = 0; i < N ; i++) {
			char[] in = br.readLine().toCharArray();
			for (int j = 0; j < N ; j++) {
				map[i][j] = in[j] - '0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	private static int solve() throws IOException{
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(0, 0, 0));
		while (!q.isEmpty()){
			Pos curr = q.poll();
			for (int i = 0; i < 4 ; i++) {
				int nr = curr.row + DIR[0][i];
				int nc = curr.col + DIR[1][i];

				if(nr >= N || nr < 0 || nc >= N || nc < 0
						   || visited[nr][nc] <= curr.weight + map[nr][nc])   continue;
				visited[nr][nc] = curr.weight + map[nr][nc];

				q.offer(new Pos(nr, nc, visited[nr][nc]));
			}
		}
		return visited[N - 1][N - 1];
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1 ; tc <= T ;tc++){
			init();
			sb.append("#").append(tc).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);
	}

}
