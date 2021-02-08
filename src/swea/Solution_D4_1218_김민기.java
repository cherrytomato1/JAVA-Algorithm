package swea;

import java.util.*;
import java.io.*;

public class Solution_D4_1218_김민기 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String str;
	static int N;
	static StringBuilder sb = new StringBuilder();
	static Stack<Character> stk = new Stack<>();
	static char[] ch= new char[]{ '[', '<', '{', '(' }; 
	
	public static void main(String[] args) throws IOException {
		for(int tc = 1 ; tc <= 10 ; tc++) {
			N = Integer.parseInt(br.readLine());
			str = br.readLine();
			
			sb.append("#" + tc + " ");
			
			boolean flag = true;
			Loop :
			for(char c : str.toCharArray()) {
				int idx = 0;
				switch(c) {
				case ')' : idx++;
				case '}' : idx++;
				case '>' : idx++;
				case ']' : 
					if(stk.isEmpty() || stk.pop() != ch[idx]) { 
						flag = false;
						break Loop;
					} else break;
				default :
					stk.push(c);
				}
			}
			
			stk.clear();
			sb.append(stk.isEmpty() && flag ? 1 : 0).append("\n");
		}
		System.out.println(sb);
	}
}
