## 문제 정의

1. 처음에 각 t에 맵의 크기 w(col)와 h(row) 가 주어진다
2. h * w 크기의 맵이 1과 0으로 주어진다. 1은 육지, 0 은 바다이다.
3. 각 육지가 8방 기준으로 이어져있다고 하면 하나의 섬이라고 본다.
4. 각 테스트 케이스에 섬의 개수를 출력한다. 맵의 크기로 0 0이 입력되면 종료한다.

## 설계

1. 맵을 탐색하면서 땅인 구간이면서 한번도 방문하지 않는 공간을 찾는다.
2. 방문한 공간으로 설정하고, 그 맵 주변을 8방 탐색하면서 땅이 있다면, 그 섬을 기준으로 다시 8방탐색한다. 위 과정이 완전히 끝날 때까지 반복하며 끝난 경우 섬의 개수 1++
- bfs
    - 큐를 선언하여 8방 확인을 진행하면서 다른 미방문 육지가 있다면 큐에 넣어 반복하여, 큐가 빌 때까지 반복한다. (BFS) 방문 체크는 무조건 큐에 넣을 때 해야함, 아니면 큐의 다른 엘리먼트에서도 중복적으로 방문하게 됨.
- dfs
    - 8방 탐색시 미방문 육지가 있다면 재귀적으로 8방탐색을 다시 실행한다. 재귀의 시작시 방문체크

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class myProblem.Elevator {
    static int[][] map;
    static int row;
    static int col;
    static Queue<Integer[]> bfsQ = new LinkedList<>();

    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int dfs(int r, int c){
        map[r][c] = -1;
        int nr;
        int nc;
        for(int i = 0 ; i < 8; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if (nr >= row || nr < 0 || nc >= col || nc < 0) {
                continue;
            }

            /** 주변 육지 찾음 */
            if (map[nr][nc] == 1) {
                dfs(nr, nc);
            }
        }

        return 1;
    }

    static int bfs(int r, int c){
        Integer [] pos;
        int nr;
        int nc;
        bfsQ.add(new Integer[]{r, c});
        map[r][c] = -1;

        while(!bfsQ.isEmpty()) {
            pos = bfsQ.poll();
            r = pos[0];
            c = pos[1];

            for (int i = 0; i < 8; i++) {
                nr = r + dr[i];
                nc = c + dc[i];
                if (nr >= row || nr < 0 || nc >= col || nc < 0) {
                    continue;
                }
                if(map[nr][nc] == 1){
                    map[nr][nc] = -1;
                    bfsQ.offer(new Integer[]{nr, nc});
                }
            }
        }
        return 1;
    }

    static Boolean input() throws IOException {
        String[] size = br.readLine().split(" ");
        col = Integer.parseInt(size[0]);
        row = Integer.parseInt(size[1]);

        if(col == 0 && row == 0) return false;
        map = new int[row][col];

        StringTokenizer st;
        for(int r = 0 ; r < row; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < col ; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        return true;
    }

    static int solve(){
        int count = 0;

        for(int r = 0 ; r < row ; r ++){
            for(int c = 0 ; c < col ; c++){
                if( map[r][c] == 1){
//                    count += dfs(r, c);
                    count += bfs(r,c);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        while(input()) {
            System.out.println(solve());
        }
    }
}
```

## 후기

처음에 dfs와 bfs 대해 전혀 모르고 나만의 알고리즘을 만들어서 풀어보려했다. 여러 코드를 작성해시도해봤지만 성공하지 못했고, 결국 다른 사람들이 어떻게 풀었는 지 확인해보았다.

---

### 틀린 설계

1. 맵을 완전탐색하면서 각 육지마다 8방 탐색을 실시한다.
2. 육지 주변에 다른 육지가 있을 경우를 찾는다. 섬의 번호가 이미 부여된 경우는 생략한다.

    A. 주변의 다른 육지가 없다면 섬의 개수를 ++ 하고 그 수를 섬의 번호로 현재 육지에 부여한다.

    B. 섬의 번호가 없는 다른 육지가 있다면, 섬의 개수를 ++하고 현재 육지와 해당 육지의 섬 번호를 섬의 개수와 같은 값으로 넣는다.

    C. 이미 섬의 번호가 부여된 다른 하나의 육지가 존재한다면 해당 육지의 섬 번호를 현재 육지의 섬번호에 부여한다.

    D. 다른 섬 번호를 가진 육지가 2개 이상이라면 현재 육지의 섬 번호는 다른 섬번호 중 가장 작은 값으로 놓고, 다른 섬 번호도 가장 작은 섬번호로 갖는다. 이때 다른 섬 번호의 개수만큼 섬의 개수를 뺀다.

3. 모두 탐색을 마치고 섬의 개수를 출력한다.

---

나는 방문체크 배열을 새로 선언하는 것의 필요성이나 dfs, bfs에 대해 전혀 알지 못했다. 하지만 이 섬 문제를 풀기 위해서는 재귀적으로 육지 주변의 다른 육지까지 모두 찾아내어서, 같은 섬인 친구들을 탐색에서 제외해야했기에 꼭 필요한 과정이었다. 

또한 bfs로도 풀어보면서 한번의 반복에서 방문해야할 모든 섬을 큐에 넣을 때 방문체크를 탐색을 시작할 때가 아닌, 탐색 큐에 넣을 때 해야하는 점도 알았다. 그렇지 않으면 이미 큐에 있는 좌표지만, 체크가 되어있지 않기 때문에 다른 좌표에서 그 좌표를 탐색할 때 다시 큐에 넣고, 이 과정이 반복되며 메모리 혹은 시간초과가 발생하는 것이다.