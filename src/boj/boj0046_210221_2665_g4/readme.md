## 문제 정의

1. n * n( 1 ≤ n ≤ 50)의 0과 1로 이루어진 맵을 입력받습니다.
2. 0,0 부터 n - 1, n - 1 좌표로 이동한다고 했을 때, 1은 이동할 수 있고 0 은 이동할 수 없습니다. 이 때 0을 1로 바꾸어야하는 최소의 개수를 출력합니다.

## 문제 풀이

1. 0,0 부터 dfs로 탐색합니다. 각각의 좌표에 각 좌표까지 도달했을 때 최소로 변경되어야 하는 방의 값을 계산합니다. 이 값을 초과한 탐색은 백트래킹합니다.
2. 탐색을 끝마쳤을 때 가장 적은 변경회수를 출력합니다

- 가장 빨리 푼 풀이는 다익스트라를 이용한 풀이였습니다. 다익스트라에 대해 배워야할 것 같습니다.

## 코드

```java
for(int r = 0 ; r < N ; r++){
    char[] in = br.readLine().toCharArray();
    for(int c = 0; c < N ; c++){
        map[r][c] = in[c] == '1';
    }
    Arrays.fill(visitedCnt[r], Integer.MAX_VALUE);
}
```

- 입력을 `boolean` 배열로 받고, 방문 배열을 `Integer.MAX_VALUE` 로 초기화 시켜줍니다.

```java
static boolean dfs(int row, int col, int cnt){
    visitedCnt[row][col] = cnt;
    for(int i = 0; i < 4; i++){
        int nr = row + DIR[i][0];
        int nc = col + DIR[i][1];

        if(nr >= N || nr < 0 || nc >= N || nc < 0)  continue;
        int nextCnt = map[nr][nc] ? cnt : cnt + 1;
        if(visitedCnt[nr][nc] <= nextCnt)           continue;
        if(nr == N - 1 && nc == N - 1){
            if(nextCnt == 0)    {
                res = 0;
                return true;
            }

            res = res < nextCnt ? res : nextCnt;
            return false;
        }

        if(dfs(nr, nc, nextCnt))     return true;
    }
    return false;
}
```

- 시작 위치부터 dfs 탐색을 시작합니다. 경계 검사 및 해당 좌표의 `cnt`랑 비교해서 현재 탐색 경로가 크거나 같을경우 방문하지 않습니다.
- `cnt` 는 바꿔야하는 벽의 수를 의미하며 다음 방문할 좌표가 `0` 이면 `cnt` 를 하나 더합니다.
- 목표 좌표에 도착했을 경우 최소의  `res`  저장합니다. 이 때 `res` 가 0 이라면 더 탐색할 필요 없으므로 true를 반환합니다.