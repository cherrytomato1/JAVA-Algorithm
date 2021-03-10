package boj.boj0064_210310_2143_g3;

import java.util.*;
import java.io.*;

public class 백준_2143번_두배열의합 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static Map<Integer,Integer> A = new HashMap<>();
	static Map<Integer,Integer> B = new HashMap<>();

	static int T;
	static int N;
	static int M;

	static int[] arrA;
	static int[] arrB;

	static void init() throws IOException{
		T = Integer.parseInt(br.readLine());

		N = Integer.parseInt(br.readLine());
		arrA = new int[N];
		st = new StringTokenizer(br.readLine());

		for(int i = 0 ; i < N; i++){
			arrA[i] = Integer.parseInt(st.nextToken());
			for(int j = i, sum = 0; j >= 0 ; j--){
				sum += arrA[j];
				A.put(sum, A.containsKey(sum) ?  A.get(sum) + 1 : 1);
			}
		}

		M = Integer.parseInt(br.readLine());
		arrB = new int[M];
		st = new StringTokenizer(br.readLine());

		for(int i = 0 ; i < M; i++){
			arrB[i] = Integer.parseInt(st.nextToken());
			for(int j = i, sum = 0; j >= 0; j--){
				sum += arrB[j];
				B.put(sum, B.containsKey(sum) ?  B.get(sum) + 1 : 1);
			}
		}
	}

	static long solve(){
		long ret = 0;
		for(Integer aKey : A.keySet()){
//			System.out.println( aKey + " " + A.get(aKey) + " ");
			int find = T - aKey;
			ret += B.containsKey(find) ? (long)A.get(aKey) * B.get(find) : 0;
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
