23 : 00 시작 

## 문제 정의

1. 입력 N(2 ≤ N ≤ 20) 개의 공간에 물고기와 상어가 있다. 물고기는 1~ 6으로 주어지고 빈공간은 0 , 아기상어는 9 로 주어진다.
2. 상어는 초기에 2의 크기이며, 상어와 같은 크기의 물고기는 지나갈 수 있고, 상어보다 작은 물고기는 먹을 수 있다.
3. 상어는 1초에 1칸 씩 이동하고 물고기를 먹는 조건은 다음과 같다
    - 먹을 수 있는 물고기가 하나라면, 그 물고기를 먹으러 간다.
    - 먹을 수 있는 물고기가 1마리 이상이면 가장 가까운 물고기를 먹는다. 이 때 가장 가까운 고기가 둘 이상이면 가장 위, 가장 위의 고기가 여럿이라면 그 중에 가장 왼쪽 고기를 먹는다.
4. 자신의 크기와 같은 수의 고기를 먹으면 크기가 하나 커진다.
5. 더 이상 먹을 수 있는 고기가 없으면 종료된다.
6. 출력으로 최대한으로 물고기를 먹을 때 걸리는 시간을 공백으로 구분하여 출력한다.

## 문제 풀이

1. 맵과 상어의 위치를 입력받는다. 상어의 초기 위치를 큐에 넣고 해당 좌표는 맵에서 지운다. 상어의 초기 사이즈를 초기화한다.
2. 큐를 시간 별로 반복하여 사방탐색한다. 크기와 맵의 밖, 그리고 이미 방문했던 좌표에 대한 예외처리를 한다. 물고기를 발견하면 해당 시간에 같이 물고기를 발견한 좌표에 대해서 우선순위 계산을 한다.
3. 우선순위를 통해 정해진 다음 경로 이외의 모든 경로(queue)를 버리고 방문 배열을 초기화 한다. 이때 소모된 시간을 갱신한다.
4. 이후 해당 좌표에서 물고기를 지우고, 상어의 먹이수를 늘려 조건에 맞다면 상어의 크기를 키운다.
5. 남겨진 하나의 경로에서부터 다시 2번으로 이동하여 반복한다.
6. 큐가 완전히 비워지거나, 남은 물고기가 없으면 결과를 출력한다.

## 코드

```java
for(int r = 0 ; r < N ; r++){
    st = new StringTokenizer(br.readLine());
    for(int c = 0 ; c < N ; c++){
        map[r][c] = Integer.parseInt(st.nextToken());
        switch(map[r][c]){
            case 9 :
                map[r][c] = 0;
                q.offer(new int[]{r, c});
                break;
            case 6 : case 5 : case 4 :
            case 3 : case 2 : case 1 :
                fish++;
        }
    }
}
```

1. 배열을 입력받고 초기 상어 위치를 큐에 넣는다. 이후 해당 위치는 0으로 바꿔준다. 물고기의 수도 카운트한다.

```java
while(!q.isEmpty() && fish != 0){
    cnt ++;
    int rowGo = Integer.MAX_VALUE;
    int colGo = Integer.MAX_VALUE;
    boolean flag = false;

    for(int size = q.size() ; size != 0 ; size--){ //....
```

 2.  큐가 비거나 물고기가 없을때까지 탐색한다. 이 때 cnt를 기록하기 위해 각 시간별로 큐를 나눠서 탐색한다. rowGo와 colGo, flag 는 해당 큐 반복에서 찾아낸 다음 좌표를 저장하고 비교하기 위한 변수.

```java
for(int size = q.size() ; size != 0 ; size--){
    int[] pos = q.poll();
    for(int i = 0 ; i < 4; i++){
        int nr = pos[0] + dr[i];
        int nc = pos[1] + dc[i];

        if(nr >= N || nr < 0 || nc >= N || nc < 0 )   continue;
        if(visited[nr][nc] || map[nr][nc] > shark)   continue;

        if(map[nr][nc] != 0 && map[nr][nc] < shark){
            flag = true;
            if(rowGo > nr) {
                rowGo = nr;
                colGo = nc;
            }
            else if(rowGo == nr){
                colGo = colGo < nc ? colGo : nc;
            }
        }
        visited[nr][nc] = true;
        q.offer(new int[]{nr, nc});
    }
}
```

 3.  각 시간 별로 큐를 반복하며 각 큐의 좌표에서는 4방 탐색을 진행한다. 맵 밖, 상어 크기, 방문 좌표에 대한 예외처리를 한다. 좌표 이동 후 방문체크를 하고 큐에 삽입한다.

만약 먹을 수 있는 물고기의 좌표에 도착하면 이미 저장되어 있는 좌표와 우선순위를 계산해서 좌표를 갱신한다.

```java
if(flag) {
    if(++point == shark) {
        shark++;
        point = 0;
    }
    getFish(cnt, rowGo, colGo);
}

static void getFish(int cnt, int r, int c){
    for(int i = 0 ; i < N ; i++){
        Arrays.fill(visited[i], false);
    }
    while(!q.isEmpty())     q.poll();
    fish--;
    res = cnt;
    map[r][c] = 0;

    visited[r][c] = true;
    q.offer(new int[]{r, c});
}
```

 4.  먹기 가능 물고기 좌표에 도달한 경우, 상어의 경험치를 늘리고 방문 배열 및 큐를 초기화 하고 그 좌표만 다시 넣는다.

### 전체 코드

```java
import java.util.*;
import java.io.*;

public class boj_16236 {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    //                  상 좌  우  하
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    static int N;
    static int fish;
    static int res;
    static int[][] map;
    static Queue<int[]> q = new ArrayDeque<>();

    static boolean[][] visited;

    static void bfs(){
        int cnt = 0;
        int shark = 2;
        int point = 0;
        while(!q.isEmpty() && fish != 0){
            cnt ++;
            int rowGo = Integer.MAX_VALUE;
            int colGo = Integer.MAX_VALUE;
            boolean flag = false;

            for(int size = q.size() ; size != 0 ; size--){
                int[] pos = q.poll();
                for(int i = 0 ; i < 4; i++){
                    int nr = pos[0] + dr[i];
                    int nc = pos[1] + dc[i];

                    if(nr >= N || nr < 0 || nc >= N || nc < 0 )   continue;
                    if(visited[nr][nc] || map[nr][nc] > shark)   continue;

                    if(map[nr][nc] != 0 && map[nr][nc] < shark){
                        flag = true;
                        if(rowGo > nr) {
                            rowGo = nr;
                            colGo = nc;
                        }
                        else if(rowGo == nr){
                            colGo = colGo < nc ? colGo : nc;
                        }
                    }
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
            if(flag) {
                if(++point == shark) {
                    shark++;
                    point = 0;
                }
                getFish(cnt, rowGo, colGo);
            }
        }
    }

    static void getFish(int cnt, int r, int c){
        for(int i = 0 ; i < N ; i++){
            Arrays.fill(visited[i], false);
        }
        while(!q.isEmpty())     q.poll();
        fish--;
        res = cnt;
        map[r][c] = 0;

        visited[r][c] = true;
        q.offer(new int[]{r, c});
    }

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int r = 0 ; r < N ; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < N ; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                switch(map[r][c]){
                    case 9 :
                        map[r][c] = 0;
                        q.offer(new int[]{r, c});
                        break;
                    case 6 : case 5 : case 4 :
                    case 3 : case 2 : case 1 :
                        fish++;
                }
            }
        }
        bfs();
        System.out.println(res);
    }
}
```

## 후기

### 잘못 이해한 문제와 코드 작성 실수

초기에 상어가 물고기를 하나 먹을 때마다 크기가 증가하는 줄 알고 잘못  작성했다. 이후 다시 수정하여 동작시켰지만 마지막 테스트 케이스에서 출력이 잘못되었고, 초기 상어 좌표인 9 를 맵에서 지우지 않았다는 것을 알고 고쳤다. 

### 알고리즘 재구성

상어가 같은 거리에 있는 먹이를 찾아서 먹는다고 했을 때, 방향배열 만으로 (상좌우하) 우선순위를 부여할 수 있다고 생각했다. 하지만 방향 배열에서 나중에 탐색하더라도, 위로 가는 이동이 많으면 row가 더 낮은 결과가 나올 수 있어, 한 번의 시간에 탐색한 모든 좌표에 대해서 우선순위를 따로 부여해서 결과를 계산해야했다.

### 최적화

처음 문제를 제출했을 때 2초라는 어마어마한 시간과 90메가라는 끔찍한 메모리 사용량을 보았다. 다른 사람들의 풀이 결과를 참고한 결과 BFS를 통한 최단 경로 탐색에서는 굳이 visited 배열을 integer로 선언할 필요가 없었다. 이미 큐를 통해 순차적으로 전진하므로, 이전에 도착한 경우보다 더 빨리 도착할 수 없기 때문이다. DFS와 이 점이 다른 것을 숙지해야겠다.

또한 q를 비우는 작업이나 배열을 비우는 작업은 비워야할 원소가 많아질수록 시간복잡도가 늘어나니, 차라리 최악의 경우가 항상 같도록 새로운 객체를 생성하여 레퍼런스를 할당해주는 방법도 있다는 것을 알았다.