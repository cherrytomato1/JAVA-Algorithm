package jungol;

import java.io.*;

public class Main_1810_김민기 {

	
	private static int[] srcArr;
	private static int[] resArr;
	
	private static final int RES_MAX = 7;
	private static final int SRC_MAX = 9;
	private static StringBuilder sb;
	
	private static void f(int idx, int idxStart, int sum) {
		/** 백트래킹 */
		if(sb != null || sum > 100)	return;
		
		if(idx == RES_MAX) {
			if(sum == 100) {
				sb = new StringBuilder();
				for(int i = 0 ; i < RES_MAX ; i++) {
					sb.append(resArr[i]).append("\n");
				}
			}
			return ;
		}
		
		for(int i = idxStart; i < SRC_MAX ; i++) {
			resArr[idx] = srcArr[i];
			f(idx + 1, i + 1, sum + resArr[idx]);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		srcArr = new int[SRC_MAX];
		resArr = new int[RES_MAX];
		
		for(int i = 0 ; i < SRC_MAX; i++ ) {
			srcArr[i] = Integer.parseInt(br.readLine());
		}
		f(0,0,0);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
