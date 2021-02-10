package swea;

/**
 * 
 * 계산기 -> 스택
 * 1. 입력문자열을 하나하나 검사해 피연산자면 바로 계산 스택에 푸시 
 * 2. 연산자일 경우 현재 변환 스택에 있는 연산자의 우선순위가 같다면 팝시키고 계산 스택에 푸시, 이후 해당 연산자를
 * 변환 스택에 푸시, 낮을 경우엔 변환 스택에 바로 푸시
 * 3. 계산스택에 연산자가 들어왔다면 앞선 두개의 피연사자로 연산 후 다시 계산 스택에 푸시
 * 4. 모든 연산이 끝나면 남은 하나의 피연산자 출력
 */

import java.io.*;
import java.util.*;


public class Solution_D4_1223_계산기2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Deque<Character> conStk;
	static Deque<Integer> calStk;
	static StringBuilder sb = new StringBuilder();
	
	private static void calc(char c) {
		if(c >= '0') {
			calStk.push(c - '0');
			return;
		}
		int num2 = calStk.pop();
		int num1 = calStk.pop();
		
		switch(c) {
		case '+' : 
			calStk.push(num1 + num2);
			break;
		case '-' :
			calStk.push(num1 - num2);
			break;
		case '*' : 
			calStk.push(num1 * num2);
			break;
		case '/' :
			calStk.push(num1 / num2);
			break;
			
		}
	}
	
	private static void conv(char c) {
		switch(c) {
		case '*' : case '/' :
			while(!conStk.isEmpty() && (conStk.peek() == '*' || conStk.peek() == '/')) {
				calc(conStk.pop());
			}
			conStk.push(c);
			break;
			
		case '+' : case '-' :
			while(!conStk.isEmpty()) {
				calc(conStk.pop());
			}
			conStk.push(c);
			break;
		default :
			calc(c);
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		for(int tc = 1 ; tc <= 10 ; tc ++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			conStk = new ArrayDeque<>();
			calStk = new ArrayDeque<>();
			
			for(int i = 0 ; i < N; i ++) {
				conv(str.charAt(i));
			}
			while(!conStk.isEmpty()) calc(conStk.pop());
			
			sb.append(calStk.pop()).append("\n");
		}
		System.out.println(sb);
	}
}
