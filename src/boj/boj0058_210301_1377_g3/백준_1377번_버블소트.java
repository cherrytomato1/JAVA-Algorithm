package boj.boj0058_210301_1377_g3;

import java.util.*;
import java.io.*;

public class 백준_1377번_버블소트 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static int[] arr;
	static int[] sortedArr;

	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
//		sortedArr = new int[N + 1];

		for(int i = 1 ; i <= N; i++){
			arr[i] = Integer.parseInt(br.readLine());
		}
		sortedArr = Arrays.copyOf(arr,arr.length);
		Arrays.sort(sortedArr);
	}

	static int solve(){
		int idx = 1;
		for(int i = 1 ; i <= N ; i++){
			if(sortedArr[idx] == arr[i]) idx++;
		}
		idx--;
		System.out.println(Arrays.toString(sortedArr));
		System.out.println("idx == " + idx + ", N ==" + N );
		return sortedArr[idx];
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

}
