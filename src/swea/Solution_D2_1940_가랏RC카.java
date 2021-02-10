package swea;

import java.util.*;
import java.io.*;

public class Solution_D2_1940_가랏RC카 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			sb.append("#").append(tc).append(" ");
			
			int speed = 0;
			int distance = 0;
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				int order = Integer.parseInt(st.nextToken());
				switch(order){
				case 0 : break;
				case 1 :
					speed += Integer.parseInt(st.nextToken());
					break;
				case 2 :
					speed = Math.max(speed - Integer.parseInt(st.nextToken()), 0);
					break;
				
				}
				distance += speed;
			}
			sb.append(distance).append("\n");
		}
		System.out.println(sb);
	}
}
