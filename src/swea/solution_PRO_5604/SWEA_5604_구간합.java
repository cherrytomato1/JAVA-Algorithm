package swea.solution_PRO_5604;

import java.util.*;
import java.io.*;

public class SWEA_5604_구간합 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int start;
	private static int end;

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
	}

	private static long solve() {
		long ret = 0;
		long val = 1;
		while (start < end) {
//			System.out.println("go");
			while (start % 10 != 0 && start <= end) {
				ret += val;
				start++;
			}

			while (end % 10 != 9 && start <= end) {
				ret += val;
				end--;
			}
			end/= 10;
			start/= 10;
			val *= 10;

		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T ; tc++) {
			init();
			sb.append("#").append(tc).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);
	}
}
