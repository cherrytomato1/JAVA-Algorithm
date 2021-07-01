package boj.boj0162_210701_1013_g5;

import java.io.*;

public class 백준_1013_Contact {
	private static final String PATTERN = "(100+1+|01)+";
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			sb.append(br.readLine().matches(PATTERN) ? "YES" : "NO").append("\n");
		}
		System.out.println(sb);
	}
}
