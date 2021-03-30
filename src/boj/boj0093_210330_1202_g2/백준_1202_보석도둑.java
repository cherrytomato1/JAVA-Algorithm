package boj.boj0093_210330_1202_g2;

import java.util.*;
import java.io.*;

public class 백준_1202_보석도둑 {
	private static class Jewel implements Comparable<Jewel> {
		int val;
		int weight;
		public Jewel( int weight, int val){
			this.val = val;
			this.weight = weight;
		}
		@Override
		public int compareTo(Jewel j){
			return Integer.compare(j.val, this.val);
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int[] bags;
	private static int[] targetBags;
	private static Jewel[] jewels;
	private static int N;
	private static int K;

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		jewels = new Jewel[N];
		bags = new int[K];
		targetBags = new int[K];

		for(int i = 0 ; i < N; i++){
			st = new StringTokenizer(br.readLine());
			jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for(int i = 0 ; i < K; i++){
			bags[i] = Integer.parseInt(br.readLine());
			targetBags[i] = i;
		}

		Arrays.sort(jewels);
		Arrays.sort(bags);
	}

	private static long solve(){
		long ret = 0;
		for(int i = 0, cnt = 0 ; i < N && cnt != K; i++){
			int idx = binSearch(jewels[i].weight);
			idx = findBag(idx);
			if (idx >= K)   continue;
			ret += jewels[i].val;
			cnt++;
		}
		return ret;
	}

	private static int findBag(int x){
		if(x >= K)  return K;
		if(targetBags[x] == x)  return targetBags[x]++;
		return targetBags[x] = findBag(targetBags[x]);
	}

	private static int binSearch(int key){
		int low = 0;
		int high = K - 1;

		while (low <= high){
			int mid = (low + high) >> 1;
			int midVal = bags[mid];
			if(midVal < key)        low = mid + 1;
//			else if(midVal == key)  return mid;
			//같으면 high가 줄어드니까 -> 로우는 안줄어들 -> lower_bound 역할
			else                    high = mid - 1;
		}
		return low;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}