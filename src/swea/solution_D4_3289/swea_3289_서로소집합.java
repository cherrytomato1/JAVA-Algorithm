package swea.solution_D4_3289;

import java.util.*;
import java.io.*;

public class swea_3289_서로소집합 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	private static int N;
	private static int M;
	private static int[] parents;

	private static void make(){
		for(int i = 0 ; i <= N; i++){
			parents[i] = i;
		}
	}

	private static int find(int x){
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}

	private static boolean parentEquals(int x, int y){
		if(x > y)  {
			int temp = x;
			x = y;
			y = temp;
		}
		int parX = find(x);
		int parY = find(y);
		return parX == parY;
	}

	private static boolean union(int x, int y){
		int parX = find(x);
		int parY = find(y);
		if(parX == parY)    return false;
		parents[parY] = parX;
		return true;
	}

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		make();
	}

	private static void solve() throws IOException{
		for(int i = 0 ; i < M ; i++){
			st = new StringTokenizer(br.readLine());
			char order = st.nextToken().charAt(0);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(order == '0')    union(x, y);
			else                sb.append(parentEquals(x, y) ? 1 : 0);
		}
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++){
			sb.append("#").append(tc).append(" ");
			init();
			solve();
			sb.append("\n");
		}
		System.out.println(sb);
	}


}
