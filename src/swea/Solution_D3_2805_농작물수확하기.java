package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_D3_2805_농작물수확하기 {
	
	static int N;
	static int T;
	static int[][] map;
	
	
	static boolean flag;
	
	/** static 변수 쓰기 */
	static int ans;
	private static void f(int row, int idxStart, int idxEnd) {
		if(row == N) {
			flag = false;
			return ;
		}
		
		if(row == N/2 ) flag = true;
		
		for(int i = idxStart ; i <= idxEnd ; i++) { 
			ans += map[row][i];
		}
	
		if(flag) f(row + 1, idxStart + 1, idxEnd - 1);
		else	 f(row + 1, idxStart - 1, idxEnd + 1);
	}
	
	/** 지역변수 리턴하기 */
	private static int f2(int row, int idxStart, int idxEnd) {
		int sum = 0;
		if(row == N) {
			flag = false;
			return 0;
		}
		if(row == N/2 ) flag = true;
		
		for(int i = idxStart ; i <= idxEnd ; i++) { 
			sum += map[row][i];
		}
		
		return sum + (flag ? f2(row + 1, idxStart + 1, idxEnd - 1) : f2(row + 1, idxStart - 1, idxEnd + 1));
	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			String str;
			
			for(int i = 0; i < N ; i++) {
				str = br.readLine();
				for(int j = 0 ; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			
			ans = 0;
			f(0, N/2, N/2);
			sb.append("#" + tc + " ").append(ans).append("\n");
//			sb.append( "#" + tc + " ").append(f2(0, N/2, N/2)).append("\n");
		}
		System.out.println(sb.toString());
	}
}
