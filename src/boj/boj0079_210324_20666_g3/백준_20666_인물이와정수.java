package boj.boj0079_210324_20666_g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_20666_인물이와정수 {
	private static class Monster implements Comparable<Monster>{
		boolean isOffered;
		int idx;
		int level;
		List<Edge> edgeList;
		public Monster(int idx, int level){
			this.idx = idx;
			this.level = level;
			edgeList = new ArrayList<>();
		}
		@Override
		public int compareTo(Monster o){
			return Integer.compare(this.level, o.level);
		}
		@Override
		public String toString(){
			return idx + " " + level + " ";
		}
	}
	private static class Edge{
		int to;
		int weight;
		public Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static Queue<Monster> pq;
	private static int N;
	private static int P;
	private static int M;
	private static Monster[] monsters;
	private static Monster[] sortedMonsters;

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		monsters = new Monster[N + 1];
		monsters[0] = new Monster(Integer.MAX_VALUE, Integer.MAX_VALUE);
		pq = new PriorityQueue<>(Collections.reverseOrder());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1 ; i <= N; i++){
			monsters[i] = new Monster(i, Integer.parseInt(st.nextToken()));
		}
		P = Integer.parseInt(br.readLine());

		for(int i = 0 ; i < P ; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			monsters[to].level += weight;
//			System.out.println(to + " " + monsters[to].level);
			monsters[from].edgeList.add(new Edge(to, weight));
		}
	}

	private static int solve() {
		sortedMonsters = Arrays.copyOf(monsters, monsters.length);
		Arrays.sort(sortedMonsters);
//		System.out.println(Arrays.toString(monsters));
//		System.out.println(Arrays.toString(sortedMonsters));
		int idx = 0;

//		for(int i = 0 ; i < N ; i++){
//			System.out.println(i + " " +sortedMonsters[i].edgeList.size());
//		}
		for(; idx < M; idx++){
			pq.add(sortedMonsters[idx]);
			sortedMonsters[idx].isOffered = true;
//			System.out.println(sortedMonsters[idx]);
			checkHeap(sortedMonsters[idx].idx);

		}

//		while(pq.size() != M){
//			pq.poll();
//		}
		for(; idx < N; idx++){
			if(sortedMonsters[idx].isOffered || pq.peek().level < sortedMonsters[idx].level )    continue;
			pq.poll();
			pq.offer(sortedMonsters[idx]);
			sortedMonsters[idx].isOffered = true;
			checkHeap(sortedMonsters[idx].idx);
		}

		return pq.peek().level;
	}

	private static void checkHeap(int idx){
//		if(idx == -1) return;
		for(Edge edge : monsters[idx].edgeList){
			int to = edge.to;
			if(edge.weight == 0)                        continue;
//			if(monsters[to].level - edge.weight == 4) System.out.println(to + "  idx");
//			System.out.println(monsters[idx].level + " " + idx);
			int tempLv =  Math.max( monsters[to].level - edge.weight, monsters[idx].level);
//			System.out.println(tempLv);
			monsters[to].level = Math.min(monsters[to].level, tempLv);

//			System.out.println(pq.peek());
//			if(monsters[to].level > pq.peek().level)  continue;
			if(!monsters[to].isOffered && monsters[to].level <= pq.peek().level) {
//				System.out.println(to);
				pq.poll();
				pq.offer(monsters[to]);
				monsters[to].isOffered = true;
			}
//			System.out.println("offered = "  + monsters[to].level);
//			System.out.println(monsters[to].level);
//			System.out.println(pq.add(monsters[to].level) + " " + monsters[to].level);
//			if(monsters[to].level == 0) System.out.println(" 0 offered");
//			pq.offer(monsters[0]);
//			pq.poll();
			edge.weight = 0;
			checkHeap(to);

//			edge.to = -1;
		}
//		sortedMonsters[idx].edgeList.clear();
	}

	public static void main(String[] args) throws IOException{
		init();
//		System.out.println(Arrays.toString(monsters));
		System.out.println(solve());
//		System.out.println(Arrays.toString(monsters));
//		while(!pq.isEmpty()) {
//			Monster curr = pq.poll();
//			System.out.println(curr.idx + " " + curr.level);
//		}
	}
}

/*
7 7
2 1 5 6 3 6 1
4
3 4 3
4 3 2
4 2 3
1 6 2


5 2
2 1 5 6 3
3
3 4 3
4 3 2
4 2 3


5 4
2 1 5 6 3
3
3 4 3
4 3 2
4 2 3

5

5 5
2 1 5 6 3
3
3 4 3
4 3 2
4 2 3

7



 */
