package boj.boj0079_210324_20666_g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_20666_인물이와정수_2 {
	private static class Monster implements Comparable<Monster>{
		int idx;
		long level;
		boolean isDead;
		List<Edge> edgeList;
		public Monster(int idx, long level){
			this.idx = idx;
			this.level = level;
			edgeList = new ArrayList<>();
		}
		@Override
		public int compareTo(Monster o){
			return Long.compare(this.level, o.level);
		}
		@Override
		public String toString(){
			return idx + " " + level + " " + isDead;
		}
	}
	private static class Edge{
		int to;
		long weight;
		public Edge(int to, long weight){
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

	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		monsters = new Monster[N + 1];
//		monsters[0] = new Monster(Integer.MAX_VALUE, Integer.MAX_VALUE);
		pq = new PriorityQueue<>();
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
			monsters[from].edgeList.add(new Edge(to, weight));
		}

		for(int i = 1 ; i <= N ; i++){
			pq.offer(monsters[i]);
		}
//		pq.offer(new Monster(Integer.MAX_VALUE, Integer.MAX_VALUE));
	}

	private static long solve(){
		int killCnt = 0;
		long ret = Long.MIN_VALUE;
		while (killCnt < M){
			Monster curr = pq.poll();
//			System.out.println(curr + " " + pq.size());
			if(curr.isDead)     continue;
			killMonster(curr);
			killCnt++;
//			System.out.print(curr.idx + " " + curr.level + ", ");
			ret = Math.max(ret, curr.level);
		}
//		System.out.println();
		return ret;
	}

	private static void killMonster(Monster monster){
		monster.isDead = true;
		for(Edge edge : monster.edgeList){
			int to = edge.to;
			if(monsters[to].isDead) continue;
			long weight = edge.weight;

			monsters[to].level -= weight;
//			System.out.println(to);
			pq.offer(pq.poll());
			pq.offer(monsters[to]);
		}
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(Arrays.toString(monsters));
		System.out.println(solve());
		System.out.println(Arrays.toString(monsters));
	}
}

/*
6 3
51 1 50 1000000000 1000000000 1000000000 1000000000
5
1 3 1000000000
1 6 1
1 4 50
3 5 48
1 6 48

6 6
2000000000 2000000000 2000000000 2000000000 2000000000 2000000000
10
1 2 1000000000
1 3 1000000000
1 4 1000000000
1 5 1000000000
1 6 1000000000
2 4 1000000000
2 3 1000000000
4 3 1000000000
5 3 1000000000
6 3 1000000000

3 3
2000000001 2000000002 2000000003
6
1 2 2000000000
1 3 2000000000
2 1 2000000000
2 3 2000000000
3 1 2000000000
3 2 2000000000

6000000001

3 2
5 3 106
4
2 1 100
2 1 2
2 1 2
1 2 200

109

3 2
5 3 6
3
2 1 3
2 1 1
1 2 2

5

3 2
5 3 6
2
2 1 3
1 2 2

5

7 6
2 1 5 6 3 6 8
4
3 4 3
4 3 2
4 2 3
1 6 2

7

7 5
15 5 5 1 3 9 8
5
3 4 3
4 3 2
4 2 3
6 5 7
7 4 5


5 2
2 1 5 6 3
3
3 4 3
4 3 2
4 2 3

3


5 4
2 1 5 6 3
3
3 4 3
4 3 2
4 2 3

7

9 8
2 1 5 6 5 15 12 15 16
9
3 5 123213
9 8 20
3 5 3
3 5 6
5 4 22222
4 3 22222
4 2 3
8 6 1222
6 9 15


6

5 5
2 1 5 6 3
3
3 4 3
4 3 2
4 2 3

7



 */
