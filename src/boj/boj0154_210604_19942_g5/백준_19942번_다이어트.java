package boj.boj0154_210604_19942_g5;

import java.util.*;
import java.io.*;

public class 백준_19942번_다이어트 {
	private static class Food {
		int p;
		int f;
		int s;
		int v;
		int price;

		public boolean isAvailable(Food o) {
			return o.f <= this.f && o.p <= this.p && o.s <= this.s && o.v <= this.v;
		}

		public void addValue(int p, int f, int s, int v) {
			this.p += p;
			this.f += f;
			this.s += s;
			this.v += v;
		}
	}

	private static int N;
	private static Food[] foods;
	private static Food target;
	private static int resPrice;
	private static String resFlagString;

	public static void main(String[] args) throws IOException{
		init();
		recur(0, 0, 0);
		if(resPrice == Integer.MAX_VALUE)   System.out.println(-1);
		else                                System.out.println(resPrice +"\n" + resFlagString);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		foods = new Food[N];

		st = new StringTokenizer(br.readLine());
		target = new Food();
		target.addValue(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
				, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		for (int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			foods[i] = new Food();
            foods[i].addValue(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
					, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			foods[i].price = Integer.parseInt(st.nextToken());
		}

		resPrice = Integer.MAX_VALUE;
		resFlagString = "Z";
	}

	private static void recur(int idx, int flag, int price) {
		if(resPrice < price) return;
		if (idx == N) {
			if(!checkResult(flag))  return;
			String flagString = flagToString(flag);
			if (resPrice == price) {
				resFlagString = resFlagString.compareTo(flagString) >= 1 ? flagString : resFlagString;
			}
			else    resFlagString = flagString;
			resPrice = price;
			return;
		}

		recur(idx + 1, flag, price);
		recur(idx + 1, flag | 1 << idx, price + foods[idx].price);
	}

	private static boolean checkResult(int flag) {
		Food food = new Food();
		for (int i = 0; i < N ; i++) {
			if((flag & 1 << i) == 0)    continue;
			food.addValue(foods[i].p, foods[i].f, foods[i].s, foods[i].v);
		}
		return food.isAvailable(target);
	}

	private static String flagToString(int flag) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N ; i++) {
			if((flag & 1 << i) != 0)    sb.append(i + 1).append(" ");
		}
		return sb.toString();
	}
}
