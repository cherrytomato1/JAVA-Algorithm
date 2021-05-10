package boj.boj0138_210510_1593_g4;

import java.util.*;
import java.io.*;

public class 백준_1593번_문자해독 {
	private static int W;
	private static int S;

	private static char[] word;
	private static char[] str;

	private static int[] wordCnt;
	private static int[] strCnt;

	private static int wordMax;
	private static int strMax;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		word = br.readLine().toCharArray();
		str = br.readLine().toCharArray();

		wordCnt = new int[100];
		strCnt = new int[100];

		for (int i = 0; i < W ; i++) {
			wordMax += wordCnt[word[i] - 'A']++ == 0 ? 1 : 0;
		}
	}

	private static int solve() {
		int ret = 0;
		for (int i = 0; i < W ; i++) {
			strMax += ++strCnt[str[i] - 'A'] == wordCnt[str[i] - 'A'] ? 1 : 0;
		}
		if(strMax == wordMax)   ret++;

		for (int i = W; i < S ; i++) {
			if(strCnt[str[i - W] - 'A']-- == wordCnt[str[i - W] - 'A']) strMax--;
			if(++strCnt[str[i] - 'A'] == wordCnt[str[i] - 'A'])         strMax++;
			if(strMax == wordMax)   ret++;
		}
		return ret;
	}
}
