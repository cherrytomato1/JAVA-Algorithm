package boj.boj0161_210623_17387_g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_17387번_선분교차2 {
	private static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static Pos a;
	private static Pos b;
	private static Pos c;
	private static Pos d;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(solve() ? 1 : 0);
	}

	private static void init() throws IOException {
		InputStream in = System.in;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		a = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		b = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		st = new StringTokenizer(br.readLine(), " ");
		c = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		d = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	}

	private static boolean solve() {
		int ab = ccw(a, b, c) * ccw(a, b, d);
		int cd = ccw(c, d, a) * ccw(c, d, b);

		//두 선분이 같은 기울기로 연장되어 있는 경우
		if (ab == 0 && cd == 0)     return checkOverlap();
		return ab <= 0 && cd <= 0;
	}

	private static int ccw(Pos p1, Pos p2, Pos p3) {
		long val = (long)p1.x * p2.y + (long)p2.x * p3.y + (long)p3.x * p1.y;
		return Long.compare(val, (long)p1.y * p2.x + (long)p2.y * p3.x + (long)p3.y * p1.x);
	}

	private static boolean checkOverlap() {
		if(Math.min(a.x, b.x) > Math.max(c.x, d.x)) return false;
		if(Math.max(a.x, b.x) < Math.min(c.x, d.x)) return false;
		if(Math.min(a.y, b.y) > Math.max(c.y, d.y)) return false;
		if(Math.max(a.y, b.y) < Math.min(c.y, d.y)) return false;
		return true;
	}
}
