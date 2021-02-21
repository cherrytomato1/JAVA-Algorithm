## 문제 정의

1. R * C(1 ≤ R, C ≤ 1,500) 배열에 물과 얼음과 백조가 입력됩니다. 물에 닿은 모든 빙판은 하루에 한 칸 씩 녹습니다.
2. 이 때 두 백조는 얼음을 지나갈 수 없으며 물만 지나갈 수 있습니다. 두 백조가 만나는 데 걸리는 최소의 일을 출력합니다.

## 문제 풀이

1. 모든 물의 좌표로부터 방문체크를 하면서 bfs 탐색을 합니다. 이 때 얼음과 만났다면 해당좌표를 물로 바꾸고 다음 날에 탐색해야 하는 큐에 넣습니다.
2. 매 하루가 지날 때마다 첫 번째 백조로부터 bfs 탐색을 합니다. 두 백조가 만날 수 없으면 다음날 탐색을 시작합니다. 이 때 백조가 얼음과 만나는 지점을 다음날 백조가 탐색해야 하는 지역으로 큐에 넣습니다. 다음날부터는 이 좌표부터 탐색합니다.
3. 그 날 해야하는 탐색 시작 표부터 다시 bfs 탐색을 하여 얼음을 녹입니다. 이후 1~3 을 반복합니다.
4. 백조가 만날 수 있다면 걸린 날짜를 출력합니다.

- 물과 백조의 탐색을 분리하고, 각각 다음날 해야하는 탐색과 오늘 해야하는 탐색을 분리하는 것이 핵심입니다.

## 코드

```java
map = new boolean[R][C];
visited = new boolean[2][][];
visited[0] = new boolean[R][C];
visited[1] = new boolean[R][C];

dq = new Deque[4];
bj = new Node[2];
for(int i = 0 ; i < 4; i++){
    dq[i] = new ArrayDeque<>();
}
```

- `map` 은 `boolean` 배열로 정의하고 백조와 물에 대해 각각 `visited` 배열을 `boolean` 으로 정의합니다.
- 두 백조의 좌표를 `bj` 에 저장하고, 물의 당일 탐색 큐를 `dq[0]` , 물의 다음날 탐색을 `dq[1]` , 백조의 당일 탐색을 `dq[2]` , 백조의 다음날 탐색을 `dq[3]` 으로 정의합니다.

```java
for(int r = 0, i = 0; r < R; r++){
    char[] in = br.readLine().toCharArray();
    for(int c = 0; c < C ; c++){
        map[r][c] = in[c] == 'X';
        if(!map[r][c]){
            dq[0].offer(new Node(r, c));
            visited[0][r][c] = true;
        }
        if(in[c] == 'L')    bj[i++] = new Node(r, c);
    }
}
dq[3].offer(bj[0]);
```

- `map` 을 입력 받을 때, 물이면 `dq[0]` 에 푸시하고 해당 좌표를 방문처리해줍니다.
- 백조일 경우 해당 백조 좌표를 `bj` 에 추가합니다.
- 입력이 끝난 후 첫 번째 백조의 좌표를 `dq[3]` 에 푸시합니다.

```java
static int solve(){
    do{
        bfs(true);
        times ++;
    } while(!bfs(false));
    return times;
}
```

- 매일 물 탐색, 백조 탐색 순으로 진행합니다. 백조 탐색에서 백조를 찾았다면 반복을 멈춥니다.

```java
static boolean bfs(boolean type){
    //queue 의 타입은 뮬일 때 0, 1 그리고 백조일 때 2,3 입니다.
    int dqType = type ? 0 : 2;
    //방문배열의 타입입니다. 물과 백조입니다.
    int visitedType = type ? 0 : 1;

    //물과 백조의 탐색에서 각각 다음날 탐색해야 하는 좌표들을 오늘 탐색하는 큐로 가져옵니다.
    while(!dq[dqType + 1].isEmpty()) dq[dqType].offer(dq[dqType + 1].poll());
    Node curr;
    //오늘 탐색해야 하는 큐가 빌 때까지 탐색합니다.
    while(!dq[dqType].isEmpty()){
        curr = dq[dqType].poll();
        for(int i = 0 ; i < 4; i++){
            int nr = curr.row + DIR[i][0];
            int nc = curr.col + DIR[i][1];

            if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[visitedType][nr][nc])  continue;
            visited[visitedType][nr][nc] = true;

            //얼음을 만났을 경우 물 탐색때 얼음을 지워주고, 백조와 물일 때 둘다 내일 탐색해야 하는 큐로 넘깁니다.
            if(map[nr][nc]) {
                if(type)    map[nr][nc] = false;
                dq[dqType + 1].offer(new Node(nr, nc));
            }
            // 백조로 탐색할 때 다른 백조를 만나면 종료합니다.
            else if(!type && nr == bj[1].row && nc == bj[1].col) return true;
            // 탐색한 좌표가 물이면 오늘 탐색할 좌표에 추가합니다.
            else            dq[dqType].offer(new Node(nr, nc));
        }
    }
    return false;
}
```

- 백조인지 물인지에 따라 접근해야하는 큐의 인덱스와 방문배열의 인덱스를 정의해줍니다.
- 탐색 전 다음날 탐색 큐를 모두 오늘 탐색큐로 옮깁니다.
- 오늘 탐색 큐가 빌 때까지 반복하며 4방 탐색하며 경계 검사 및 방문 체크를 합니다.
- 얼음을 만났다면 내일 탐색 큐에 해당 얼음의 좌표를 넣어줍니다. 이 때 물 탐색중이라면 얼음을 녹입니다.
- 백조 탐색 중 백조를 만난다면 `true` 를 반환하여 반복을 종료합니다.
- 탐색하는 좌표가 물이라면 계속해서 오늘탐색 큐에 넣어 진행합니다.

## 후기

 문제가 복잡해보였지만 해야하는 것들을 분리해서 생각하다보니 생각보다 쉽게 풀어내었습니다. 코드 작성 중 물 탐색과 백조 탐색을 분리해서 작성했는데 몇가지 조건 빼고는 겹치는 부분이 많아 하나로 합쳐서 작성했습니다. 처음 생각했던 대로 로직을 짜고, 정상적으로 동작하며 정답을 확인했을 때 기분이 너무 좋았습니다. 또한 다른 사람의 풀이보다 수행시간도 빨라서 매우 만족했습니다.