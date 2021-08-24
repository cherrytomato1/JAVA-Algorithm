package boj.boj0172_210824_1949_g1;

import java.util.*;
import java.io.*;

public class 백준_1949번_우수마을 {
	private static class Town {
		int peopleCount;
		ArrayList<Integer> childTownList;

		public Town(int peopleCount) {
			this.peopleCount = peopleCount;
			this.childTownList = new ArrayList<>();
		}
	}

	private static int N;
	private static Town[] towns;
	private static int[][] maxValues;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(Math.max(dfs(0, 1, false), dfs(0, 1, true)));
	}

	private static void init() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		towns = new Town[N + 1];
		//true, false 일 때 최댓값 메모이제이션
		maxValues = new int[N + 1][2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N ; i++) {
			towns[i] = new Town(Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < N - 1 ; i++) {
			st = new StringTokenizer(br.readLine());
			int val1 = Integer.parseInt(st.nextToken());
			int val2 = Integer.parseInt(st.nextToken());

			int from = Math.min(val1, val2);
			int to = Math.max(val1, val2);

			towns[from].childTownList.add(to);
			towns[to].childTownList.add(from);
		}
	}

	private static int dfs(int from, int index, boolean isExcellent) {
		int excellentIndex = isExcellent ? 0 : 1;
		if (maxValues[index][excellentIndex] != 0) {
			return maxValues[index][excellentIndex];
		}

		if (isExcellent) {
			maxValues[index][excellentIndex] += towns[index].peopleCount;
		}
		for (int childIndex : towns[index].childTownList) {
			if (childIndex == from) {
				continue;
			}
			maxValues[index][excellentIndex] += Math.max(dfs(index, childIndex, false), isExcellent ? 0 : dfs(index, childIndex, true));
		}

		return maxValues[index][excellentIndex];
	}
}

/*

7
100 1 1 100 1 1 100
1 2
2 3
3 4
3 5
5 6
6 7


4
18 17 15 4
2 1
3 1
4 2

 */
