package boj.boj0103_210411_2473_g4;

import java.util.*;
import java.io.*;

public class 백준_2473번_세용액 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static long[] arr;
	private static long res = Long.MAX_VALUE;

	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);
	}

	private static long[] solve(){
		long[] ret = new long[3];
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N - 1 ; j++) {
				long target = arr[i] + arr[j];
				int resIdx = getIdx(j + 1, target);

				long temp = Math.abs(target + arr[resIdx]);
				if(temp >= res) continue;
				res = temp;
				ret[0] = arr[i];
				ret[1] = arr[j];
				ret[2] = arr[resIdx];
			}
		}
		return ret;
	}

	private static int getIdx(int startIdx, long target){
		int idx1 = binSearch(startIdx, N - 1, -target, true);
		int idx2 = binSearch(startIdx, N - 1, -target, false);
		return Math.abs(arr[idx1] + target) < Math.abs(arr[idx2] + target) ? idx1 : idx2;
	}

	private static int binSearch(int start, int end, long key , boolean type){
		int low = start;
		int high = end;
		while(low < high){
			int mid = (low + high) >> 1;
			long midVal = arr[mid];
			if(midVal < key)    low = mid + 1;
			else                high = mid - 1;
		}
		//찾지 못했다면 type == true은 가장 가까운 작은 수, false 는 가장 가까운 큰수 반환 low -1, low
		return type ? (start > low - 1 ? low : (low - 1)) : Math.min(end, low);
	}

	public static void main(String[] args) throws IOException{
		init();
		long[] ans = solve();
		System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
	}
}