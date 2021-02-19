## 문제 정의

1. 화단의 크기 N ( 6 ≤ N ≤ 10) 이 주어지고, 그 안에 화단의 각 좌표에 대한 가격이 주어집니다.
2. 화단 내에 + 모양의 꽃을 서로 닿지 않게 심을 때 가장 적게 드는 비용으로 심는 경우의 비용을 출력합니다.

## 풀이

1.  1,1 부터 N-2, N-2 까지 탐색하며 해당 위치 포함 4방의 방문체크를 합니다.
2. 방문된 좌표가 없다면 해당 5개의 위치에 방문체크를 하고 해당 위치의 값을 모두 더해 다음 재귀로 보내줍니다.
3. 1~2 과정을 반복하여 재귀 깊이가 3이 되었을 때 총 가격의 최솟값과 비교하여 저장합니다.
4. 모든 좌표에 대한 탐색이 끝난 후 최솟값을 출력합니다.

## 코드

```java
static final int[][] DIR ={{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
```

- 현재 위치 포함 5개의 좌표를 한 번에 탐색해야 하므로 5개의 인덱스를 가진 방향배열을 선언합니다.

```java
static void solve(){
    for(int r = 1 ; r < N - 1; r++){
        for(int c = 1; c < N - 1; c++){
            visited = new boolean[N][N];
            find(r, c, 0, 1);
        }
    }
}
```

- 1, 1 ~ N - 2, N -2 의 좌표에서 모두 재귀함수를 호출합니다. 탐색 시 방문배열을 초기화 합니다.

```java
static void find(int row, int col, int val, int cnt){
    if(!checker(row, col))  return ;
    val += marker(row, col, true);

    if(cnt == 3){
        res = res < val ? res : val;
        marker(row, col, false);
        return;
    }
// ...
```

- 해당 row와 col 좌표에서 겹치는 곳이 있는지 checker 메서드를 통해 방문체크 합니다.
- 미방문 좌표일 시 해당 좌표에 marker로 방문표시를 하고 값의 합을 가져옵니다.
- 마킹을 완료했을 때 재귀 깊이가 3이면서  해당 좌표에서 최솟값을 받습니다. 이 후 해당 좌표의 방문표시를 해제합니다.

```java
for(int i = 1 ; i < N - 1; i++){
    for(int j = 1 ; j < N - 1; j++){
        find(i, j, val, cnt + 1);
    }
}
marker(row, col, false);
```

- 이 후 다시 맵 전체를 탐색하여 재귀합니다. 그리고 해당 탐색이 끝났다면 다시 마킹을 해제해 줍니다.

```java
static boolean checker(int row, int col){
    for(int i = 0 ; i < 5; i++){
        int nr = row + DIR[i][0];
        int nc = col + DIR[i][1];

        if(visited[nr][nc])   return false;
    }
    return true;
}

static int marker(int row, int col, boolean type){
    int val = 0;
    for(int i = 0 ; i < 5 ; i++){
        int nr = row + DIR[i][0];
        int nc = col + DIR[i][1];
        visited[nr][nc] = type;
        val += map[nr][nc];
    }
    return val;
}
```

- checker는 현재 좌표 포함 5방을 탐색하여 방문된 좌표인지 반환해주는 메서드 입니다.
- marker는 선언된 type으로 배열을 마킹하며, 마킹된 해당 좌표들의 값의 합을 가져오는 메서드입니다.