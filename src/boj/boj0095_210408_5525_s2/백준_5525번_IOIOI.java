package boj.boj0095_210408_5525_s2;

import java.io.*;

public class 백준_5525번_IOIOI {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static int M;
	private static char[] chars;
	private static final char[] IOI = {'I', 'O', 'I'};

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		chars = br.readLine().toCharArray();
	}

	private static int solve() {
		int ret = 0;
		loop :
		for(int i = 0, len = 0 ; i < M - 2; i++){
			for(int j = 0 ; j < 3; j++){
				if(chars[i + j] == IOI[j])  continue;
				len = 0;
				continue loop;
//				if(chars[i + j] != IOI[j] && (len = 0) == 0)    continue loop;
			}
			if(++len >= N)  ret++;
			i++;
//			if(i++ >= 0 && ++len >= N ) ret++;
//			ret = ++len >= N ? 1 : (i++) * 0;
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
