package boj.boj0083_210326_17411_p2;

import java.util.*;
import java.io.*;

public class 백준_17411번_가장긴증가하는부분수열6 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static final long mod = 1000000000 + 7;

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

	private static String solve(){
		int size = 0;
		long count = 0;
		for(int i = 0 ; i < N; i++){
			int idx = binSearch(0, size, arr[i]);
			d[idx] = arr[i];
			count++;
			count %= mod;
			if(idx == size) {
//				System.out.println(size + " " + count);
				count = 1;
				size++;
			}
		}
		return size + " " + count;
	}

	private static int binSearch(int start, int end, int key){
		int low = start;
		int high = end - 1;
		while (low <= high){
			int mid = (low + high) >> 1;
			int midVal = d[mid];
			if(midVal < key)        low = mid + 1;
			else if(midVal == key)  return mid;
			else                    high = mid - 1;
		}
		return low;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(d));
	}
}
