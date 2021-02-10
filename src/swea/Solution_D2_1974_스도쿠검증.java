package swea;

import java.util.*;
import java.io.*;

public class Solution_D2_1974_스도쿠검증 {
	
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static final int MAX = 9;
	static final int BLOCK = 3;
	static int[][] arr = new int[MAX][MAX];
	
	public static void main(String[] args) throws IOException{
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= T ; tc ++) {
			sb.append("#").append(tc).append(" ");
			for(int r = 0 ; r < MAX ; r ++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < MAX ; c++ ) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean flag = true;
			/** 행욜체크 */
			for(int r = 0 ; r < MAX ; r ++) {
				int rMask = 0 ;
				int cMask = 0 ;
				for(int c = 0 ; c < MAX ; c++) {
					rMask |= 1 << (arr[r][c] - 1);
					cMask |= 1 << (arr[c][r] - 1);
				}
				// 0b 1 1111 1111
				if(cMask != 0x1FF || rMask != 0x1FF) {
					flag = false; 
					break;
				}
			}
			
			/** 블록 체크 */
			for(int i = 0 ; i < BLOCK ; i ++) {
				for(int j = 0 ; j < BLOCK ; j ++) {
					int rBlock = i * BLOCK;
					int cBlock = j * BLOCK;
					int mask = 0;
					for(int r = rBlock ; r < rBlock + BLOCK ; r++) {
						for(int c = cBlock ; c < cBlock + BLOCK ; c++) {
							mask |= 1 << (arr[r][c] - 1);
						}
					}
					if(mask != 0x1FF) {
						flag = false; 
						break;
					}
				}
			}
			sb.append(flag ? 1 : 0).append("\n");
		}
		System.out.println(sb);
	}
}
