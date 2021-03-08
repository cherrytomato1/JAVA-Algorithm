package boj.boj0062_210308_14715_s1;
import java.io.*;
import java.util.*;

public class 백준_14715번_전생했더니_dp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int K;
	static int[] dp;

	static void init() throws IOException{
		K = Integer.parseInt(br.readLine());
		dp = new int[K + 1];
		Arrays.fill(dp, Integer.MIN_VALUE);
	}

	static int recur(int n){
		if(dp[n] != Integer.MIN_VALUE)  return dp[n];

		int max = (int)Math.sqrt(n);
		int ret = Integer.MAX_VALUE;

		for(int j = 2; j <= max ; j++ ){
			if(n % j != 0 )     continue;
			int val = Math.max(recur(n/j) + 1, recur(j) + 1);
			ret = Math.min(val, ret);
		}
		return dp[n] = ret == Integer.MAX_VALUE ? 0 : ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(recur(K));
	}
}
