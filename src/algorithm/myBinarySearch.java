package algorithm;

public class myBinarySearch {
	public static int binarySearch(int[] arr, int start, int end, int key) {
		int low = start;
		int high = end - 1;

		while(low <= high) {
			int mid = (low + high) >> 1;
			int midVal = arr[mid];
			if (midVal < key) {
				low = mid + 1;
			} else {
				if (midVal <= key) {
					return mid;
				}
				high = mid - 1;
			}
		}

		//못찾았을 때
//		return -(high + 2); 와 같음
		return -(low + 1);
	}

	private static int lowerBound(int[] arr, int start, int end, int key){
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
}
