package boj;

import java.util.*;
import java.io.*;

public class 백준_1068_트리 {

	private static int[] nodes;
	private static boolean[] visited;
	private static int N;

	public static void main(String[] args) throws IOException{
		init();
//		System.out.println(solve());
	}

	private static void init() throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		nodes = new int[N];
		visited = new boolean[N];

		for (int i = 0; i < N ; i++) {
			nodes[i] = Integer.parseInt(st.nextToken());
		}

//		Arrays.sort(nodes, Collections.reverseOrder());
	}
}
