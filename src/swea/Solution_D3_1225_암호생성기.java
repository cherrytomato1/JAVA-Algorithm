package swea;

import java.io.*;
import java.util.*;

public class Solution_D3_1225_암호생성기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int LEN = 8;
	
	static int[] password = new int[LEN];
	static Queue<Integer> q = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		
		for(int tc = 1 ; tc <= 10 ; tc ++) {
			tc = Integer.parseInt(br.readLine());
			sb.append("#" + tc + " ");
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < LEN ; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			int cnt = 0;
			int val = Integer.MAX_VALUE;
			
			while(val != 0) {
				cnt = cnt % 5 + 1;
				q.offer(val = Math.max(q.poll() - cnt, 0));
			}
			
			while(!q.isEmpty()) {
				sb.append(q.poll()).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

/*

1
9550 9556 9550 9553 9558 9551 9551 9551 
2
2419 2418 2423 2415 2422 2419 2420 2415 
3
7834 7840 7840 7835 7841 7835 7835 7838 
4
4088 4087 4090 4089 4093 4085 4090 4084 
5
2945 2946 2950 2948 2942 2943 2948 2947 
6
670 667 669 671 670 670 668 671 
7
8869 8869 8873 8875 8870 8872 8871 8873 
8
1709 1707 1712 1712 1714 1710 1706 1712 
9
10239 10248 10242 10240 10242 10242 10245 10235 
10
6580 6579 6574 6580 6583 6580 6577 6581 
*/