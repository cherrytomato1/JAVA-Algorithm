package boj.boj0115_210416_17609_s1;

import java.io.*;

public class 백준_17609번_회문 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static char[] str;

	private static int find(int low, int high, boolean flag){
		for (; low < high; low++, high--) {
			if(str[low] == str[high])       continue;
			if(flag || (find(low + 1, high, true) != 0
					   && find(low, high - 1, true) != 0) )    return 2;
			return 1;
		}
		return 0;
	}
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T ; tc++) {
			str = br.readLine().toCharArray();
			sb.append(find(0, str.length - 1, false)).append("\n");
		}
		System.out.println(sb);
	}
}
