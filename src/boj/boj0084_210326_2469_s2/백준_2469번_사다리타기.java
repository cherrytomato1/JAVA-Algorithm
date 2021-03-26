package boj.boj0084_210326_2469_s2;

import java.util.*;
import java.io.*;

public class 백준_2469번_사다리타기 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int K;
	private static int N;

	private static char[] in;
	private static char[] out;

	private static boolean[][] top;
	private static boolean[][] bottom;
	private static int topLen;
	private static int botLen;

	private static void init() throws IOException{
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());

		in = new char[K];
		for(int i = 0 ; i < K; i++){
			in[i] = (char)('A' + i);
		}
		out = br.readLine().toCharArray();

		top = new boolean[N][K - 1];
		bottom = new boolean[N][K - 1];

		for(int i = 0 ; i < N; i++){
			char[] line = br.readLine().toCharArray();
			if(line[0] == '?')  {
				topLen = i;
				botLen = N - topLen - 1;
				break;
			}
			for(int j = 0 ; j < K - 1; j++){
				top[i][j] = line[j] == '-';
			}
		}

		for(int i = 0; i < botLen; i++){
			char[] line = br.readLine().toCharArray();
			for(int j = 0 ; j < K - 1; j++){
				bottom[i][j] = line[j] == '-';
			}
		}

		for(int i = 0, j = botLen - 1; i < j ; i++, j-- ){
			boolean[] temp = bottom[i];
			bottom[i] = bottom[j];
			bottom[j] = temp;
		}
	}

	private static String solve() {
		char[] ret = new char[K - 1];
		Arrays.fill(ret, '*');
		char[] topRes = go(in, top, topLen);
		char[] botRes = go(out, bottom, botLen);

		boolean isAvailable = true;
		for(int i = 0; i < K - 1; i++){
			if(topRes[i] == botRes[i]) continue;
			if(topRes[i] != botRes[i + 1] || topRes[i + 1] != botRes[i]){
				isAvailable = false;
				break;
			}
			ret[i++] = '-';
		}
		if(!isAvailable)    Arrays.fill(ret, 'x');
		return String.valueOf(ret);
	}



	private static char[] go(char[] start, boolean[][] map, int len){
		char[] ret = Arrays.copyOf(start, start.length);
		for(int i = 0; i < len; i++){
			for(int j = 0 ; j < K - 1; j++){
				if(map[i][j])   swapIdx(j, j + 1, ret);
			}
		}
		return ret;
	}

	private static void swapIdx(int idx1, int idx2, char[] arr){
		char temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
