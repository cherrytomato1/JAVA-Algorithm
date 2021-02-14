## 문제 정의

1. T 개의 테스트 케이스가 입력된다
2. 각각 테스트 케이스에서 가로 M 과 세로 N( 1 ≤ M, N ≤ 50)의 배추밭의 크기와 배추의 개수 K(1 ≤ K ≤ 2,500) 가 주어진다.
3. 다음 K 개의 열에 배추의 위치 X( 0 ≤ X ≤ M - 1)와 Y( 0 ≤ Y ≤ N - 1)가 주어진다
4. 지렁이는 배추가 위치한 좌표에 위치할 수 있다. 이 때 배추에 있는 지렁이는 배추와 상하좌우로 인접한 배추를 모두 보호할 수 있다.
5. 각 테스트 케이스 별로 배추를 보호하는 데 필요한 최소한의 지렁이 수를 출력해라

## 문제 풀이

1. 모든 배추에 대하여 상하좌우를 기준으로 하는 bfs 탐색을 시행한다.
2. 해당 배추에 인접한 배추와 또 다시 그 배추에 인접한 배추를 모두 탐색하여 그 배추들은 하나의 지렁이만 필요하다고 간주한다.
3. 탐색을 마친 배추의 좌표는 방문체크하여 중복 방문을 하지 않도록 한다.
4. 인접한 배추의 그룹의 수를 세어 출력한다.

## 코드

```java
st = new StringTokenizer(br.readLine());
M = Integer.parseInt(st.nextToken());
N = Integer.parseInt(st.nextToken());
K = Integer.parseInt(st.nextToken());

map = new boolean[N][M];

for(int i = 0 ; i < K ; i++){
    st = new StringTokenizer(br.readLine());
    int X = Integer.parseInt(st.nextToken());
    int Y = Integer.parseInt(st.nextToken());
    map[Y][X] = true;
}
```

- 각각의 테스트 케이스에 대하여 M, N, K를 입력받고 맵은 boolean으로 할당한다.
- 배추의 좌표에 true를 표시한다.

```java
int res = 0 ;
for(int r = 0 ; r < N ; r++){
    for(int c = 0 ; c < M ; c++){
        if(map[r][c]) res += bfs(r, c);
    }
}
return res;
```

- 모든 좌표를 검사하여 배추가 있는 좌표를 찾았다면 해당 좌표에서 bfs 를 시작한다. bfs 탐색 결과 값을 res에 추가한다.

```java
static int bfs(int row, int col){
	  que.offer(new Node(row, col));
	  while(!que.isEmpty()){
	      Node curr = que.poll();
	
	      for(int i = 0 ; i < 4 ; i++){
	          int nr = curr.row + DIR[i][0];
	          int nc = curr.col + DIR[i][1];
	
	          if(nr >= N || nr < 0 || nc >= M || nc < 0 || !map[nr][nc]) continue;
	
	          map[nr][nc] = false;
	          que.offer(new Node(nr, nc));
	      }
	  }
	  return 1;
}
```

- 시작 좌표를 먼저 큐에 넣고 bfs탐색을 시작하며 큐가 빌 때까지(인접 4방에 배추가 없을 때까지) 큐에 넣으며 탐색한다.
- 4방 탐색 중 다음에 탐색할 좌표가 범위를 벗어나거나, 배추가 없는좌표(false)라면 다음 방향을 탐색한다.
- 탐색할 좌표가 배추라면, 그 좌표를 검사했으므로 다시 검사하지 않도록 배추가 없다고 표시한 후 다음 탐색할 큐에 넣는다.
- 탐색을 마쳤다면 그 배추 그룹은 지렁이가 1마리만 있어도 되므로 1을 리턴한다.