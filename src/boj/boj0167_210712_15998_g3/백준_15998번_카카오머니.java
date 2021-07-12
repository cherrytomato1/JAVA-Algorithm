package boj.boj0167_210712_15998_g3;

import java.util.*;
import java.io.*;

public class 백준_15998번_카카오머니 {
	private static final long MAX_VALUE = 9_000_000_000_000_000_000L;

	public static void main(String[] args) throws IOException{
		System.out.println(solve());
	}

	private static long solve() throws IOException{
		long maxRemain = 0;
		long res = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long prevRemain;
		long remain = 0;
		for (int i = 0; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long val = Long.parseLong(st.nextToken());
			prevRemain = remain;
			remain = Long.parseLong(st.nextToken());

			if(!isValid(remain, prevRemain, val))    return -1;
			if(val >= 0 || prevRemain + val >= 0)    continue;

			maxRemain = Math.max(maxRemain, remain);
			long sub = -(val + prevRemain) + remain;

			res = makeRes(res , sub, maxRemain);
			if(res == -1)   return -1;
		}
		return res == 0 ? 1 : res;
	}

	private static boolean isValid(long remain, long prevRemain, long val) {
		if(val >= 0)    return prevRemain + val == remain;
		if(prevRemain + val >= 0)   return val + prevRemain == remain;
		return true;
	}

	private static long makeRes(long res, long sub, long maxRemain) {
		long gcd = gcd(Math.max(res, sub), Math.min(res, sub));

		if(gcd <= maxRemain) return -1;

		long ret = makeMin(gcd, maxRemain);
		return ret > MAX_VALUE ? -1 : ret;
	}

	private static long gcd(long a, long b) {
		if(b == 0)  return a;
		return gcd(b, a % b);
	}

	private static long makeMin(long gcd, long maxVal) {
		for (int i = 2; i < Math.sqrt(gcd) ; i++) {
			if(gcd % i < maxVal)    return gcd;
			if(gcd % i == 0)    gcd %= i--;
		}
		return gcd;
	}
}
