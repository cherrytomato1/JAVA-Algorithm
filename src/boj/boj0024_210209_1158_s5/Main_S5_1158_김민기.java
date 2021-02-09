package boj.boj0024_210209_1158_s5;

import java.util.*;
import java.io.*;

public class Main_S5_1158_김민기 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int K;
	static Queue<Integer> q = new ArrayDeque<>();
	
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		sb.append("<");
		for(int i = 1 ; i <= N; i++) {
			q.offer(i);
		}
		
		for(int i = 1 ; !q.isEmpty(); i++) {
			if(i % K == 0 )		sb.append(q.poll()).append(", ");
			else 		   		q.offer(q.poll());
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");
		System.out.println(sb);
	}
}
