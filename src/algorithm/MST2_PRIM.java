package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MST2_PRIM {
	private final int N;

	private final int[][] arr;
	private final boolean[] visited;
	private final int[] minEdge;
	private final BufferedReader br;

	public MST2_PRIM() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		this.N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N];
		minEdge = new int[N];

		getIn();
	}
	private void getIn() throws IOException{
		for(int r = 0 ; r < N ; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
			minEdge[r] = Integer.MAX_VALUE;
		}
	}

	public int prim(){
		int res = 0;
		minEdge[0] = 0;
		for(int c = 0 ; c < N; c++){
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			//신장트리에 연결되지 않은 정점 중 minEdge비용이 최소인 정점
			for(int i = 0 ; i < N; i++){
				if(visited[i] || min <= minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			res += min;
			visited[minVertex] = true;

			for(int i = 0; i < N; i++){
				if(!visited[i] && arr[minVertex][i] != 0 && minEdge[i] > arr[minVertex][i]){
					minEdge[i] = arr[minVertex][i];
				}
			}
		}
		return res;
	}
}
