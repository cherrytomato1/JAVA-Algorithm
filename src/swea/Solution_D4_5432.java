package swea;
import java.io.*;
import java.util.*;

public class Solution_D4_5432 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static Stack<Character> stk = new Stack<>();
	static String str = null;
	
	/** 스택으로 풀기 */
	private static void stackSolve(int T) throws IOException {
		for(int tc = 1; tc <= T ; tc++) {
			sb.append("#").append(tc).append(" ");
			str = br.readLine();
			
			int cnt = 0;
			for(int i = 0 ; i < str.length(); i++) {
				if(str.charAt(i) == '(') { 
					stk.push(str.charAt(i));
					continue;
				}
				stk.pop();
				cnt += str.charAt(i - 1) == '(' ?  stk.size() : 1;
			}
			sb.append(cnt).append("\n");
		}
	}
	
	/** 재귀로 풀기 */
	private static int f(int idx, int cnt) {
		if(idx == str.length()) return 0;
		
		if(str.charAt(idx) == '(') { 
			return f(idx + 1, cnt + 1);
		}
		return (str.charAt(idx - 1) == '(' ?  cnt - 1 : 1) + f(idx + 1, cnt - 1);	
	}
	
	private static void solve(int T) throws IOException{
		for(int tc = 1; tc <= T ; tc++) {
			sb.append("#").append(tc).append(" ");
			str = br.readLine();
			
			sb.append(f(0, 0)).append("\n");
		}
	}
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
//		stackSolve(T);
		solve(T);
		System.out.println(sb);
	}
}
