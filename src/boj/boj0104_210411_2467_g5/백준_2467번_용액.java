package boj.boj0104_210411_2467_g5;

import java.util.*;
import java.io.*;

public class 백준_2467번_용액 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static long[] numbers;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		numbers = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N ; i++) {
			numbers[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(numbers);
	}

	private static long[] solve(){
		long[] res = new long[3];
		res[0] = Long.MAX_VALUE;
		int low = 0;
		int high = N - 1;

		while (low != high){
			long sum = numbers[low] + numbers[high];
			if(Math.abs(sum) < res[0]){
				res[0] = Math.abs(sum);
				res[1] = numbers[low];
				res[2] = numbers[high];
			}
			if(sum == 0)    break;
			if(sum < 0)     low++;
			else            high--;
		}
		return res;
	}

	public static void main(String[] args) throws IOException{
		init();
		long[] res = solve();
		System.out.println(res[1] + " " + res[2]);
	}

}
