## 문제 정의

1. 맵의 크기 N(row), M(col) 가 주어진다 (3 ≤ N, M ≤ 10)
2. 두번째 부터 N 행에 맵이 주어진다.
3. 이 때  . , # , 0, R, B 가 주어지고 이때 . 은 이동가능한 공간, #은 벽, 0은 R이 들어가야하는 위치, B는 들어가면 안되는 공이다.
4. 맵은 기울이기로만 움직일 수 있으며 기울이게 되면 기울인 방향의 끝까지 이동하게된다.
5. R을 넣을 수 있는 최소의 기울임 횟수를 출력하라. 못넣으면 -1

## 풀이

1. N과 M에 맞는 char 배열을 만들고 값을 할당한다.
2. 완전탐색을 이용하여 각 기울임 동작을 하고, 그 동작 이후 다시 기울임을 하는 dfs 를 구현한다.
3. 각 기울임 동작에서는 빨간공과 파란공이 각각 벽이나 0을 만날때까지 4방향으로 반복한다.
4. 벽을 만났을 경우는 그 자리에 고정하고, 빨간공이 0을 만났으면 그 좌표 그대로 다음 재귀로 보낸다. 파란공이 0을 만났을 경우, 유망하지 않은 경로라고 판단하여 재귀를 호출하지 않는다.
5. 두 공을 좌표만으로 저장하기에 같은 좌표에 겹칠 수 있다. 이 때 이동 전의 위치를 통해 좌표를 조정해주는 예외처리를 한다. 도착 위치에서 더 멀리 있던 공을 나중에 도착한 공으로 하게끔 처리.
6. 이 때 이미 방문했던 곳을 사중배열로 체크하여  그곳에 다시 도달하게 된다면 그 경로는 유망하지 않은 경로로 판단하여 버린다(백트래킹) 이 때 돌아서 해당 좌표에 도착할 경우도 있으므로 그 좌표에 도착하기까지 걸린 이동수를 기록하여, 더 효율적인 경로가 아니라면 중복체크로 판단한다.
7. 기저조건으로 방문체크 좌표에 이미 저장된 값이 더 작거나, 10회의 방문을 초과하면 종료한다. 혹은 빨간공이 O의 위치에 정확히 위치한다면, 답값을 입력하고 종료한다ㅣ.

## 코드

```java
for(int i = 0 ; i < N ; i++){
    str = br.readLine();
    for(int j = 0; j < M ; j++){
        map[i][j] = str.charAt(j);
        if(map[i][j] == 'R') {
            rowRed = i;
            colRed = j;
            map[i][j] = '.';
        } else if(map[i][j] == 'B') {
            rowBlue = i;
            colBlue = j;
            map[i][j] = '.';
        }
    }
}
```

- 좌표 입력받기. 이 때 공들은 좌표로만 기억하고 맵에 남기지 않게하여 예외를 제거한다.

```java
private static void f(int rowRed, int colRed, int rowBlue, int colBlue, int cnt){ 

// .... 함수동작

f(nrRed, ncRed, nrBlue, ncBlue, cnt + 1);
```

- 빨간공과 파란공의 좌표, 이동 회수를 매개변수로하는 재귀를 사용한다.

```java
int nrRed, ncRed, nrBlue, ncBlue;
  dirLoop :
  for(int dir = 0 ; dir < 4; dir++){
      for(int i = 1; ; i++){
          nrRed = rowRed + dr[dir] * i;
          ncRed = colRed + dc[dir] * i;
          if(map[nrRed][ncRed] == '#') {
              nrRed -= dr[dir];
              ncRed -= dc[dir];
              break ;
          }
          if(map[nrRed][ncRed] == 'O') break;
      }

      for(int i = 1; ; i++){
          nrBlue = rowBlue + dr[dir] * i;
          ncBlue = colBlue + dc[dir] * i;

          if(map[nrBlue][ncBlue] == '#') {
              nrBlue -= dr[dir];
              ncBlue -= dc[dir];
              break;
          }
          if(map[nrBlue][ncBlue] == 'O') continue dirLoop;
      }
// ...
```

- 각 기울임 동작은 4방향으로 반복해서 갈 수 있는 곳까지 공을 옮긴다. 옮기는 중간에 구멍을 만나면 빨간 공은 그자리에 좌표를 고정하고, 파란공은 이하 재귀문을 모두 스킵한다.

```java
if(nrRed == nrBlue && ncRed == ncBlue){
    switch(dir){
        case 0 :
            if (rowRed > rowBlue)   nrRed++;
            else                    nrBlue++;
            break;
        case 1 :
            if (rowRed < rowBlue)   nrRed--;
            else                    nrBlue--;
            break;
        case 2 :
            if (colRed > colBlue)   ncRed++;
            else                    ncBlue++;
            break;
        case 3 :
            if (colRed < colBlue)   ncRed--;
            else                    ncBlue--;
    }
}
```

- 공이 겹쳤을 때, 이동한 방향에 따라, 그리고 멀리있는 공이 어떤 것인지에 따라 공 위치를 변경해준다.

```java
private static int[][][][] visitedCounter;
//...

if(cnt == MAX_CNT || cnt - MAX_CNT >= visitedCounter[rowRed][colRed][rowBlue][colBlue] ) {
      return ;
  }
  if(map[rowRed][colRed] == 'O'){
      Ans = Ans < cnt ? Ans : cnt;
      return ;
  }
  visitedCounter[rowRed][colRed][rowBlue][colBlue] = cnt - MAX_CNT;
```

- 기저조건과 기저조건이 될 방문배열, 4중 배열로 선언하여 빨간공 파란공의 로우와 컬럼을 모두 담고, 불리언으로 방문만 체크했을 경우 더 효율적인 경로를 버릴 수 있기 때문에 cnt도 같이 담아간다.
- 빨간공이 0 이 되고 cnt 가 최솟값이라면 저장한다.

### 전체 코드

```java
package boj0020_210203_13460_g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1360_구슬탈출2 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st ;

    private static int[][][][] visitedCounter;
    private static char[][] map;
    private static int N, M;

    static final int MAX_CNT = 11;
    /**                       상  하  좌  우 */
    static final int[] dr = { -1, 1, 0, 0};
    static final int[] dc = { 0, 0, -1, 1};

    static int Ans = Integer.MAX_VALUE;
    private static void f(int rowRed, int colRed, int rowBlue, int colBlue, int cnt){
        if(cnt == MAX_CNT || cnt - MAX_CNT >= visitedCounter[rowRed][colRed][rowBlue][colBlue] ) {
            return ;
        }
        if(map[rowRed][colRed] == 'O'){
            Ans = Ans < cnt ? Ans : cnt;
            return ;
        }
        visitedCounter[rowRed][colRed][rowBlue][colBlue] = cnt - MAX_CNT;

        int nrRed, ncRed, nrBlue, ncBlue;
        dirLoop :
        for(int dir = 0 ; dir < 4; dir++){
            for(int i = 1; ; i++){
                nrRed = rowRed + dr[dir] * i;
                ncRed = colRed + dc[dir] * i;
                if(map[nrRed][ncRed] == '#') {
                    nrRed -= dr[dir];
                    ncRed -= dc[dir];
                    break ;
                }
                if(map[nrRed][ncRed] == 'O') break;
            }

            for(int i = 1; ; i++){
                nrBlue = rowBlue + dr[dir] * i;
                ncBlue = colBlue + dc[dir] * i;

                if(map[nrBlue][ncBlue] == '#') {
                    nrBlue -= dr[dir];
                    ncBlue -= dc[dir];
                    break;
                }
                if(map[nrBlue][ncBlue] == 'O') continue dirLoop;
            }

            if(nrRed == nrBlue && ncRed == ncBlue){
                switch(dir){
                    case 0 :
                        if (rowRed > rowBlue)   nrRed++;
                        else                    nrBlue++;
                        break;
                    case 1 :
                        if (rowRed < rowBlue)   nrRed--;
                        else                    nrBlue--;
                        break;
                    case 2 :
                        if (colRed > colBlue)   ncRed++;
                        else                    ncBlue++;
                        break;
                    case 3 :
                        if (colRed < colBlue)   ncRed--;
                        else                    ncBlue--;
                }
            }
            f(nrRed, ncRed, nrBlue, ncBlue, cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visitedCounter = new int[N][M][N][M];
        map = new char[N][M];

        int rowRed = 0, colRed = 0, rowBlue = 0, colBlue = 0;

        String str = null;
        for(int i = 0 ; i < N ; i++){
            str = br.readLine();
            for(int j = 0; j < M ; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'R') {
                    rowRed = i;
                    colRed = j;
                    map[i][j] = '.';
                } else if(map[i][j] == 'B') {
                    rowBlue = i;
                    colBlue = j;
                    map[i][j] = '.';
                }
            }
        }
        f(rowRed, colRed, rowBlue, colBlue, 0);
        System.out.println(Ans == Integer.MAX_VALUE ? -1 : Ans);
    }
}
```