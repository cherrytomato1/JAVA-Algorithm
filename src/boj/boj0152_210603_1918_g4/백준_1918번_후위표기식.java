package boj.boj0152_210603_1918_g4;

import java.util.*;
import java.io.*;

public class 백준_1918번_후위표기식 {
	private static char[] input;
	private static StringBuilder sb;
	private static ArrayDeque<Character> opStk;
	private static Map<Character, Integer> levelMap;

	public static void main(String[] args) throws IOException{
		init();
		solve();
		System.out.println(sb);
	}

	private static void init() throws IOException {
		InputStream in = System.in;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		input = br.readLine().toCharArray();
		sb = new StringBuilder();
		opStk = new ArrayDeque<>();

		levelMap = new HashMap<>();
		levelMap.put('(', 0);

		levelMap.put('+', 1);
		levelMap.put('-', 1);

		levelMap.put('*', 2);
		levelMap.put('/', 2);
	}

	private static void solve() {
		for (char c : input) {
			if (c >= 'A')       sb.append(c);
			else                pushOperator(c);
		}
		while (!opStk.isEmpty())    sb.append(opStk.pop());
	}

	private static void pushOperator(char c) {
		switch (c) {
			case '(' :
				opStk.push(c);
				break;
			case ')' :
				while (opStk.peek() != '(')     sb.append(opStk.pop());
				opStk.pop();
				break;
			default:
				while (!opStk.isEmpty() && levelMap.get(c) <= levelMap.get(opStk.peek())) {
					sb.append(opStk.pop());
				}
				opStk.push(c);
		}
	}
}
