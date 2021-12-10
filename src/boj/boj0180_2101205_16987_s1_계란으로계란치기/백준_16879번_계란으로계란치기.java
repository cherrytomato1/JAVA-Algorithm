package boj.boj0180_2101205_16987_s1_계란으로계란치기;

import java.util.*;
import java.io.*;


public class 백준_16879번_계란으로계란치기 {
	private static class Egg {
		int durability;
		int weight;

		public Egg(int durability, int weight) {
			this.durability = durability;
			this.weight = weight;
		}
	}


	public static void main(String[] args) throws IOException {
		Egg[] eggs = init();
		System.out.println(dfs(eggs, 0));
	}

	private static Egg[] init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Egg[] eggs = new Egg[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		return eggs;
	}

	private static int dfs(Egg[] eggs, int index) {
		if (index >= eggs.length) {
			return 0;
		}
		if (eggs[index].durability <= 0) {
			return dfs(eggs, index + 1);
		}
		int ret = 0;
		for (int i = 0; i < eggs.length; i++) {
			int tempIndexDur = eggs[index].durability;
			int tempTargetDur = eggs[i].durability;

			if (tempTargetDur <= 0 || i == index) {
				continue;
			}

			int tempValue = 0;
			if ((eggs[i].durability -= eggs[index].weight) <= 0) {
				tempValue++;
			}
			if ((eggs[index].durability -= eggs[i].weight) <= 0) {
				tempValue++;
			}
			tempValue += dfs(eggs, index + 1);

			eggs[index].durability = tempIndexDur;
			eggs[i].durability = tempTargetDur;
			ret = Math.max(tempValue, ret);
		}
		return ret;
	}
}
