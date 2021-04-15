package boj.boj0112_210415_9252_g5;

import java.util.*;
import java.io.*;

public class 백준_9252번_LCS2 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static char[] in1;
	private static char[] in2;

	private static int len1;
	private static int len2;

	private static int[][] d;

	private static void init() throws IOException {
		in1 = br.readLine().toCharArray();
		in2 = br.readLine().toCharArray();
		len1 = in1.length;
		len2 = in2.length;
		d = new int[len1 + 1][len2 + 1];
	}

	private static String solve(){
		StringBuilder sb = new StringBuilder();
		sb.append(LCS()).append("\n").append(findStr());

		return sb.toString();
	}

	private static int LCS() {
		for (int i = 1; i <= len1 ; i++) {
			for (int j = 1; j <= len2 ; j++) {
				if(in1[i - 1] == in2[j - 1])    d[i][j] = d[i - 1][j - 1] + 1;
				else                            d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
			}
		}
		return d[len1][len2];
	}

	private static String findStr(){
		Deque<Character> stk = new ArrayDeque<>();
		int i = len1;
		int j = len2;
		while(i != 0 && j != 0){
			if(in1[i - 1] == in2[j - 1]){
				stk.push(in1[--i]);
				j--;
				continue;
			}
			if(d[i][j] == d[i - 1][j])      i--;
			else if(d[i][j] == d[i][j - 1]) j--;
		}
		StringBuilder sb = new StringBuilder();
		while (!stk.isEmpty())  {
			sb.append(stk.pop());
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
