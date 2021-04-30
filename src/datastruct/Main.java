package datastruct;

import java.util.ArrayDeque;
import java.util.Stack;

public class Main {

	static final int EOK = 100000000;
	static final int EOK10 = 1000000000;

	public static void main(String[] args) {
//		callStack();
		BinarySearchTest();
	}

	static void BinarySearchTest(){
		int[] arr = new int[EOK10/2];

		for(int i = 0; i < EOK10/2; i++){
			arr[i] = i;
		}

		int val = EOK * 4;
		long start = System.currentTimeMillis();
		for(int i = 0; i < EOK10/2; i++){
			if (arr[i] == val) {
				System.out.println(i);
				break;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		start = System.currentTimeMillis();
//		System.out.println(BinarySearch.binarySearch(arr, val));
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	static void callStack(){
		MkStack stk = new MkStack(10);
		Stack<Integer> stk2 = new Stack<>();
		ArrayDeque<Integer> stk3 = new ArrayDeque<>();

		long start = System.currentTimeMillis();
		for(int i = 0; i < Integer.MAX_VALUE; i++){
			stk2.push(i);
			stk2.peek();
			stk2.pop();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		start = System.currentTimeMillis();
		for(int i = 0; i < Integer.MAX_VALUE ; i++){
			stk3.push(i);
			stk3.peek();
			stk3.pop();
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);

		start = System.currentTimeMillis();
		for(int i = 0; i < Integer.MAX_VALUE ; i++){
			stk.push(i);
			stk.peek();
			stk.pop();
		}
		end = System.currentTimeMillis();

		System.out.println(end - start);

	}
}
