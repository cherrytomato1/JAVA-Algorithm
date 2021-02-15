## 문제 정의

1. 규영이와 인영이는 1 ~ 18 까지 적힌 18장의 카드를 섞어 9장씩 나눠 가진다.
2. 각 라운드에서 한 장씩 카드를 내어 높은 카드를 낸 사람이 두 장의 값 합계를 점수로 얻는다.
3. 9라운드가 끝나면 점수가 더 높은 사람이 승리하고, 같으면 무승부다
4. 규영이가 입력으로 받는 카드 순서에 따라 카드를 낼 때, 규영이가 승리하거나 패배하는 경우의 수의 가짓수를 모두 출력

## 문제 풀이

1. 각 테스트 케이스 별로 규영이가 받는 모든 카드를 규영이 배열에 저장하고 나머지 입력되지 않는 카드를 모두 인영이 배열에 저장한다.
2. 규영이가 내는 순서는 고정되어 있으니 인영이가 낼 수 있는 카드의 조합 수를 모두 뽑는다. 뽑음과 동시에 규영이의 카드와 크기를 비교하여 점수를 다음 재귀로 넘겨준다.
3. 카드의 순열이 하나 완성될 때마다 규영이와 인영이의 점수를 비교하여 win 혹은 lose를 세준다.

## 코드

```java
package com.ssafy.swea;

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
```