package swea.solution_D3_6806;

import java.util.*;
import java.io.*;

public class Solution_D3_6806_규영이와인영이의카드게임 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static final int CARD_MAX = 18;
	static final int CARD_MID = CARD_MAX/2;
	
	static int[] cards = new int[CARD_MAX];
	static int[] kyu0 = new int[CARD_MID];
	static int[] in0 = new int[CARD_MID];
	
	static int win = 0;
	static int lose = 0;
	
	static void input() throws IOException {
		win = 0;
		lose = 0;
		
		for(int i = 0 ; i < CARD_MAX ; i++) {
			cards[i] = i + 1;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < CARD_MID ; i++) {
			int card = Integer.parseInt(st.nextToken());
			kyu0[i] = card;
			cards[card - 1] = -1;
		}
		
		for(int i = 0, j = 0; i < CARD_MID; j++) {
			if(cards[j] != -1) 	in0[i++] = cards[j];
		}		
	}
	
	static void f(int idx, int kyuScr, int inScr, int flag) {
		if(idx == CARD_MID) {
			if(kyuScr > inScr) 		win++;
			else if(kyuScr < inScr) lose++;
			return ;
		}
		
		for(int i = 0 ; i < CARD_MID ; i++) {
			if((flag & 1 << i) != 0) continue;
			
			int sum = kyu0[idx] + in0[i];
			if(kyu0[idx] > in0[i]) 		f(idx + 1, kyuScr + sum, inScr, flag | 1 << i);
			else if(kyu0[idx] < in0[i]) f(idx + 1, kyuScr, inScr + sum, flag | 1 << i);
		}
	}
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++) {
			sb.append("#").append(tc).append(" ");
			input();
			f(0, 0, 0, 0);
			sb.append(win).append(" ").append(lose).append("\n");
		}
		System.out.println(sb);
	}
}
