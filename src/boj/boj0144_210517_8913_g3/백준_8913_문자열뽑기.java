package boj.boj0144_210517_8913_g3;

import java.io.*;

public class 백준_8913_문자열뽑기 {
	private static char[] in;
	private static int length;
	private static int flagMax;
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			init();
			sb.append(recur(0) ? 1 : 0).append("\n");
		}
		System.out.print(sb);
	}

	private static void init() throws IOException {
		in = br.readLine().toCharArray();
		length = in.length;
		flagMax = (1 << length) - 1;
	}

	private static boolean recur(int flag) {
		if (flag == flagMax)    return true;

		for (int i = 0; i < length; i++) {
			if((flag & 1 << i) != 0)        continue;
			int nextFlag = flag;

			int j = i + 1;
			for (; j < length ; j++) {
				if((flag & 1 << j) != 0)    continue;
				if(in[i] != in[j])         break;
				nextFlag |= 1 << j;
			}

			if(nextFlag == flag)                  continue;
			if(recur(nextFlag | 1 << i))    return true;
			i = j - 1;
		}
		return false;
	}
}

