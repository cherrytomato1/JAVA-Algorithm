package myProblem;

import java.util.*;
import java.io.*;

public class Elevator {
	//탑승객 클래스
	private static class People implements Comparable<People>{
		//층수, 몸무게
		int level;
		int weight;

		public People(int level, int weight) {
			this.level = level;
			this.weight = weight;
		}

		//정렬을 위한 compareTo 오버라이드
		//층수 -> 몸무게
		@Override
		public int compareTo(People o) {
			int val = Integer.compare(o.level, this.level);
			return val == 0 ? Integer.compare(o.weight, this.weight) : val;
		}
	}

	private static final InputStream in = System.in;
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(in));

	private static int N;
	private static int M;
	private static int weightLimit;
	private static int peopleCount;
	private static List<People> peopleList;

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T ; tc++) {
			init();
			//아무도 안태운 상태에서 재귀 시작
			recur(0,0,0,0,0, false);
			sb.append("#").append(tc).append(" ").append(minCost).append("\n");
		}
		System.out.println(sb);
	}

	private static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		weightLimit = Integer.parseInt(st.nextToken());

		peopleList = new ArrayList<>();

		//peopleList에 입력받은 탑승객 정보 추가
		for (int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int level = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());

			for (int j = 0; j < len ; j++) {
				peopleList.add(new People(level, Integer.parseInt(st.nextToken())));
			}
		}
		Collections.sort(peopleList);
		//남아있는 사람수 초기화
		peopleCount = peopleList.size();
		//최소 비용 초기화
		minCost = Integer.MAX_VALUE;
	}

	//최소 비용
	private static int minCost;
	/*
		태울 승객에 대한 부분집합을 구하는 재귀
		@params idx      : 현재 선택할지 고려할 리스트 요소의 인덱스
		@params flag      : 현재까지 운반한 승객에 대한 비트 플래그
		@params weight   : 현재 운반중인 승겍들의 무게합
		@params maxLevel : 현재 운반중인 승객의 최대 층수
		@params cost     : 선택한 부분집합으로 운반했을 때 도합 비용
		@params selected : 재귀 레벨에서 한명이라도 선택한 손님이 있는지 없는지 판단
	 */
	private static void recur(int idx, int flag, int weight, int maxLevel, int cost, boolean selected) {
		//최소 비용보다 더 높은 비용이 필요하거나, 엘리베이터의 최대 무게를 넘는 경우 백트래킹
		if (cost >= minCost || weight > weightLimit) return;
		//모든 승객 운반을 마친 경우
		if (flag == (1 << peopleCount) - 1) {
			//맨 마지막에 운반한 손님들의 비용(최대 층 수의 손님)을 더함
			cost += (maxLevel - 1) * 2;
			//최소 비용 갱신
			minCost = Math.min(cost, minCost);
			return;
		}

		//한 번의 운반에서 부분집합 선택을 완료한 경우
		if (idx == peopleCount) {
			//해당 운반에서 한명도 선택하지 않은 경우는 제외
			if(!selected)   return;
			//해당 운반에서 최대 층 수의 손님 비용 더하기
			cost += (maxLevel - 1) * 2;
			//다시 첫 손님부터 부분집합 선택
			recur(0, flag, 0, 0, cost,false);
			return;
		}

		//목표 손님 설정
		People target = peopleList.get(idx);
		//목표 손님을 태우지 않는 경우의 재귀
		recur(idx + 1, flag, weight, maxLevel, cost, selected);
		//목표 손님을 태울 경우의 재귀, 이미 탑승한 손님이라면 제외
		if((flag & 1 << idx) != 0)     return;
		recur(idx + 1, flag | 1 << idx, weight + target.weight, Math.max(maxLevel, target.level), cost, true);
	}
}

/*

엘리베이터 무게에 맞게 채우는 것이 해일 경우
3 2 100 100
2 3 40 40 40
3 2 60 10

높은층부터 ->  4 + 2
몸무게 우선 -> 4 + 4

정렬 ?
->

가장 높은 층부터 내리는 게 해일 경우
4 2 100 100
2 2 40 80
4 2 60 20

6 + 2
6 + 6



=>

=> 난해문제



50       최고층부터
25 35 10 => 50미만이면서 가장 많은 무게를 담는 경우를 담아야함 (부분집합) 단순정렬로는 x => 배낭

층수 5개 사람수 5개 정도 ?

(최대한 많은 무게) => 그러면서도 사람도 많아야함
사람이 남아있는 최고층부터 인원을 최대한 많이 태워서 내려온다 (최대한 많이 -> 부분집합)


1
5 3 100
4 5 10 100 50 30 1
5 5 1 30 50 100 10
2 5 50 50 30 5 5
3 5 10 50 30 5 5


1
2 1 2 100
2 3 50 50 30

1
3 2 3 100
2 3 50 50 30
3 3 80 20 10

5
2 1 2 100
2 3 50 50 30
2 2 3 100
2 3 50 50 30
3 3 80 20 10

2 2 3 100
3 3 40 45 50
2 2 10 55

2 3 3 100
4 3 50 50 30
3 3 40 45 50
2 2 10 55
2 3 3 100
4 3 50 50 30
3 3 80 20 10
2 2 10 55


#1 4

#2 10

#3 8

#4 18

#5 18
 */
