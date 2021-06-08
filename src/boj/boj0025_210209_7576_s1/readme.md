## 문제 정의

1. col M, row N (2 ≤ M,N ≤ 1,000) 의 상자 크기가 첫 열에 입력된다
2. 다음 열부터 N 까지 익은 토마토 1 , 안익은 토마토 0, 비어있는 곳은 -1 로 입력된다
3. 익은 토마토는 한 번에 4방향에 있는 안익은 토마토를 익힌다. 모든 토마토가 익을 때까지 가장 적게 걸리는 최소 시간을 출력하라.

## 문제 풀이

1. 익은 토마토들의 row와 col 을 초기 큐에 넣는다.
2. 큐를 팝하면서 -1과 배열 밖을 예외처리 하며 4방 탐색을 한다.
3. 4방 탐색하면서 안익은 토마토를 익히고 큐에 넣는다.
4. 큐를 팝하면서 그 row,col 부터 익혀야하는 토마토를 다시 큐에 넣는다. 큐가 빌 때 한번의 사이클이 끝났다고 생각하여 날짜를 +1 한다.
5. 이 때 한번의 사이클에 다른 하위 탐색이 실행되지 않도록 해당 사이클의 큐 사이즈를 받아서 그 사이즈를 매개로 반복을 해야함
6. 큐가 완전히 비었을 때, 다시 맵을 탐색하여 안익은 토마토가 있다면 -1 출력, 아니면 날짜를 출력한다.
7. 이 때 마지막으로 익힌 토마토에서도 주변 탐색을 하기 때문에 날짜가 하나 많으므로 -1 해준다.

## 코드

```java
for(int i = 0 ; i < N ; i++){
    st = new StringTokenizer(br.readLine());
    for(int j = 0 ; j < M ; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] == 1) q.offer(new int[]{i, j});
    }
}
```

1. 상자를 입력받고 토마토일 경우에 큐에 넣어줌

```java
while(!q.isEmpty()){
	  for(int size = q.size(); size != 0; size--){
	      pos = q.poll();
//...

	res++;
}
```

2. 큐가 빌 때까지 반복하되, 하루하루를 따로 측정하기 위하여 하루에 추가된 큐만 검사함.

```java
pos = q.poll();
    for(int i = 0 ; i < 4; i++) {
        int nr = pos[0] + dr[i];
        int nc = pos[1] + dc[i];
        if (nr >= N || nr < 0 || nc >= M || nc < 0 || map[nr][nc] != 0) continue;

        map[nr][nc] = 1;
        q.offer(new int[]{nr, nc});
    }
```

3. 매 사이클에서 큐를 뽑아내어 4방 탐색 한 후, 주변 토마토를 전염시킨 뒤 전염된 토마토를 큐에 넣는다.

```java
for(int i = 0 ; i < N ; i++){
    for(int j = 0 ; j < M ; j++){
        if(map[i][j] == 0) return -1;
    }
}
//마지막으로 익힌 토마토에서도 주변 탐색을 하기 때문에 날짜가 하나 많으므로 -1 해준다.
return res - 1;
```

4. 큐가 완전히 비었을 때, 남은 토마토가 있는지 검사해서 있으면 -1 없으면 날짜를 리턴한다. 이 때 마지막 토마토에서도 주변에 전염시킬 토마토가 있는 지 탐색을 했기에 res가 +1 되어 있으므로 빼준다.

### 전체 코드

```java
package boj.boj0025_210209_7576_s1;

import java.io.*;
import java.util.*;

public class myProblem.Elevator {

	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int M;
	static int[][] map;
	static Queue<int[]> q = new ArrayDeque<>();

	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};

	static int bfs() {
		int[] pos;

		int res = 0;
		while (!q.isEmpty()) {
			for (int size = q.size(); size != 0; size--) {
				pos = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = pos[0] + dr[i];
					int nc = pos[1] + dc[i];
					if (nr >= N || nr < 0 || nc >= M || nc < 0 || map[nr][nc] != 0) continue;

					map[nr][nc] = 1;
					q.offer(new int[]{nr, nc});
				}
			}
			res++;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) return -1;
			}
		}
		//마지막으로 익힌 토마토에서도 주변 탐색을 하기 때문에 날짜가 하나 많으므로 -1 해준다.
		return res - 1;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) q.offer(new int[]{i, j});
			}
		}
		System.out.println(bfs());
	}
}
```

## 더 좋은 풀이

- 초기에 맵을 받으면서 토마토의 개수를 받아주면, 마지막에 남은 토마토가 있는지 없는 지 검사할 필요가 없다.
- 큐를 이용한 반복에서도 큐가 비지 않았을 때 뿐 아니라 토마토가 남아있을 때만 반복하면 되므로 한번 더 탐색할 필요가 없다.