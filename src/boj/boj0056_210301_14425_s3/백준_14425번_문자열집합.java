package boj.boj0056_210301_14425_s3;

import java.io.*;
import java.util.*;

public class 백준_14425번_문자열집합 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static Set<String> set = new HashSet<>(10000);
	static int N;
	static int M;
//	static String[] S;

	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

//		S = new String[N];
//		for(int i = 0 ; i < N; i++){
//			S[i] = br.readLine();
//		}
		for(int i = 0 ; i < N; i++) {
//			map.put(br.readLine(), i);
			set.add(br.readLine());
		}
	}

	static int solve() throws IOException{
		int cnt = 0;
		for(int i = 0; i < M; i++){
			String str = br.readLine();

//			for(String s : S){
//				if(s.equals(str)){
//					cnt++;
//					break;
//				}
//			}
			if(set.contains(str))    cnt++;
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
