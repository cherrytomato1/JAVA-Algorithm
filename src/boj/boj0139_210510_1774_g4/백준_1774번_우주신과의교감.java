package boj.boj0139_210510_1774_g4;

import java.io.*;
import java.util.*;

/*
	잘못된 comparator
	Queue<Object[]> pq = new PriorityQueue<>((o1, o2) -> (int)((double)o1[VAL] - (double)o2[VAL]));

	변경 후
	Queue<Object[]> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> (double) o[VAL]));
 */
public class 백준_1774번_우주신과의교감 {
	private static double[][] arr;
	private static double[] min;
	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException{
		init();
		System.out.printf("%.2f", solve(0));
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] gods = new int[N][2];
		arr = new double[N][N];
		min = new double[N];

		for (int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			gods[i][0] = Integer.parseInt(st.nextToken());
			gods[i][1] = Integer.parseInt(st.nextToken());
			min[i] = Double.MAX_VALUE;
		}

		for (int i = 0; i < N ; i++) {
			for (int j = i + 1; j < N ; j++) {
				arr[j][i] = arr[i][j] = getDist(gods[i], gods[j]);
			}
		}

		for (int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			arr[from][to] = 0;
			arr[to][from] = 0;
		}
	}

	private static double getDist(int[] x, int[] y) {
		double val1 = Math.pow(x[0] - y[0], 2);
		double val2 = Math.pow(x[1] - y[1], 2);

		return Math.sqrt(val1 + val2);
	}

	private static double solve(int start) {
		final int IDX = 0;
		final int VAL = 1;
		boolean[] visited = new boolean[N];

		Queue<Object[]> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> (double) o[VAL]));
		min[start] = 0;
		pq.offer(new Object[]{start, 0.0});

		double ret = 0;
		for (int i = 0; i < N ; i++) {
			Object[] cur;
			do{
				cur = pq.poll();
			} while(visited[(int)cur[IDX]]);

			int idx = (int)cur[IDX];
			ret += (double)cur[VAL];
			visited[idx] = true;

			for (int j = 0; j < N ; j++) {
				if(visited[j] | min[j] <= arr[idx][j])  continue;
				min[j] = arr[idx][j];
				pq.offer(new Object[]{j, min[j]});
			}
		}

		return ret;
	}
}

