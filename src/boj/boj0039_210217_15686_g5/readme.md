## 문제정의

1. 각각의 치킨 가게와 집은 |row1 - row2| + |col1 - col2|의 치킨 거리를 갖습니다.
2. 치킨집 M개를 빼고 모두 폐업할 때 모든 집에 대한 최소 치킨 거리의 합을 출력합니다.

## 문제풀이

### 풀이 1 - bfs를 통한 거리 구하기

1. 맵을 입력 받을 때 모든 집의 좌표와 치킨집의 좌표를 각 배열에 저장하면서, 치킨집의 수와 집의 수를 세줍니다.
2. 치킨집 배열에서 M개의 조합을 뽑아낸 후, 각 조합에서 뽑힌 치킨집과 존재하는 모든 집들이 갖는 가장 가까운 치킨집과의 거리를 모두 더해줍니다.
3. 위 과정을 모든 조합에서 반복하여 가장 낮은 거리합을 구해 출력합니다.
- 문제는 각 집과 치킨집 사이에 방해물이 없고, 이동해야하는 실제 칸 수가 아닌 거리를 계산하는 수식이 있으므로 다 탐색할 필요는 없습니다.

### 풀이 2 - 집으로부터 가장 가까운 치킨집 찾기

1. 풀이 1과 같이 치킨집에 대한 조합을 뽑습니다.
2. 이후 각 집에서 조합에 뽑힌 치킨집 중 가장 가까운 치킨집의 거리를 구합니다.
3. 조합마다 모든 집의 가까운 치킨집의 거리를 구하여 더해주고, 만들어진 조합중에 이 수가 가장 낮은 값을 답으로 출력합니다.
- 이 때 탐색을 할 필요가 없으므로 맵을 그리는 배열을 선언하지 않아도 됩니다.

## 코드

풀이 2가 속도도 훨씬빠르고 메모리도 훨씬 적게 썼으므로 풀이 2의 코드를 기술합니다.

```java
class Node {
    int row;
    int col;

    public Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}
```

- 집과 치킨집의 좌표를 저장하기 위한 클래스 node 를 선언합니다.

```java
chicken = new Node[N * N];
house = new Node[N * N];

for(int r = 0 ; r < N; r++){
    st = new StringTokenizer(br.readLine());
    for(int c = 0 ; c < N ; c++){
        char pos = st.nextToken().charAt(0);
        if(pos == '1')        house[houseCnt++] = new Node(r, c);
        else if(pos == '2')   chicken[chickenCnt++] = new Node(r, c);
    }
}
```

- 입력으로부터 치킨집과 집의 개수를 세어주고 해당 치킨집과 집 마다의 좌표를 배열에 저장합니다.

```java
int[] flag = new int[chickenCnt];
int min = Integer.MAX_VALUE;
int cnt ;
for(int i = chickenCnt - 1; i >= chickenCnt - M ; i--){
    flag[i] = 1;
}
do{
    cnt = check(flag);
    min = Math.min(cnt, min);
}while(np(flag));
return min;
```

- flag를 이용한 next permutation 알고리즘으로 순열을 구해줍니다. 이 후 해당 조합에서 치킨집의 거리합이 얼마인지 구해주는 check 메소드를 호출합니다.

```java
int cnt = 0;
for(int i = 0; i < houseCnt; i++){
    int row = house[i].row;
    int col = house[i].col;
    int dist = Integer.MAX_VALUE;
    for(int j = 0 ; j < chickenCnt; j++){
        if(flag[j] != 1)    continue;
        dist = Math.min(dist, Math.abs(chicken[j].row - row) + Math.abs(chicken[j].col - col));
    }
    cnt += dist;
}
return cnt;
```

- 모든 집에서 가장 가까운 치킨집을 구하기 위해 조합에 포함된 모든 치킨집과의 거리를 구합니다. 그 후 가장 낮은 값이 가장 가까운 치킨집과의 거리이므로, 전체 치킨집 거리합에 더해줍니다.
- 모든 집을 탐색했으면 거리합을 반환합니다.