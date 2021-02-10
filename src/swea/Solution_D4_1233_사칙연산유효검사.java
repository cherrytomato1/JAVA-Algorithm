package swea;

import java.util.*;
import java.io.*;

public class Solution_D4_1233_사칙연산유효검사 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	
	public static void main(String[] args) throws IOException{
		for(int tc = 1 ; tc <= 10 ; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			
			boolean flag = true;
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				
				if(!flag)	continue;
				st.nextToken();
				
				char c = st.nextToken().charAt(0);

				if(c >= '0') flag = st.countTokens() == 0 ? flag : false;
				else 		 flag = st.countTokens() == 2 ? flag : false;
			}
			sb.append(flag ? 1 : 0).append("\n");
		}
		System.out.println(sb);
	}
}
