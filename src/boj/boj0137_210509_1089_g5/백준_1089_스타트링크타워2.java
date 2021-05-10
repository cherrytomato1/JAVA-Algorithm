package boj.boj0137_210509_1089_g5;

import java.io.*;

public class 백준_1089_스타트링크타워2 {
	private static final String[] numbers=
		{
			"###...#.###.###.#.#.###.###.###.###.###",
			"#.#...#...#...#.#.#.#...#.....#.#.#.#.#",
			"#.#...#.###.###.###.###.###...#.###.###",
			"#.#...#.#.....#...#...#.#.#...#.#.#...#",
			"###...#.###.###...#.###.###...#.###.###",
		};

	private static int N;
	private static char[][] arr;

	public static void main(String[] args) throws IOException{
		init();
		double res = solve();
		if(res == -1)   System.out.println(-1);
		else            System.out.printf("%.6f", res);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()) * 4 - 1;
		arr = new char[5][N];

		for (int i = 0; i < 5; i++) {
			arr[i] = br.readLine().toCharArray();
		}
	}

	private static double solve() {
		double ret = 0;

		int temp = (int)Math.pow(10, N/4);
		for (int i = 0; i < N ; i += 4, temp /= 10) {
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
				if(arr[i][j] != '#')  continue;
				if(numbers[i].charAt(row + num * 4) != '#') return false;
			}
		}
		return true;
	}
}

