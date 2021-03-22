package algorithm;

import java.io.*;
import java.util.*;

public class Dijkstra {
	int N;
	int[][] arr;
	boolean[] visited;
	int[] dist;
	int start;
	int end;

	public Dijkstra() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		//1. 초기화
		//1.1 정점 갯수 받기
		N = Integer.parseInt(br.readLine().trim());

		//1.2 방문배열 선언
		visited = new boolean[N];

		//1.3 최소비용배열 선언
		dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);

		//1.4 시작 정점 및 종료 정점 설정
		start = 0;
		end = N - 1;
		dist[start] = 0 ;

		//1.5 인접배열 초기화
		arr = new int[N][N];
		for(int r = 0 ; r < N ; r++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int c = 0 ; c < N ; c++){
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}

	public int dijkstraTest(){
		//2. 각 정점의 수만큼 반복해서 최소 비용 찾기
		int min = 0;
		int curr = 0;

		for(int i = 0 ; i < N ; i++){
			//2.1 방문하지 않은 정점 중에서 최소 비용의 정점을 선택
			min = Integer.MAX_VALUE;
			for(int j = 0; j < N ; j++){
				if(visited[j] || dist[j] >= min)        continue;
				min = dist[j];
				curr = j;
			}

			//2.2 최소 비용으로 선택된 정점을 방문하기
			visited[curr] = true;

			//(선택) 도착 지점에 도착했을 때 종료
			if(curr == end) break;

			//2.3 기존 최소 비용과 경유하여 만들 수 있는 최소 비용을 비교하여 최소 비용 갱신
			for(int j = 0 ; j < N; j++){
				//arr[curr][j] == 0  : 인접하지 않은 정점
				//dist[j] : 기존 비용
				//min + arr[curr][j] 경유하여 갈 수 있는 비용 (min == dist[curr])
				//기존의 최소비용(dist[j])과 현재 위치에서 j 로 갈 수 있는 동선 비교해서 최솟값 갱신
				if(visited[j] || arr[curr][j] == 0 || dist[j] <= min + arr[curr][j])    continue;
				dist[j] = min + arr[curr][j];
			}
		}
		// dist[end]가 초기값(Integer.MAX_VALUE) 라면 도달할 수 있는 경로가 없음
		return dist[end];
	}
}
