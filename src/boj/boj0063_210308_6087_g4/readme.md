## 문제 정의

1. `W` * `H` ( 1≤ W, H ≤ 100) 의 배열이 주어집니다.
2. `.` 은 빈칸이고 `*` 은 벽이며 두 개의 `C` 를 레이저로 통신하려고 합니다.
3. 레이저는 거울에 의해 90도로 꺾일 수 있을 때 두 `C` 를 연결하기 위한 거울 개수의 최솟값을 출력합니다.

## 문제 풀이

1. `C` 로부터 4방향 탐색을 시작합니다.
2. BFS 탐색을 할 때 한 방향으로 벽에 도착할 때까지 탐색합니다. 탐색하는 도중의 경로는 모두 다음 탐색 큐에 넣습니다.
3. 끝까지 전진하여 `C` 를 만나지 못했을 경우 큐에 있던 좌표들로부터 4방향으로 탐색합니다.
4. 이 때 거울의 반사 횟수를 방문배열에 저장하여 백트래킹합니다.

## 코드

```java
visited[i][j] = Integer.MAX_VALUE;
map[i][j] = in[j] == '*';
if(in[j] == 'C'){
    if(q.isEmpty()) q.offer(new Node(i, j));
    else            end = new Node(i, j);
}
```

- 입력받으면서 `map` 을 `boolean[][]` 로 저장합니다.
- `visited` 를 `Integer.MAX_VALUE` 로 초기화합니다.
- 첫번째 `C` 는 큐에 넣고 두번째 `C` 는 도착지점으로 저장합니다.

```java
int ret = Integer.MAX_VALUE;
int cnt = 0;
    while(!q.isEmpty()){
        for(int i = 0, size = q.size() ; i < size; i++){
            Node curr = q.poll();
            for(int j = 0 ; j < 4; j++){
                if(go(curr, j, cnt))    ret = Math.min(cnt,ret);
            }
        }
        cnt++;
    }
return ret;
```

- 한 번의 큐 전체 탐색을 분리해주고 해당 탐색을 끝내면 `cnt` 를 더해줍니다.
- 각 탐색은 해당 좌표로부터 4방향에서 직진이 불가능할 때까지 반복합니다.
- 나중에 도착한 경로가 더 적은 거울을 사용할 수 있으므로 도착했더라도 바로 반환하지 않고 결과값을 갱신해줍니다.

```java
static boolean go(Node curr, int dir, int cnt){
    int nr = curr.row;
    int nc = curr.col;
    while(true){
        nr += DIR[0][dir];
        nc += DIR[1][dir];
        if(nr >= H || nr < 0 || nc >= W || nc < 0) return false;
        if(cnt > visited[nr][nc] || map[nr][nc])   return false;
    
        if(nr == end.row && nc == end.col)         return true;
        visited[nr][nc] = cnt;
        q.offer(new Node(nr, nc));
    }
}
```

- `go` 메서드에서는 입력받은 방향에 대해 벽을 만나거나 이전에 해당 좌표를 방문한 경로가 더 적은 거울을 사용했을 때까지 직진합니다.
- 위의 예외사항을 통과하고 도착한다면 `true` 를 반환합니다.
- 각 좌표 도착 시 방문 배열에는 사용한 거울 수를 기록하며 다음 탐색할 좌표를 큐에 넣습니다.