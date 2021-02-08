package jungol;

import java.io.*;
import java.util.*;

public class Main_1141_김민기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static Stack<Integer> stk = new Stack<>();

	
	static long ans;
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < N ; i++) {
			int cow = Integer.parseInt(br.readLine());
			while(!stk.isEmpty() && stk.peek() <= cow) {
				stk.pop();
			}
			ans += stk.size();
			stk.push(cow);
		}
		
		System.out.println(ans);
	}
}

/**
 * 소의 번호를 인덱스로 하는 배열에 그 소를 볼 수 있는 소를카운
*/
