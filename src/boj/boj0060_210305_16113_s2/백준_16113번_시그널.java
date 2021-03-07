package boj.boj0060_210305_16113_s2;

import java.io.*;

public class 백준_16113번_시그널 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String[] strArr;

	static int N;
	static int length;

	static void init() throws IOException{

		N = Integer.parseInt(br.readLine());
		String str = br.readLine();

		strArr = new String[N];
		length = N/5;

		for(int i = 0; i < 5 ; i++){
			int subIdx = length * i;
			strArr[i] = str.substring(subIdx, subIdx + length);
		}
	}

	static String solve(){
		StringBuilder res = new StringBuilder();
		for(int i = 0 ; i < length; i++){
			if(strArr[0].charAt(i) != '#')  continue;

			int cnt = check(i);
//			System.out.println(i);
			if(cnt == 1)    i++;
			else            i += 2;
			res.append(cnt);
		}
		return res.toString();
	}

	static int check(int idx){
		boolean flag = true;
		for(int i = 0 ; i < 5; i++){
			for(int j = 0 ; j < 2 && idx + j < length; j++) {
				if (strArr[i].charAt(idx + j) != (j == 0 ? '#' : '.')) {
					flag = false;
					break;
				}
			}
		}
		if(flag)    return 1;

		int cnt = 0;
		for(int i = idx ; i < idx + 3 ; i++){
			for(int j = 0 ; j < 5; j++){
				if(strArr[j].charAt(i) == '#') cnt ++;
			}
		}

		switch(cnt){
			case 9 : return 4;
			case 7 : return 7;
			case 13 : return 8;
			default : return findNum(cnt, idx);
		}
	}

	static int findNum(int num, int idx){
		if(num == 11){
			if(strArr[1].charAt(idx) == '#')    return 5;
			if(strArr[3].charAt(idx) == '#')    return 2;
			return 3;
		}else if(num == 12) {
			if (strArr[2].charAt(idx + 1) != '#') return 0;
			if (strArr[3].charAt(idx) == '#') return 6;
			return 9;
		}
		return -1;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.print(solve());
	}
}
