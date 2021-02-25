package swea.solution_1767;

import java.util.*;
import java.io.*;

class Node{
	int row;
	int col;

	public Node(int row, int col){
		this.row = row;
		this.col = col;
	}
}

public class solution_1767_프로세서연결하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	static int N;
	static boolean[][] map;
	static boolean[][] visited;
	static List<Node> cores;

	static int lenMin ;
	static int coreMax ;

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());

		map = new boolean[N][N];
		visited = new boolean[N][N];
		cores = new ArrayList<>();
		lenMin = Integer.MAX_VALUE;
		coreMax = Integer.MIN_VALUE;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				if(st.nextToken().equals("1")){
					map[r][c] = true;
					if(r != 0 && r != N -1 && c != 0 && c != N - 1) cores.add(new Node(r, c));
				}
			}
		}
	}

	static void dfs(int coreIdx, int len, int coreCnt){
		if(coreCnt + cores.size() - coreIdx < coreMax) return;

		if(coreIdx == cores.size()){
			if(coreMax < coreCnt)   {
				lenMin = len;
				coreMax = coreCnt;
			}else if(coreCnt == coreMax){
				lenMin = Math.min(lenMin, len);
			}
			return;
		}
		for(int i = 0 ; i < 4; i++){
			Node curr = cores.get(coreIdx);
			if(!check(curr, i)) {
				dfs(coreIdx + 1, len, coreCnt);
				continue;
			}
			int nlen = mark(curr, i, true);
			dfs(coreIdx + 1, len + nlen, coreCnt + 1);
			mark(curr, i, false);
		}
	}

	static int mark(Node pos, int dir, boolean type){
		int row = pos.row;
		int col = pos.col;
		int cnt = 0;

		for(int i = 1 ; ; i++){
			int nr = row + DIR[dir][0] * i;
			int nc = col + DIR[dir][1] * i;

			if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] == type)  return cnt;

			visited[nr][nc] = type;
			if(!map[nr][nc])    cnt ++ ;
		}
	}

	static boolean check(Node core, int dir){
		int row = core.row;
		int col = core.col;

		for(int i = 1 ; ; i++){
			int nr = row + DIR[dir][0] * i;
			int nc = col + DIR[dir][1] * i;

			if(nr < 0 || nr >= N || nc < 0 || nc >= N)  return true ;
			if(visited[nr][nc] || map[nr][nc])   return false;
		}
	}


	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1 ; tc <= T ; tc++){
			sb.append("#").append(tc).append(" ");
			init();
			dfs(0, 0, 0);
			sb.append(lenMin).append("\n");
		}
		System.out.println(sb);
	}
}
