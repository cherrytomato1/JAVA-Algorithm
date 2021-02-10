package boj.boj0028_210210_15649_s5;

import java.util.*;
import java.io.*;

/**
 * 1부터 N까지 중복없는 순열 출
 * @author mk
 *
 */
public class boj_15649_N과M1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N ;
	static int M ;
	
	static int ans[];
	static void f(int idx, int flag) {
		if(idx == M ) {
			for(int num : ans) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return ;
		}
		
		for(int i = 1 ; i <= N; i ++) {
			
			if((flag & 1 << i) != 0) continue;
			
			// flag & 1 << i == 1 은 왜안
			
			ans[idx] = i;
			f(idx + 1, flag | 1 << i);
		}
	}
	static boolean isSelected[];
	static void f(int idx) {
		if(idx == M ) {
			for(int num : ans) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return ;
		}
		
		for(int i = 1 ; i <= N ; i++) {
			if(isSelected[i]) continue;
			
			ans[idx] = i;
			isSelected[i] = true;
			f(idx + 1);
			isSelected[i] = false;
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = new int[M];
		isSelected = new boolean[N + 1];
		Arrays.fill(isSelected, false);
//		f(0,0)
		f(0);
		System.out.println(sb);
	}
}
