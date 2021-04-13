package boj.boj0106_210412_2564_s1;

import java.util.*;
import java.io.*;

public class 백준_2564번_경비원 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static final int[] KEYS = {1, 4, 3, 2};
	private static int R;
	private static int C;
	private static int N;
	private static int length;
	private static int[] stores;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());

		length = R * 2 + C * 2;
		stores = new int[N + 1];

		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 0);
		map.put(4, C);
		map.put(2, length - R);
		map.put(3, length);

		for (int i = 0; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			stores[i] = map.get(dir) + (dir / 2 == 1 ? -dist : + dist);
		}
	}

	private static int solve(){
		int ret = 0;
		for (int i = 0; i < N ; i++) {
			int sub = stores[N] - stores[i];
			ret += Math.min(Math.abs(sub)
					, Math.min(Math.abs(sub - length), Math.abs(sub + length)));
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
