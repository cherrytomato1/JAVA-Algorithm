## 문제 정의

1. 오목판이 주어졌을 때 이긴 색과 이겼을 때 가장 왼쪽에서 가장 위에 있는 돌의 좌표를 출력합니다

## 문제 풀이

1. 각각의 돌로부터 dfs 탐색을 하되, 진행 방향은 우상, 우, 하, 우하이다. 이  때 재귀에서는 한번 정해진 방향으로만 탐색하고 돌을 찾았을 때 4방을 탐색한다.
2. 각 dfs의 depth가 5일 때, 6번째 돌이 같은 돌이 아니라면 오목으로 판단하고 재귀를 끝낸다. 이 때 재귀를 불러온 자리에서 좌표의 값을 설정해준다.
3. 이 때 6목 검사를 위해 검사 반대방향 돌이 같을 경우 바로 반환해줍니다..

## 코드

```java
package jungol.jungol_1733_오목;

import java.io.*;
import java.util.*;

public class Main_1733_오목 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 19;
    static final int[][] DIR = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}};

    static char[][] map = new char[MAX][MAX];

    static void init() throws IOException{
        for(int r = 0 ; r < MAX; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < MAX ; c++){
                map[r][c] = st.nextToken().charAt(0);
            }
        }
    }

    static int[] res;
    static int solve(){
        for(int r = 0 ; r < MAX ;r++){
            for(int c = 0 ; c < MAX ; c++){
                if(map[r][c] == '0')    continue;
                for(int i = 0 ; i < 4; i++){
                    if(!dfs(r, c, i, 0, map[r][c]))             continue;

                    res = new int[]{r + 1, c + 1};
                    return map[r][c] - '0';
                }
            }
        }
        return -1;
    }

    static boolean dfs(int row, int col, int dir, int level, char type){
        if(level == 0 ){
            int pr = row - DIR[dir][0];
            int pc = col - DIR[dir][1];

            if(pr >= 0 && pr < MAX && pc >= 0 && map[pr][pc] == map[row][col])    {
                return false;
            }
        }
        if(map[row][col] != type)   return false;

        int nr = row + DIR[dir][0];
        int nc = col + DIR[dir][1];

        if(level == 4){
            if(nr >= MAX | nc >= MAX | nr < 0 )     return true;
            return map[nr][nc] != type;
        }
        if(nr >= MAX | nc >= MAX | nr < 0 )     return false;

        return dfs(nr, nc, dir, level + 1, type);
    }

    public static void main(String[] args) throws IOException{
        init();
        int i = solve();
        if(i == -1) {
            System.out.println(0);
            return;
        }
        sb.append(i).append("\n").append(res[0]).append(" ").append(res[1]);
        System.out.print(sb);
    }
}
```

## 후기

생각보다 예외처리 해야하는 부분이 너무 많았습니다. 육목을 검사하기 위해서는 검사하는 돌의 이전 돌까지 함께 검사해서 예외처리를 했어야합니다. 풀이에 2시간 정도 걸렸고 앞으로 더 많이 공부해야겠다고 느꼈습니다.