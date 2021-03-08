package boj.boj0061_210307_1300_g3;

import java.io.*;

public class 백준_1300번_K번째수 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static long N ;
	static long K ;

	static long check(long mid){
		long cnt = 0;
		for(long i = 1 ; i <= N; i++){
			cnt += Math.min(N, mid/i);
		}
		return cnt;
	}

	static long binarySearch(){
		long res = 1;
		long low = 1;
		long high = K;

		while ( low <= high){
			long mid = (low + high) / 2;
			if(check(mid) >= K){
				res = mid;
				high = mid - 1;
			}else low = mid + 1;
		}
		return res;
	}

	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		System.out.println(binarySearch());
	}

}
