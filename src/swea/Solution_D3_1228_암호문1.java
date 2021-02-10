package swea;

import java.util.*;
import java.io.*;

public class Solution_D3_1228_암호문1 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static LinkedList<Integer> list = new LinkedList<>();
	static Deque<Integer> dq ;
	static Deque<Integer> dq2 ;
	static StringTokenizer st ;
	static int N;
	
	public static void main(String[] args) throws IOException{
//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for(int tc = 1 ; tc <= T ; tc ++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			dq = new ArrayDeque<>();
			dq2 = new ArrayDeque<>();
			
			sb.append("#").append(tc).append(" ");
			for(int i = 0 ; i < N ; i++) {
				dq.offer(Integer.parseInt(st.nextToken()));
			}
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0 ; i < M ; i++) {
				st.nextToken();
				int idx = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				for(int j = 0 ; j < idx ; j ++) {
					dq2.offer(dq.poll());
				}
				for(int j = 0 ; j < size ; j++) {
					dq2.offer(Integer.parseInt(st.nextToken()));
				}
				while(!dq.isEmpty()) {
					dq2.offer(dq.poll());
				}
				while(!dq2.isEmpty()) {
					dq.offer(dq2.poll());
				}
			}
			
			for(int i = 0 ; i < 10 && !dq.isEmpty() ; i++) {
				sb.append(dq.poll()).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
