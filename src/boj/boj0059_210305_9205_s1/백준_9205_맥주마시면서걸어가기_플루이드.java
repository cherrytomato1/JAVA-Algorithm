package boj.boj0059_210305_9205_s1;

import java.util.*;
import java.io.*;

public class 백준_9205_맥주마시면서걸어가기_플루이드 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st ;

	private static int N;
	private static int[][] dist;
	private static boolean[][] visited;
	private static List<int[]> pos = new ArrayList<>();

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		dist = new int[N + 2][N + 2];
		visited = new boolean[N + 2][N + 2];
		pos = new ArrayList<>();

		for (int i = 0; i <= N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pos.add(new int[] { x, y });
		}
	}

	private static boolean solve(){
		for (int i = 0; i <= N + 1; i++) {
			for (int j = 0; j <= N + 1; j++) {
				dist[i][j] = Math.abs(pos.get(i)[0] - pos.get(j)[0]) + Math.abs(pos.get(i)[1] - pos.get(j)[1]);
				if (dist[i][j] <= 20 * 50) visited[i][j] = true;
			}
		}

		for (int i = 0; i <= N + 1; i++) {
			for (int j = 0; j <= N + 1; j++) {
				for (int k = 0; k <= N + 1; k++) {
					if (visited[j][i] & visited[i][k]) visited[j][k] = true;
				}
			}
		}

		return visited[0][N + 1];
	}

	public static void main(String[] args) throws IOException{

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			init();
			System.out.println(solve() ? "happy" : "sad");
		}
	}
}
