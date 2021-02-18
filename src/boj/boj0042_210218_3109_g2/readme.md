## 문제 정의

1. 크기 R * C 인 벽과 갈 수 있는 공간으로 이루어진 2차원 맵을 받습니다.
2. col == 0 에서 col == C - 1 으로 갈 수 있는 최대 경로의 수를 구합니다.
3. 이 때 벽은 통과할 수 없고, 각각의 경로는 대각선으로도 이동할 수 있습니다.

## 문제 풀이

1. 모든 출발지로부터 깊이우선탐색을 진행합니다. 이 때 다음 좌표는 row -1, row, row + 1 과 col + 1입니다.
2. dfs 과정에서 한번 방문한 구역은 벽으로 바꾸어 더이상 이동할 수 없게 합니다.
3. 한번 끝에 도달한 경로는 나누어진 다른 분기에 대해 탐색할 수 없도록 true를 리턴해줍니다.
4. 이미 방문했던 좌표거나, 맵 밖으로 나간 경로는 false를 리턴해주어 해당 경로로부터 다른 분기의 경로 탐색이 가능하도록 합니다.

## 코드

```java
map = new boolean[R][C];
for(int i = 0 ; i < R ; i++){
    String str = br.readLine();
    for(int j = 0 ; j < C ; j++){
        map[i][j] = str.charAt(j) == 'x';
    }
}
```

- 벽을 true, 길을 false로 받아줍니다.

```java
static boolean dfs(int row, int col){
    if( row < 0 || row >= R || map[row][col])   return false;
    map[row][col] = true;
    if(col == C - 1){
        ans++;
        return true;
    }
    for(int i = 0 ; i < 3; i++){
        if(dfs(row + rowDir[i], col + 1))   return true;
    }
    return false;
}
```

- 각 dfs 별로 우상, 우, 우하로 접근할 수 있도록합니다. 이 때 그 경로가 이미 도착 지점을 찾아서 다른 경로로 분기할 필요가 없을 땐 바로 반환해줍니다.
- 각 경로가 맵 밖을 나갔거나, 이미 방문한 지점에 도착했을 경우 false 를 반환합니다.
- 각 방문마다 방문체크를 해주고, 그 지점이 도착지점이면 경로의 수를 하나 더해주고 true를 반환합니다.