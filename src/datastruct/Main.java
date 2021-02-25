package datastruct;

import java.util.ArrayDeque;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		callStack();
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
