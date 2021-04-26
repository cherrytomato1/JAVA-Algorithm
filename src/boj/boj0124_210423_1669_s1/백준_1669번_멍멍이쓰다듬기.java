package boj.boj0124_210423_1669_s1;

import java.util.*;
import java.io.*;

public class 백준_1669번_멍멍이쓰다듬기 {
	private static long diff;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		diff = -(Long.parseLong(st.nextToken()) - Long.parseLong(st.nextToken()));
	}

	private static int solve() {
		if(diff == 0)    return 0;
		int cnt = 0;
		long val = 0;
		for (; val < diff ; cnt++) val = (long)cnt * cnt;

	}
}
