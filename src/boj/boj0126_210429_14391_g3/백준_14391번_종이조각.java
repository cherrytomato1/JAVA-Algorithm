//package boj.boj0126_210429_14391_g3;
//
//import java.util.*;
//import java.io.*;
//
//public class 백준_14391번_종이조각 {
//	private static int[][] arr;
//	private static int R;
//	private static int C;
//	private static int maxLength;
//	private static Deque<Integer> stk = new ArrayDeque<>();
//
//	public static void main(String[] args) throws IOException{
//		init();
//		System.out.println(solve());
//	}
//
//	private static void init() throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		R = Integer.parseInt(st.nextToken());
//		C = Integer.parseInt(st.nextToken());
//		maxLength = Math.max(R, C);
//
//		arr = new int[R][C];
//
//		for (int i = 0; i < R; i++) {
//			char[] in = br.readLine().toCharArray();
//			for (int j = 0; j < C ; j++) {
//				arr[i][j] = in[j] - '0';
//			}
//		}
//	}
//
//	private static int solve() {
//		int rowMax = 0;
//		int colMax = 0;
//		for (int i = 0; i < maxLength ; i++) {
//			for (int j = 0; j < maxLength ; j++) {
//				if(i < R && j < C && arr[i][j] != 0)  {
//					rowMax += arr[i][j] * getVal(C, j);
//				}
//				if(i < C && j < R)  {
//					colMax += arr[j][i] * getVal(R, j);
//				}
//			}
//		}
//
//		return Math.max(rowMax, colMax);
//	}
//
//	static int res = 0;
//	private static void recur(boolean[][] visited, int val, int visitedCnt, int row, int col) {
//		if(visitedCnt == R * C){
//			res = Math.max(val, res);
//			return;
//		}
//		boolean[][] rowMap = copyArray(visited);
//		boolean[][] colMap = copyArray(visited);
//		int rowVal = 0;
//		int colVal = 0;
//		int rowVisited = visitedCnt;
//		int colVisited = visitedCnt;
//		for (int i = row; i < R ; i++) {
//			if(i == 0)  continue;
//			for (int j = 0; j < C ; j++) {
//				if(visited[i][j])   continue;
//				stk.push(arr[i][j]);
//				visited[i][j] = true;
//				rowVisited--;
//			}
//			for (int stkCnt = 0; !stk.isEmpty(); stkCnt++) {
//				rowVal += getVal(stkCnt, stk.pop());
//			}
//		}
//	}
//
//	private static boolean[][] copyArray(boolean[][] source) {
//		boolean[][] ret = new boolean[R][C];
//		for (int i = 0; i < R ; i++) {
//			System.arraycopy(source[i], 0, ret[i], 0, C);
//		}
//		return ret;
//	}
//
//	private static int getVal(int size, int val) {
//		return val * (int)Math.pow(10, length - index - 1);
//	}
//}
///*
//1 0 0 0
//0 0 0 3
//0 0 0 0
//2 0 0 0
//
//
// */
