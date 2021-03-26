package boj.boj0080_210325_12015_g2;

import java.util.*;
import java.io.*;

public class 백준_12015번_가장긴증가하는부분수열2 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int[] arr;
	private static int[] d;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		d = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static int solve(){
		int size = 0;
		for(int i = 0 ; i < N ; i++){
			int idx = Math.abs(binSearch(0, size, arr[i]));
			d[idx] = arr[i];
			if(idx == size)     size++;
		}
		return size;
	}

	private static int binSearch(int start, int end, int key){
		int low = start;
		int high = end - 1;

		while(low <= high){
			int mid = (low + high) >> 1;
			int midVal = d[mid];
			if(midVal < key)        low = mid + 1;
			else if(midVal == key)  return mid;
			else                    high = mid - 1;
		}
		return -low;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
//		System.out.println(Arrays.toString(arr));
	}
}
