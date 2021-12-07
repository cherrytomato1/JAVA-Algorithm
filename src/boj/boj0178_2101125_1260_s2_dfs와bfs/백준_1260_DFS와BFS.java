package boj.boj0178_2101125_1260_s2_dfs와bfs;

import java.util.*;
import java.io.*;

/**
 * 간선 정보 저장하는 방법
 * 1. 인접배열
 * - 2중배열을 만들어, 각 배열의 인덱스가 정점을 뜻함.
 * - ex) array[1][2] = true -> 여기 간선이 있다 ? 이거는 단방향이니까
 * 양방향할려면은 array[2][1] = true;
 * 이럴경우는 1에서 2갈수있고 2에서 1갈수있다.
 * <p>
 * 확장 ) 간선에 비용이 있는경우가 있지 1 -> 2 가 3의 비용 (다익스트라, 플로이드 와샬 -> 최단경로)
 * 이경우는 boolean 배열이 아닌 int 배열로 비용을 저장하면됨 array[1][2] = 3;
 * <p>
 * 이럴경우 배열의 크기는 모든 정점을 담ㅇ아야함 -> N + 1 의 크기 보통 0은 안쓰니까
 * <p>
 * 2. 진짜 정점과 간선만들기(리스트)
 * - 각 정점에 대해서 간선리스트를 만드는거야
 * - ex) 1 -> 2 ,1 -> 3, 1 - 4
 * - ArrayList<ArrayList<Integer>> ( List<List>[] )
 * <p>
 * - 저 상위 ArrayList의 인덱스가 정점을 의미하고, 하위 에 있는 리스트의 값들이 연결가능한 정점.
 * - 그런걸 고민할 필요가없어 1 <-> 2
 * List.get(1).add(2)
 * List.get(2).add(1)
 * <p>
 * 이럴경우 배열의 크기는 모든 정점을 담ㅇ아야함 -> N + 1 의 크기 보통 0은 안쓰니까
 */


public class 백준_1260_DFS와BFS {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static List<List<Integer>> list = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		//시작
		int V = Integer.parseInt(st.nextToken());

		int from;
		int to;

		for (int i = 0; i < N + 1; i++) {
			list.add(new ArrayList<>());
		}


		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());

			list.get(from).add(to);
			list.get(to).add(from);
		}

		for (int i = 0; i < N + 1; i++) {
			Collections.sort(list.get(i));
		}
		sb = new StringBuilder();
		visited = new boolean[N + 1];
		dfs(V);
		sb = sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);

		sb = new StringBuilder();
		visited = new boolean[N + 1];
		bfs(V);
		sb = sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);

	}

	private static StringBuilder sb;
	private static boolean[] visited;
	//뭐가들어가야하나 얘가 해야할 것
	//탐색의 기본은 갔던 곳 다시 안감 -> 아예 호출안하기, 못가는 곳이면 리턴하기(안연결, 방문했음)


	private static void dfs(int index) {
		if (visited[index]) {
			return;
		}
		//위에걸 지낫으면 탐색중인거임 해당정점(index)를
		sb.append(index).append(" ");
		visited[index] = true;
		//str = str + index + " "

		//다른 간선 찾기(얘랑 연결된)

		List<Integer> vertexes = list.get(index);

		for (int i = 0; i < vertexes.size(); i++) {
			int nextVertex = vertexes.get(i);
			dfs(nextVertex);
		}
	}

	private static void bfs(int startIndex) {
		Queue<Integer> q = new LinkedList<>();

		visited[startIndex] = true;
		q.offer(startIndex);

		while (!q.isEmpty()) {
			int index = q.poll();
			sb.append(index).append(" ");

			List<Integer> vertexes = list.get(index);

			for (int i = 0; i < vertexes.size() ; i++) {

				int nextVertex = vertexes.get(i);

				if (!visited[nextVertex]) {

					visited[nextVertex] = true;

					q.offer(nextVertex);
				}

			}
		}
	}
}
