package swea.solution_D3_3307;

import java.util.*;
import java.io.*;

public class swea_3307_최장증가부분수열 {
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

	private static int lis(){
		int size = 0;
		d[size] = arr[size++];
		for( int i = 1 ; i < N; i++){
//			int idx = Math.abs(binSearch(arr[i]));
			int idx = Math.abs(binSearch(0, size, arr[i]));
//			System.out.println(idx);
//			idx = idx < 0 ? idx * -1 - 1 : idx;

			d[idx] = arr[i];
			if(idx == size) size++;
		}
		return size;
	}

	private static int binSearch(int start, int end, int key){
		int low = start ;
		int high = end - 1;
		while(low <= high){
			int mid = (low + high) >> 1;
			int midVal = d[mid];
			if(midVal < key)        low = mid + 1;
			else{
				if(midVal == key)   return mid;
				high = mid - 1;
			}
		}
//		return -(low + 1);
		return -low;
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T ; tc ++) {
			sb.append("#").append(tc).append(" ");
			init();
			sb.append(lis()).append("\n");
		}
		System.out.println(sb);
	}
}
