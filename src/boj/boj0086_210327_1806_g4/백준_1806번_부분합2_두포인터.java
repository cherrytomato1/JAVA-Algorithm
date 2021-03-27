package boj.boj0086_210327_1806_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1806번_부분합2_두포인터 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int S;

	private static int[] arr;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static int solve(){
		int ret = Integer.MAX_VALUE;
		int p1 = 0;
		int p2 = 0;
		int sum = 0;
		while(true){
			if(sum < S)     {
				if(p1 == N) break;
				sum += arr[p1++];
			}
			else{
				ret = Math.min(p1 - p2, ret);
				sum -= arr[p2++];
			}
		}
		return ret == Integer.MAX_VALUE ? 0 : ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
