package boj.boj0178_2101122_17449_g2_순위계산;

import java.util.*;
import java.io.*;

/**
 * 1. 첫 1번째 수로 현재 순위가 주어진다 (동점이 있을 수 있음)
 * 2. 현재 등수와 비교하여 높거나 낮을 경우 순위가 조정된다.
 * 1. 높을 경우 -> 탐레프의 순위가 1 밀린다.
 * 2. 같을 경우 -> 탐레프의 순위가 1 밀리거나 동점이다.(가능한 순위 분기)
 * 3. 낮을 경우 -> 탐레프의 순위에는 변동이 없거나, 동점자 경우의 수가 줄어든다.
 */

public class 백준_17449번_순위계산 {

	public static void main(String[] args) throws IOException {
		solution();
	}

	private static void solution() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int highRank = Integer.parseInt(br.readLine());
		int availableTieCount = 0;
//		int lowRankRange = highRank;

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (n-- > 0) {
			int next = Integer.parseInt(st.nextToken());
//			System.out.println("next: " +  next + " high : " + highRank + " availTie : " + availableTieCount);
			if (next < highRank) {
				highRank++;
				continue;
			}
			if (next == highRank) {
				availableTieCount++;
				continue;
			}
			if (next == highRank + availableTieCount) {
//				System.out.println("next == highRank + availableTieCount");
				highRank = next;
				availableTieCount = 1;
				continue;
			}
			if (next < highRank + availableTieCount) {
//				System.out.println("next < highRank + availableTieCount");
				availableTieCount = highRank + availableTieCount - next;
				highRank = next + 1;
			}
		}
		System.out.println(highRank + " " + (highRank + availableTieCount));
	}
}

/*


3
6
3 3 3 3 3 4


 */