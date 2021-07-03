package boj.boj0163_210703_16120_g4;

import java.util.*;
import java.io.*;

public class 백준_16120번_PPAP {
	private static char[] in;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve() ? "PPAP" : "NP") ;
	}

	private static void init() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		in = br.readLine().toCharArray();11
	}

	private static boolean solve() {
		ArrayDeque<Character> stk = new ArrayDeque<>();
		boolean containsA = false;
		final char[] PPA = {'P', 'P', 'A'};

		for (int i = 0; i < in.length ; i++) {
			if (!containsA) {
				stk.push(in[i]);
				if(in[i] == 'A')    containsA = true;
				continue;
			}
			if (in[i] == 'A')   return false;

			for (int j = 2; j >= 0 ; j--) {
				if (stk.isEmpty() || stk.pop()!= PPA[j])      return false;
			}
			stk.push('P');
			containsA = false;
		}
		return stk.size() == 1 && stk.pop() == 'P';
	}
}
