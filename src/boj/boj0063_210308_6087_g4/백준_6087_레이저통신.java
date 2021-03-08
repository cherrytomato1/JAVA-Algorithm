package boj.boj0063_210308_6087_g4;

import java.util.*;
import java.io.*;

public class 백준_6087_레이저통신 {

	private static class Node{
		int row;
		int col;

		public Node(int row, int col){
			this.row = row;
			this.col = col;
		}
	}

	static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int W;
	static int H;
	static Node end;

	static boolean[][] map;
	static int[][] visited;
	static Queue<Node> q = new ArrayDeque<>();

	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new boolean[H][W];
		visited = new int[H][W];

		for(int i = 0 ; i < H; i++){
			char[] in = br.readLine().trim().toCharArray();
			for(int j = 0 ; j < W ; j++){
				visited[i][j] = Integer.MAX_VALUE;
				map[i][j] = in[j] == '*';
				if(in[j] == 'C'){
					if(q.isEmpty()) q.offer(new Node(i, j));
					else            end = new Node(i, j);
				}
			}
		}
	}

	static boolean go(Node curr, int dir, int cnt){
		int nr = curr.row;
		int nc = curr.col;
		while(true){
			nr += DIR[0][dir];
			nc += DIR[1][dir];
			if(nr >= H || nr < 0 || nc >= W || nc < 0) return false;
			if(cnt > visited[nr][nc] || map[nr][nc])   return false;

			if(nr == end.row && nc == end.col)         return true;
			visited[nr][nc] = cnt;
			q.offer(new Node(nr, nc));
		}
	}

	static int bfs(){
		int ret = Integer.MAX_VALUE;
		int cnt = 0;
		while(!q.isEmpty()){
			for(int i = 0, size = q.size() ; i < size; i++){
				Node curr = q.poll();
				for(int j = 0 ; j < 4; j++){
					if(go(curr, j, cnt))    ret = Math.min(cnt,ret);
				}
			}
			cnt++;
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(bfs());
	}
}
