package boj.boj0160_210623_17140_g4;

import java.util.*;
import java.io.*;

public class 백준_17140번_이차원배열과연산 {
	private static class ArrayValue implements Comparable<ArrayValue>{
		int count;
		int value;

		public ArrayValue(int count, int value) {
			this.count = count;
			this.value = value;
		}

		@Override
		public int compareTo(ArrayValue o) {
			int ret = Integer.compare(this.count, o.count);
			return ret == 0 ? Integer.compare(this.value, o.value) : ret;
		}
	}

	private static int rowSize;
	private static int colSize;
	private static int r;
	private static int c;
	private static int k;
	private static int[][] arr;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}

	private static void init() throws IOException {
		InputStream in;
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		rowSize = 3;
		colSize = 3;
		arr = new int[100][100];

		for (int i = 0; i < rowSize ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < colSize ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int solve() {
		int ret = 0;
		while (arr[r - 1][c - 1] != k && ret++ <= 100) {
			sortArr(rowSize >= colSize);
		}
		return ret > 100 ? -1 : ret;
	}

	private static void sortArr(boolean type) {
		Map<Integer, Integer> map;
		Queue<ArrayValue> pq = new PriorityQueue<>();
		int maxSize = 0;

		for (int i = 0, iSize = type ? rowSize : colSize; i < iSize ; i++) {
			map = new HashMap<>();
			for (int j = 0, jSize = type ? colSize : rowSize; j < jSize ; j++) {
				int val = type ? arr[i][j] : arr[j][i];
				if(val == 0)  continue;
				map.put(val, map.containsKey(val) ? map.get(val) + 1 : 1);
			}

			map.forEach((key, value) ->{
				pq.offer(new ArrayValue(value, key));
			});

			maxSize = Math.max(maxSize, pq.size() * 2);
			int idx = 0;
			while (!pq.isEmpty()) {
				ArrayValue curr = pq.poll();
				arr[type ? i : idx++][type ? idx++ : i] = curr.value;
				arr[type ? i : idx++][type ? idx++ : i] = curr.count;
			}
			for (; idx < 100 ; idx++) {
				arr[type ? i : idx][type ? idx : i] = 0;
			}
		}

		if(type)    colSize = Math.min(maxSize, 100);
		else        rowSize = Math.min(maxSize, 100);
	}
}
