package algorithm;

public class MyBinarySearch {
	/*
		@params arr : 원소를 찾을 배열
		@params start : 탐색 시작 인덱스
		@params end : 탐색 마지막 인덱스
		@params key : 찾을 원소 값
	*/
	public static int binarySearch(int[] arr, int start, int end, int key) {
		int low = start;
		int high = end;

		while(low <= high) {
			int mid = (low + high) >> 1;
			int midVal = arr[mid];
			if (midVal < key) {
				low = mid + 1;
			} else if (midVal == key){
				return mid;
			}
			else {
				high = mid - 1;
			}
		}

		//못찾았을 때
//		return -(high + 2); 와 같음
		return -(low + 1);
	}

	/*
		@params arr : 원소를 찾을 배열
		@params start : 탐색 시작 인덱스
		@params end : 탐색 마지막 인덱스
		@params key : 찾을 원소 값
	*/
	public static int lowerBound(int[] arr, int start, int end, int key){
		int low = start;
		int high = end;

		while (low <= high){
			int mid = (low + high) >> 1;
			int midVal = arr[mid];
			if(midVal < key)        low = mid + 1;
//			else if(midVal == key)  return mid;
				//같으면 high가 줄어드니까 -> 로우는 안줄어들 -> lower_bound 역할
			else                    high = mid - 1;
		}

		return low;
	}
	/*
		@params arr : 원소를 찾을 배열
		@params start : 탐색 시작 인덱스
		@params end : 탐색 마지막 인덱스
		@params key : 찾을 원소 값
	*/
	public static int upperBound(int[] arr, int start, int end, int key) {
		int low = start;
		int high = end;

		while (low <= high) {
			int mid = (low + high) >> 1;
			int midVal = arr[mid];
			if(midVal > key)    high = mid - 1;
			//			else if(midVal == key)  return mid;
			//같으면 low가 커지니 -> upper_bound 역할
			else                low = mid + 1;
		}

		return high;
	}
}
