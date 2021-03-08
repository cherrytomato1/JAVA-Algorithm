package datastruct;

public class BinarySearch {
	public static int binarySearch(int[] arr, int val){
		int low = 0;
		int high = arr.length - 1;

		while(low <= high){
			int mid = (low + high) / 2;
			if(arr[mid] == val)     return mid;
			if(arr[mid] < val)      low = mid + 1;
			else if(arr[mid] > val) high = mid - 1;
		}
		return -1;
	}
}
