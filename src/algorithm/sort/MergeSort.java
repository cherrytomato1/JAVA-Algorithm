package algorithm.sort;

public class MergeSort {
	private static int[] tempArray;
	public static void mergeSort(int[] array) {
		tempArray = new int[array.length];
		mergeSort(array, 0, array.length - 1);
	}

	private static void mergeSort(int[] dest, int start, int end) {
		if(start == end)    return;

		int mid = (start + end) >> 1;
		mergeSort(dest, start, mid);
		mergeSort(dest, mid + 1, end);
		merge(dest, start, mid, end);
	}

	private static void merge(int[] dest, int start, int mid, int end) {
		for (int l = start, r = mid + 1, index = start; index <= end; index++) {
			if (r > end || l <= mid && dest[l] <= dest[r]) {
				tempArray[index] = dest[l++];
			} else {
				tempArray[index] = dest[r++];
			}
		}
		System.arraycopy(tempArray, start, dest, start, end - start + 1);
	}
}
