package swea;

import java.util.*;
import java.io.*;

public class Solution_D3_5215 {
	
	static int N;
	static int L;
	static int[][] src;		//0 만족도, 1 칼로리
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int ans ;
	public static void subset(int idx, int cal, int mask) {
		if(cal > L) {
			return ;
		}
		if(idx == N) {
			int sum = 0;
			for(int i = 0; i < src.length; i++) {
				if((mask & (2 << i)) != 0) {
					sum += src[i][0];
				}
			}
			ans = ans > sum ? ans : sum;
			return;
		}
		subset(idx + 1, cal + src[idx][1], mask | 2 << idx);
		subset(idx + 1, cal, mask);
	}
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			sb.append("#").append(tc).append(" ");
			
			ans = Integer.MIN_VALUE;
			src = new int[N][2];
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				src[i][0] = Integer.parseInt(st.nextToken());
				src[i][1] = Integer.parseInt(st.nextToken());
			}
			
			subset(0, 0, 0);
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
