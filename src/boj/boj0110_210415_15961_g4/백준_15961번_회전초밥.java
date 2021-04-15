package boj.boj0110_210415_15961_g4;

import java.util.*;
import java.io.*;

public class 백준_15961번_회전초밥 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int D;
	private static int K;
	private static int C;

	private static int[] in;
	private static int[] types;
	private static Queue<Integer> q;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		q = new ArrayDeque<>();
		in = new int[N];
		types = new int[D + 1];

		for (int i = 0; i < N ; i++) {
			in[i] = Integer.parseInt(br.readLine());
		}
	}

	private static int solve() {
		int cnt = 1;
		int ret = Integer.MIN_VALUE;
		types[C]++;

		for (int i = N - K; i < N; i++) {
			if(types[in[i]]++ == 0) cnt++;
			q.offer(in[i]);
		}

		for (int i = 0 ; i < N; i++){
			if(--types[q.poll()] == 0)  cnt--;
			if(types[in[i]]++ == 0)     cnt++;
			q.offer(in[i]);
			ret = Math.max(ret, cnt);
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
