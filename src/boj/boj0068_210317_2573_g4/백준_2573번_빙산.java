package boj.boj0068_210317_2573_g4;

import java.util.*;
import java.io.*;

public class 백준_2573번_빙산 {
	private static class Node{
		int row;
		int col;
		public Node(int row, int col){
			this.row = row;
			this.col = col;
		}
	}

	private static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int M;
	private static int[][] map;
	private static boolean[][] visited;

	private static Deque<Node> q = new ArrayDeque<>();

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for(int i = 0 ; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M; j ++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void bfs(Node start){
		q.clear();
		q.offer(start);

		while (!q.isEmpty()){
			Node curr = q.poll();
			int r = curr.row;
			int c = curr.col;

			for(int i = 0 ; i < 4 ; i++){
				int nr = r + DIR[0][i];
				int nc = c + DIR[1][i];

				if(nr >= N || nr < 0 || nc >= M || nc < 0 || map[nr][nc] == 0 || visited[nr][nc] ) continue;
				visited[nr][nc] = true;
				q.offer(new Node(nr, nc));
			}
		}
	}

	private static void melt(){
		for(int r = 0 ; r < N; r++){
			for(int c = 0 ; c < M ; c++){
				if(map[r][c] == 0) continue;
				int cnt = 0;
				for(int i = 0 ; i < 4; i++){
					int nr = r + DIR[0][i];
					int nc = c + DIR[1][i];

					if(nr >= N || nr < 0 || nc >= M || nc < 0 || map[nr][nc] != 0) continue;
					cnt++;
				}
				map[r][c] = (map[r][c] - cnt) <= 0 ? -1 : (map[r][c] - cnt);
			}
		}
		for(int r = 0; r < N; r ++){
			for(int c = 0 ; c < M ; c++){
				map[r][c] = map[r][c] == -1 ? 0 : map[r][c];
			}
		}
	}

	private static int find(){
		int cnt = 0;
		visited = new boolean[N][M];
		for(int r = 0; r < N ; r++){
			for(int c = 0; c < M; c++){
				if(map[r][c] == 0 || visited[r][c]) continue;
				if(++cnt == 2)                      return 2;
				bfs(new Node(r, c));
			}
		}
		return cnt;
	}


	private static int solve(){
		int ret = 0;
		while(true){
			int val = find();
			if(val != 1)    return val == 0 ? 0 : ret;
			melt();
			ret++;
		}
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

}
