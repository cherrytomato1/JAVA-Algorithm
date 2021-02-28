## 문제 정의

1. 육지가 4방으로 이어져 있다면, 이 육지들은 같은 섬입니다.
2. 섬 끼리 이을 수 있는 단 하나의 다리를 짓는다고 했을 때 가장 짧은 거리의 다리의 길이를 출력합니다.

## 문제 풀이

1. 먼저 각각의 육지들을 섬 단위로 구분해야합니다.
    1. 모든 육지에서 탐색하여 연결되어 육지들은 같은 섬입니다.
    2. 한번의 육지 bfs 탐색마다 인덱스를 부여해 같은 섬으로 연결된 육지들이라면 모두 같은 인덱스를 부여합니다.
2. 구해진 섬 인덱스로 자신을 제외한 다른 섬을 연결하는 경로를 탐색합니다.
    1. 먼저 해당 섬의 외곽에 존재하는( 바다와 맡닿아 있는) 모든 육지들을 큐에 넣습니다. 
    2. 큐에 넣어진 값들로부터 다른 섬을 찾을 때까지 탐색합니다.
    3. 다른 섬을 가장 먼저 찾은 탐색이 있다면, 그 탐색의 소요시간을 최소의 다리 수와 갱신합니다.
    4. 최소의 다리 수보다 많은 탐색을 하게되는 모든 경우를 백트래킹합니다.
    5. 모든 섬에 대해서 진행합니다.

## 코드

```java
static void findIsland(){
		int islandIdx = 1;
		while(!q.isEmpty()){
				Node curr = q.poll();
				int row = curr.row;
				int col = curr.col;
				if(map[row][col] == 1){
						map[row][col] += islandIdx;
						setIsland(row, col, islandIdx++);
				}
		}
}
```

- 먼저 연결된 육지들을 섬으로 판별하기 위해 육지인 땅으로부터 섬 만들기를 시작합니다.
- 이 때 하나의 섬으로 판별된 육지들은 모두 같은 인덱스 값을 부여해줍니다.

```java
static void setIsland(int row, int col, int num){
		for(int i = 0 ; i < 4 ; i++){
			int nr = row + DIR[i][0];
			int nc = col + DIR[i][1];

			if(nr < 0 || nr >= N || nc < 0 || nc >= N)  continue;
			if(map[nr][nc] != 0 && map[nr][nc] != 1)    continue;

			if(map[nr][nc] == 0 ) {
					q2.offer(new Node(nr, nc, num + 1));
					map[nr][nc] = -1;
					continue;
			}

				map[nr][nc] += num;
				setIsland(nr, nc, num);
		}
}
```

- 이어져 있는 땅의 경우 모두 같은 값을 부여해주고 가장자리(바다와 만나는 곳)의 좌표를 큐에 넣어줍니다.

```java
int minLen = Integer.MAX_VALUE;

loop1 :
while(!q2.isEmpty()){
	int currIdx = q2.peek().idx;
	while(!q2.isEmpty() && q2.peek().idx == currIdx){
		q.offer(q2.poll());
	}
```

- 큐에 담겨진 가장자리들을 섬의 번호에 따라 구별해줍니다.

```java
visited = new boolean[N][N];
while(!q.isEmpty()){
		if(times ++ > minLen) {
			q.clear();
			break;
		}
	
		for(int size = q.size(); size > 0; size--){
					Node curr = q.poll();
			
					for(int i = 0 ; i < 4 ; i++){
						int nr = curr.row + DIR[i][0];
						int nc = curr.col + DIR[i][1];
			
						if(nr < 0 || nr >= N || nc < 0 || nc >= N )     continue;
						if(map[nr][nc] == curr.idx || visited[nr][nc])  continue;
			
						if(map[nr][nc] != 0 && map[nr][nc] != -1 ){
							q.clear();
							minLen = Math.min(times, minLen);
							continue loop1;
						}
						visited[nr][nc] = true;
						q.offer(new Node(nr, nc, curr.idx));
				}
		}
}
```

- 각 섬의 번호로 구분된 탐색에서 다른 섬을 찾을 때까지 탐색하여 성공적으로 찾았다면 최솟값과 비교해줍니다.

## 후기

문제를 설계하고 해결하는 과정에서 최적화 욕심 때문에 너무 많은 요소들을 작성했다. 가장자리만 큐에 담는다던가 큐를 나누어서 검사한다던지 하는 작업들이 많았다. 결국 시간안에 문제를 제출하지 못했는데, 설계 자체의 문제가 아니라 `for(int i = 0 ; i < 4 ; i++)` 에서 `4` 를 `3` 으로 작성하는 바보같은 실수가 일어났다.

문제를 복잡하게 해결하려다보니 디버깅을 하는 것도 쉽지 않았다. 다음에 문제를 해결할 때는 최적화보다 먼저 가장 직관적이게 해결할 수 있는 방법을 생각해봐야겠다.