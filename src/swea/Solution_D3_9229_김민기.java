/**
 * 
 * 문제 정의
 * 1. N개의 과자봉지 중 정확히 2개의 과자봉지를 고를 때 가장 높은 무게합을 출력
 * 
 * 입력
 * 1. 첫째 줄 과자 봉지 수 N( 2 <= N <= 1000) , 한빈이의 무게 합( 2<= M <= 2,000,000)
 * 2. 다음 줄에 N개의 공백으로 구분된 과자봉지의 무게 ( 1 <= ai <= 1,000,000)
 * 
 * 풀이
 * 1. N개의 과자봉지중 2개를 뽑을 때, 무게 제한을 초과하지 않는 가장 큰 무게를 가진 조합의 무게를 출력
 */

package swea;

import java.util.*;
import java.io.*;

public class Solution_D3_9229_김민기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static int[] snack ;
	static int M;
	
	static int ans = -1;
	static void f(int idx, int idxStart, int sum) {
		if(sum > M) return ;
		
		if(idx == 2) {
			ans = ans > sum ? ans : sum ;
			return ;
		}
		
		for(int i = idxStart ; i < N ; i++) {
			f(idx + 1, i + 1, sum + snack[i]);
		}
	}
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			st = new StringTokenizer(br.readLine());
			sb.append("#").append(tc).append(" ");
			ans = -1;
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			snack = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}
			
			f(0,0,0);
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
}
