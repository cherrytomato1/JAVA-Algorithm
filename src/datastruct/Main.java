package datastruct;

import java.util.ArrayDeque;
import java.util.Stack;

public class Main {

	static final int EOK = 100000000;
	static final int EOK10 = 1000000000;

	public static void main(String[] args) {
//		callStack();
		linkedListTest();

	}

	private static void linkedListTest() {
		MkLinkedList<Integer> list = new MkLinkedList<>();
		//머리에 추가하기
		System.out.println("size : " + list.size());
		for (int i = 0; i < 10; i++) {
			list.addFirst(i);
			System.out.println("add first : " + i + " size = " + list.size());
		}
		//모두 출력하기
		getAllListItems(list);

		//모두 비우기
		list.clear();
		System.out.println("size : " + list.size());

		//꼬리에 추가하기
		for (int i = 0; i < 10; i++) {
			list.addLast(i);
			System.out.println("add last : " + i + " size = " + list.size());
		}
		getAllListItems(list);

		//중간에 추가하기
		for (int i = 9; i > 0  ; i--) {
			list.add(i, i * 10);
			System.out.println("add idx : " + i + " size = " + list.size());
		}
		getAllListItems(list);

		//머리 지우기
		for (int i = 0; i < 5 ; i++) {
			list.removeFirst();
		}
		getAllListItems(list);

		//꼬리 지우기
		for (int i = 0; i < 5 ; i++) {
			list.removeLast();
		}
		getAllListItems(list);

		//허리 지우기
		for (int i = 1; i < 7 ; i+=2) {
			list.remove(i);
		}
		getAllListItems(list);
	}

	private static void getAllListItems(MkLinkedList<Integer> list) {
		System.out.print("리스트 순회 :");
		for (int i = 0; i < list.size() ; i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}


	static void callStack(){
		MkStack<Integer> stk = new MkStack<>(10);
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
