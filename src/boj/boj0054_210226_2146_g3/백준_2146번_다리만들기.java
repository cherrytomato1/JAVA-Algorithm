package boj.boj0054_210226_2146_g3;

import java.util.*;
import java.io.*;

class Node{
	int row;
	int col;
	int idx;

	public Node(int row, int col, int idx){
		this.row = row;
		this.col = col;
		this.idx = idx;
	}
}


public class 백준_2146번_다리만들기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	static int N;
	static int[][] map;
	static boolean[][] visited;

	static Queue<Node> q = new ArrayDeque<>();
	static Queue<Node> q2 = new ArrayDeque<>();

	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];

		for(int r = 0 ; r < N ; r ++){
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < N; c++){
				if(st.nextToken().equals("1"))  {
					q.offer(new Node(r, c, 0));
					map[r][c] = 1;
				}
			}
		}
	}

	static void findIsland(){
		int islandIdx = 1;
		while(!q.isEmpty()){
			Node curr = q.poll();
			int row = curr.row;
			int col = curr.col;
			if(map[row][col] == 1){
				map[row][col] += islandIdx;
				setIsland(row, col, islandIdx++);
			}
		}
	}

	static void setIsland(int row, int col, int num){
		for(int i = 0 ; i < 4 ; i++){
			int nr = row + DIR[i][0];
			int nc = col + DIR[i][1];

			if(nr < 0 || nr >= N || nc < 0 || nc >= N)  continue;
			if(map[nr][nc] != 0 && map[nr][nc] != 1)    continue;

			if(map[nr][nc] == 0 ) {
				q2.offer(new Node(nr, nc, num + 1));
				map[nr][nc] = -1;
				continue;
			}

			map[nr][nc] += num;
			setIsland(nr, nc, num);
		}
	}

	static int findBridge(){
		int minLen = Integer.MAX_VALUE;

		loop1 :
		while(!q2.isEmpty()){
			int currIdx = q2.peek().idx;
			while(!q2.isEmpty() && q2.peek().idx == currIdx){
				q.offer(q2.poll());
			}
			int times = 0;

			visited = new boolean[N][N];
			while(!q.isEmpty()){
				if(times ++ > minLen) {
					q.clear();
					break;
				}

				for(int size = q.size(); size > 0; size--){
					Node curr = q.poll();

					for(int i = 0 ; i < 4 ; i++){
						int nr = curr.row + DIR[i][0];
						int nc = curr.col + DIR[i][1];

						if(nr < 0 || nr >= N || nc < 0 || nc >= N )     continue;
						if(map[nr][nc] == curr.idx || visited[nr][nc])  continue;

						if(map[nr][nc] != 0 && map[nr][nc] != -1 ){
							q.clear();
							minLen = Math.min(times, minLen);
							continue loop1;
						}
						visited[nr][nc] = true;
						q.offer(new Node(nr, nc, curr.idx));
					}
				}
			}
		}
		return minLen;
	}

	public static void main(String[] args) throws IOException{
		init();
		findIsland();
		System.out.println(findBridge());
	}

}
