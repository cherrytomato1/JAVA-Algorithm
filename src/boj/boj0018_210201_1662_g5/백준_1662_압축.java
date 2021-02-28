package boj.boj0018_210201_1662_g5;

import java.io.*;

public class 백준_1662_압축 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static char[] arr;

	static int[] f(int idx, int mod){
		int val = 0;
		int skipIdx = -1;
		for(int i = idx ; i < arr.length; i++){
			if(i <= skipIdx)  continue;

			if(arr[i] == '('){
				int[] returns = f(i + 1, arr[i - 1] - '0');
				val += returns[0] - 1;
				skipIdx = returns[1];
			}else if(arr[i] == ')'){
				return new int[]{val * mod, i};
			}
			else val++;
		}
		return new int[]{val * mod, 0};
	}

	static void init() throws IOException{
		arr = br.readLine().toCharArray();
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(f(0,1)[0]);
	}
}
