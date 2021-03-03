package boj.boj0058_210301_1377_g3;

import java.util.*;
import java.io.*;

public class 백준_1377번_버블소트 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static Integer[][] arr ;

	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		arr = new Integer[N][];

		for(int i = 0 ; i < N; i++){
			arr[i] = new Integer[]{Integer.parseInt(br.readLine()), i};
		}
		Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);
	}

	static int solve(){
		int ans = 0;

		for(int i = 0 ; i < N; i++){
			ans = Math.max(ans, arr[i][1] - i);
		}
		return ans + 1;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
