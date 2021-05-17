package boj.boj0143_210517_16472_g2;

import java.io.*;

public class 백준_16472번_고냥이 {
	private static int N;
	private static char[] in;
	private static int[] chars;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		in = br.readLine().toCharArray();
		chars = new int[26];
	}

	private static int solve() {
		int max = 0;
		int used = 0;

		for (int i = 0, j = 0, length = in.length; j < length; j++) {
			if(chars[in[j] - 'a']++ == 0)   used++;
			while(used > N){
				if(--chars[in[i++] - 'a'] == 0)   used--;
			}
			max = Math.max(j - i + 1, max);
		}
		return max;
	}
}
