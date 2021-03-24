package boj.boj0078_210324_2075_g5;

import java.util.*;
import java.io.*;

public class 백준_2075번_N번째큰수 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static Queue<Integer> pq = new PriorityQueue<>();

	private static int solve() throws IOException{
		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++){
			pq.offer(Integer.parseInt(st.nextToken()));
		}

		for(int i = 1 ; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ;j++){
				int val = Integer.parseInt(st.nextToken());
				if(pq.peek() < val){
					pq.poll();
					pq.offer(val);
				}
			}
		}
		return pq.peek();
	}

	public static void main(String[] args) throws IOException{
		System.out.println(solve());
	}
}
