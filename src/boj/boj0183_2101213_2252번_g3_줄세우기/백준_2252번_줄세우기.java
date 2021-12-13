package boj.boj0183_2101213_2252번_g3_줄세우기;

import java.io.*;
import java.util.*;

/**
 * 위상정렬 기본문제.
 */

public class 백준_2252번_줄세우기 {
	private static class Vertex {
		int parentCount;
		List<Integer> edgeList;

		public Vertex(int parentCount) {
			this.parentCount = parentCount;
			edgeList = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		Queue<Integer> q = new ArrayDeque<>();
		List<Vertex> vertexList = new ArrayList<>();
		int n = init(vertexList);
		System.out.println(solve(n, q, vertexList));
	}

	public static int init(List<Vertex> vertexList) throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		vertexList.add(new Vertex(Integer.MAX_VALUE));

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++) {
			vertexList.add(new Vertex(0));
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			vertexList.get(from).edgeList.add(to);
			vertexList.get(to).parentCount++;
		}
		return n;
	}

	public static String solve(int N, Queue<Integer> q, List<Vertex> vertexList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (vertexList.get(i).parentCount == 0) {
				q.offer(i);
			}
		}

		while (N-- > 0) {
			if (q.isEmpty()) {
				return "Graph has Cycle !!";
			}
			int next = q.poll();
			sb.append(next).append(" ");
			for (Integer target : vertexList.get(next).edgeList) {
				if (--vertexList.get(target).parentCount == 0) {
					q.offer(target);
				}
			}
		}

		return sb.toString();
	}
}
