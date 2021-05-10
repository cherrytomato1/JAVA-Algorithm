package boj.boj0137_210509_1089_g5;

import java.io.*;
public class 백준_1089_스타트링크타워 {
	private static final boolean[][] chars=
		{
			{ true ,   true,   true },
			{ false,   false,  true },
			{ true ,   false,  false},
			{ true ,   false,  true },
		};
	private static boolean[][][] numbers;

	private static int N;
	private static boolean[][] arr;

	public static void main(String[] args) throws IOException{
		init();
		double res = solve();
		if(res == -1)   System.out.println(-1);
		else            System.out.printf("%.6f", res);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()) * 4 - 1;
		arr = new boolean[5][N];

		makeNumbers();

		for (int i = 0; i < 5; i++) {
			char[] in = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				arr[i][j] = in[j] == '#';
			}
		}
	}

	private static void makeNumbers() {
		numbers = new boolean[10][5][];
		for (int i = 0; i < 10; i++) {
			if (i == 1 || i == 4) continue;
			numbers[i][0] = chars[0];
			if (i == 7) continue;
			numbers[i][4] = chars[0];
			if (i == 0) continue;
			numbers[i][2] = chars[0];
		}
		numbers[4][2] = chars[0];

		for (int i = 0; i < 10; i++) {
			if (i == 0 || i == 4 || i == 5 || i == 6 || i == 8 || i == 9) continue;
			numbers[i][1] = chars[1];
			if (i == 2) continue;
			numbers[i][3] = chars[1];
			if (i == 3) continue;
			numbers[i][2] = numbers[i][4] = chars[1];
		}

		numbers[1][0] = numbers[5][3] = numbers[4][3] = numbers[4][4]
                = numbers[9][3] = chars[1];
		numbers[2][3] = numbers[5][1] = numbers[6][1] = chars[2];
		numbers[0][1] = numbers[0][3] = numbers[4][1] = numbers[6][3]
                = numbers[8][1] = numbers[8][3] = numbers[9][1] = numbers[0][2]
				= numbers[4][0] = chars[3];
	}

	private static double solve() {
		double ret = 0;

		for (int i = 0; i < N ; i+= 4) {
			int temp = (int)Math.pow(10, N/4 - i/4);
			int val = 0;
			int cnt = 0;
			for (int j = 0; j < 10; j++) {
				if(!checkNum(i, j)) continue;
				cnt++;
				val += j;
			}
			if(cnt == 0)    return -1;
			ret += (double)val * temp / cnt;
		}
		return ret;
	}

	private static boolean checkNum(int idx, int num) {
		for (int i= 0; i < 5 ; i++) {
			for (int j = idx, row = 0; j < idx + 3 ; j++, row++) {
				if(!arr[i][j])  continue;
				if(!numbers[num][i][row]) return false;
			}
		}
		return true;
	}
}

