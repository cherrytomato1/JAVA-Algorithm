package algorithm;

import java.util.*;
import java.io.*;

public class LIS {
	private int[] arr;
	private int[] LIS;
	private int N;

	public LIS() throws IOException{
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		LIS = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

	//O(N^2) 이므로 N <= 10,000일 때만 가능
	public int getLIS(){
		int ret = 0;
		for(int i = 0 ; i < N; i++){
			for(int j = 0 ; j < i; j++){
				if(arr[j] >= arr[i])     continue;
				LIS[i] = Math.max(LIS[j], LIS[i]);
			}
			ret = Math.max(++LIS[i], ret);
		}
		return ret;
	}

	public int getLIS2BinarySearch(){
		int size = 0;
		LIS[size] = arr[size++];
		for(int i = 1 ; i < N; i++){
			//LIS 배열의 0번 인덱스부터 size 인덱스까지, arr[i]과 같은 값을 찾는다.
			//binarySearch 같은 경우 찾으면 해당 데이터 index, 못 찾으면 음수 값으로 마지막 까지 찾은 위치
			int temp = myBinarySearch.binarySearch(LIS, 0, size,  arr[i]);
			temp = temp >= 0 ? temp : temp * -1 -1;

			LIS[temp] = arr[i];
			//LIS 맨 뒤에 추가한 경우 size ++
			if(temp == size)    size++;
		}
		return size;
	}
}
