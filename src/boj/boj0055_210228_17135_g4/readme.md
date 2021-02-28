## 문제 정의

1. `N * M` 의 맵 입력이 주어집니다. `N` 행 밑의 행의 모든 칸은 성이 있다고 가정합니다.
2. 성의 하나의 칸 당 하나 씩 궁수 3명을 배치한다고 했을 때 궁수가 공격하는 적은 거리 `D` 이하의 가장 가까운 적이며 그런 적이 여럿이라면 가가장 왼쪽에 있는 적을 공격합니다. 두 격자판 사이 거리는 `|r1 - r2| + |c1 - c2|` 입니다.
3. 모든 궁수는 동시에 공격하며 같은 적이 여러 궁수에게 공격당할 수 있습니다.
4. 매 턴 궁수의 공격이 끝나면 적은 한 칸 아래로 이동하고, 성이 있는 칸에 접근하면 적은 제외됩니다. 모든 적이 제외되면 게임이 끝납니다.
5. 궁수를 임의대로 배치할 때 최대로 적을 제거할 수 있는 수를 출력하세요.
6. 첫 째 줄에 `N` , `M` ( 3 ≤ N, M ≤ 15), `D` ( 1≤ D ≤ 10) 이 주어집니다.

## 문제 풀이

1. 성의 좌표에 대하여 궁수를 배치할 수 있는 조합을 뽑습니다. 
2. 해당 궁수 배치로 게임을 진행합니다.
    1. 게임은 궁수의 사격, 적의 이동 순으로 진행됩니다.
        1. `row` 좌표를 `N-1` 부터 한칸 씩올리면서 적의 이동을 대신합니다
        2. 각 궁수로부터 가장 가까운 적의 좌표를 찾습니다.
            1. bfs 나 직접 좌표 계산으로 찾습니다.
        3. 모든 궁수의 가장 가까운 좌표의 적을 제거합니다.
    2. 모든 적이 격자에서 제거되면 제거맵에서 제거한 적의 수를 카운트하고 종료합니다.
3. 모든 조합에서 가장 많은 적을 제거한 조합의 결과를 출력합니다.

## 코드

```java
static boolean np(){
    int i = M - 1;
    while(i > 0 && selected[i - 1] >= selected[i])  i--;
    if(i == 0)  return false;

    int j = M - 1;
    while(selected[i - 1] >= selected[j])   j--;
    idxSwap(i - 1, j);

    int k = M - 1;
    while(k > i){
            idxSwap(i++, k--);
    }
    return true;
}

static void idxSwap(int idx1, int idx2){
    int temp = selected[idx1];
    selected[idx1] = selected[idx2];
    selected[idx2] = temp;
}
```

- np 알고리즘으로 궁수 조합을 생성합니다.

```java
static int game(){
    killed = new boolean[N][M];
    int[][] killPos = new int[3][];
    for (int row = N; row > 0; row--) {
        for(int i = 0 ; i < 3; i++) {
            killPos[i] = null;
            kill(row, i, killPos);
    }

    for(int i = 0 ; i < 3; i++) {
        if (killPos[i] != null) {
        killed[killPos[i][0]][killPos[i][1]] = true;
            }
        }
    }
    return check(killed);
}
```

- 적의 이동을 `row` 의 이동으로 제어하고 해당 `row` 에서 제거할 수 있는 적의 좌표를 받아옵니다.
- 좌표를 받아와서 해당 좌표의 적을 제거합니다.

```java
static void kill(int row, int i, int[][] killPos){
    for(int r = row - 1; r >= row - 1 - D && r >= 0; r--){
        for(int c = Math.max(archer[i] - D, 0); c < archer[i] + D && c < M; c++ ){

            int dist = Math.abs(row - r) + Math.abs(archer[i] - c);
            if(!map[r][c] || killed[r][c] || dist > D)  continue;

            if(killPos[i] == null){
                    killPos[i] = new int[]{r, c, dist};
            }else if (dist < killPos[i][2] || (dist == killPos[i][2] && c < killPos[i][1])){
                killPos[i][0] = r;
                killPos[i][1] = c;
                killPos[i][2] = dist;
           }
        }
    }
}
```

- 각 좌표에서 가장 가까운 적을 찾습니다.
- BFS 알고리즘으로 대체해도 됩니다.

## 후기

좌표를 제거하는 알고리즘을 직접 row, col 값을 비교해서 했기 때문에 알고리즘을 짜는데 굉장히 복잡했습니다. 적을 제거할 때 bfs 탐색으로 가장 가까운 적을 찾아서 제거했으면 더 가독성 있는 알고리즘으로 구현할 수 있을 것 같습니다.