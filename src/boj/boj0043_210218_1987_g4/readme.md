## 문제 정의

1. R, C ( 1 ≤ R, C ≤ 20)의 알파벳으로 만들어진 맵이 주어진다.
2. 말은 좌측 최상단에 위치하며 말이 이미 밟았던 알파벳을 밟지 않고 이동할 수 있는 최대의 칸 수를 출력

## 문제풀이

1. 말이 이동하는 좌표를 dfs로 구현한다. 가장 오래 살아 남는 노드를 구해야한다.
2. 이 때 이미 지난 알파벳을 검사하기 위해 알파벳에 해당하는 비트마스킹을 구현한다. 또한 해당 좌표에 대한 방문 체크도 따로 한다.
3. 이 때 방문체크를 알파벳 플래그(알파벳은 한 번씩 밖에 밟지 못하므로 count 역할)로 함으로써 한번 방문했던 경로로는 다시 방문하지 않게 한다.

## 코드

```java
static int res = Integer.MIN_VALUE;
static void dfs(int row, int col, int flag, int cnt){
    res = res > cnt ? res : cnt;
    for(int i = 0 ; i < 4; i++){
        int nr = row + DIR[i][0];
        int nc = col + DIR[i][1];

        if(nr >= R | nr < 0 | nc >= C | nc < 0) continue;

        int nextFlag = 1 << map[nr][nc] - 'A';
        if(visitedFlag[nr][nc] == (flag | nextFlag) || (flag & nextFlag) != 0)     continue;

        visitedFlag[nr][nc] = flag | nextFlag;
        dfs(nr, nc, flag | nextFlag, cnt + 1);
    }
}
```

- 재귀 인자로 row, col, 알파뱃 flag, cnt 를 넘깁니다.
- 4방 탐색하여 다음 이동 위치가 경계 밖이거나, 이미 같은 알파벳 배열로 방문했던 곳, 혹은 이미 밟은 알파벳이 있다면 방문하지 않는다.
- 방문할 때마다 res 값을 갱신시키며 더 이상 방문할 공간이 없으면 종료한다.