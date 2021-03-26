package boj.boj0082_210326_14003_p5;

import java.util.*;
import java.io.*;

public class 백준_14003번_가장긴증가하는부분수열5 {
	private static class Number implements Comparable<Number>{
		int val;
		int prevIdx;
		int idx;

		public Number(int val, int idx, int prevIdx){
			this.val = val;
			this.idx = idx;
		}

		@Override
		public int compareTo(Number p){
			return Integer.compare(this.val, p.val);
		}

		@Override
		public String toString(){
			return this.val + " " + this.prevIdx;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N;
	private static Number[] numbers;
	private static Number[] d;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		numbers = new Number[N];
		d = new Number[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++){
			numbers[i] = new Number(Integer.parseInt(st.nextToken()), i, -1);
		}
	}

	private static int solve() {
		int size = 0;
		for(int i = 0 ; i < N ; i++){
			int idx = Math.abs(binSearch(0, size, numbers[i].val));
			numbers[i].prevIdx = idx == 0 ? -1 : d[idx - 1].idx;
			d[idx] = numbers[i];
			if(size == idx)     size++;
		}
		return size;
	}

	private static void printArr(int end){
		Deque<Integer> stk = new ArrayDeque<Integer>();
		stk.push(d[end].val);
		int idx = d[end].prevIdx;
		while(idx != -1){
			stk.push(numbers[idx].val);
			idx = numbers[idx].prevIdx;
		}
		while(!stk.isEmpty()){
			sb.append(stk.pop()).append(" ");
		}

	}

	private static int binSearch(int start, int end, int key){
		int low = start;
		int high = end - 1;
		while(low <= high){
			int mid = (low + high) >> 1;
			int midVal = d[mid].val;
			if(midVal < key)       low = mid + 1;
			else if(midVal == key)     return mid;
			else                    high = mid - 1;
		}
		return -low;
	}

	public static void main(String[] args) throws IOException{
		init();
		int len = solve();
		sb.append(len).append("\n");
		printArr(len - 1);
		System.out.println(sb);
//		System.out.println(Arrays.toString(d));
	}
}
