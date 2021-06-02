package boj.boj0150_210603_9953_g4;


import java.io.*;
import java.util.Arrays;

public class 백준_9953번_문자열폭발 {
	private static char[] str;
	private static char[] bombStr;
	private static char[] res;
	private static int bombSize;
	private static int resSize;

	public static void main(String[] args) throws IOException{
		init();
		solve();
		printStr();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str = br.readLine().toCharArray();
		bombStr = br.readLine().toCharArray();
		bombSize = bombStr.length;

		res = new char[str.length];
	}

	private static void solve() {
		int strSize = str.length;
		System.arraycopy(str, 0, res, 0, bombSize - 1);
		resSize = bombSize - 1;
		for (int i = resSize; i < strSize ; i++, resSize++) {
			res[resSize] = str[i];
			if(!checkBomb(resSize)) continue;
			resSize -= bombSize;
		}
	}

	private static boolean checkBomb(int idx) {
		for (int i = bombSize - 1; i >= 0 ; i--, idx--) {
			if(res[idx] != bombStr[i])    return false;
		}
		return true;
	}

	private static void printStr() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < resSize ; i++) {
			sb.append(res[i]);
		}
		System.out.println(sb.length() == 0 ? "FRULA" : sb);
	}
}