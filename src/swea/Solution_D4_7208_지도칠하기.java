/**
 *
 * 문제 정의
 * 1. 각 인접행렬로 주어진 나라가 인접한 경우 다른 색으로 칠한다. 이 때 가장 색을 적게 바꾸는 경우의 수 출력
 *
 * 입력
 * 1. 테스트 케이스수
 * 2. 나라의 수
 * 3. 각 나라가 갖고있는 색
 * 4. 나라의 수 만큼의 열에 각 나라별 인접행렬
 *
 * 풀이
 * 1. 각 나라가 가질 수 있는 색의 순열을 모두 출력한다.
 * 2. 출력화는 과정 중에 원래의 나라색과 다르다면 변경 수 1을 더한 후 재귀한다.
 * 3. 순열을 생성 후 인접행렬에 의해 인접한 나라의 색을 검사한다. 인접 나라가 색이 같다면 그 조합은 버린다.
 * 4. 인접국끼리 색이 모두 다른 경우 색을 변경한 회수 중 가장 낮은 값을 결과로 출력한다.
 *
 */

package swea;

import java.util.*;
import java.io.*;

public class Solution_D4_7208_지도칠하기 {
	static int N;
	static boolean[][] contry;
	static int[] colors;
	static int[] res;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int ans = Integer.MAX_VALUE;
	static void f(int idx, int cnt) {
		if(idx == N ) {
			for(int i = 0 ; i < N ; i ++) {
				for(int j = 0 ; j < N ; j++) {
					if(contry[i][j] && res[i] == res[j])
						return;
				}
			}
			ans = ans < cnt ? ans : cnt;
			return ;
		}
		
		for(int i = 1 ; i <= 4 ; i++) {
			res[idx] = i ;
			if(res[idx] != colors[idx])		f(idx + 1, cnt + 1);
			else f(idx + 1, cnt);
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			sb.append("#").append(tc).append(" ");
			
			contry = new boolean[N][N];
			colors = new int[N];
			res = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				colors[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					contry[i][j] = Integer.parseInt(st.nextToken()) == 1;
				}
			}
			
			ans = Integer.MAX_VALUE;
			f(0, 0);
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
