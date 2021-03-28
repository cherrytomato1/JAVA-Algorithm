package boj.boj0089_210328_2608_s1;

import java.io.*;
import java.util.*;

public class 백준_2608번_로마숫자 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final String[] input = new String[2];
	private static final Map<String, Integer> romeNumberMap = new HashMap<>();
	private static final String[] keys = {"I", "V", "X", "L", "C", "D", "M", "IV", "IX", "XL", "XC", "CD", "CM"};
	private static final int[] values = {1, 5, 10, 50, 100, 500, 1000, 4, 9, 40, 90, 400, 900};
	private static final int KEY_MAX = 6;

	private static void init() throws IOException {
		input[0] = br.readLine().trim();
		input[1] = br.readLine().trim();

		for (int i = 0, size = keys.length; i < size; i++) {
			romeNumberMap.put(keys[i], values[i]);
		}
	}

	private static String solve(){
		StringBuilder sb = new StringBuilder();
		int sum = toArabia(input[0]) + toArabia(input[1]);
		sb.append(sum).append("\n").append(toRome(sum)).append("\n");

		return sb.toString();
	}

	private static int toArabia(String s) {
		int ret = 0;
		int prevValue = Integer.MIN_VALUE;

		for(int i = s.length() - 1 ; i >= 0 ; i--){
			String key = String.valueOf(s.charAt(i));
			int number = romeNumberMap.get(key);

			ret += number < prevValue ? -number : number;
			prevValue = number;
		}
		return ret;
	}

	private static String toRome(int n){
		StringBuilder ret = new StringBuilder();
		for(int i = KEY_MAX ; i >= 0; i--){
			int value = romeNumberMap.get(keys[i]);
			while(n / value >= 1){
				n -= value;
				ret.append(keys[i]);
			}
			value = romeNumberMap.get(keys[i + KEY_MAX]);
			if (i == 0 || n < value)     continue;

			n -= value;
			ret.append(keys[i + KEY_MAX]);
		}
		return ret.toString();
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(solve());
	}
}
