package boj.boj0147_210521_1715_g4;

import java.io.*;
import java.util.*;

public class 백준_1715번_카드정렬하기 {
	private static Queue<Integer> pq;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();

		for (int i = 0; i < N ; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
	}

	private static long solve() {
		long ret = 0;
		while (pq.size() > 1) {
			int val = pq.poll() + pq.poll();
			pq.offer(val);
		}
		return ret;
	}
}