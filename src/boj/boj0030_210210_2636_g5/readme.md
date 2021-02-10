## 문제 정의

1. 맵의 크기 r, c 입력 (1≤ r, c ≤) 및 치즈 배열 입력 (0 = 공기, 1 = 치즈)
2. 공기와 4방 기준으로 맞닿아 있는 치즈는 한시간 뒤 녹게된다.
3. 치즈가 모두 녹는데 걸리는 시간과 모두 녹기 한시간 전 남아있는 치즈의 수를 출력하라

## 풀이

1. 입력을 받으면서 치즈의 개수를 센다. 모든 맵의 가장자리와 맞닿아 있는 공기의 좌표를 큐에 넣는다. 
2. 큐로 탐색 중 치즈를 만나게 된다면 치즈를 지우고 치즈 개수를 하나 뺀다. 그리고 지워진 치즈의 좌표를 또다른 새로운 큐에 넣는다.
3. 기존 큐가 완전히 팝되면 시간을 하나 더한다. 그리고 새로운 큐에 있는 값들을 다시 원래 큐에 넣는다. 이후 다시 2 반복
4. 치즈의 개수가 0이 될 경우 종료하고 마지막으로 큐에 저장된 값의 수(사이즈)를 출력

## 코드

```java
for(int r = 0 ; r < R ; r++){
    st = new StringTokenizer(br.readLine());
    for(int c = 0 ; c < C ; c++){
        if(map[r][c] = st.nextToken().equals("1")) cheese++;
        else if(r == 0 || c == 0) q.offer(new int[]{r, c});
    }
}
```

1. 초기 입력 때 치즈일 경우 배열에 true, 아니면 false, 그리고 가장자리에 있는 공기일 경우 해당 좌표를 큐로 넣는다(시작 좌표)

```java
	int times = 0;
	  while(cheese != 0){
	      times++;
	      while(!q.isEmpty()){
								int pos[] = q.poll();
                for(int i = 0 ; i < 4 ; i ++){
                    int nr = pos[0] + dr[i];
                    int nc = pos[1] + dc[i];

                    if(nr >= R || nr < 0 || nc >= C || nc < 0 || visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    if(map[nr][nc]){
                        q2.offer(new int[]{nr, nc});
                        cheese--;
                        continue;
                    }
                    q.offer(new int[]{nr, nc});
                }
            }
            while(!q2.isEmpty())    q.offer(q2.poll());
        }
		}
return times;
```

 2.  매 한시간 동안 표면에 있는 치즈만 제거하므로, 한번의 탐색에서 치즈를 없앤 경우 해당좌표를 다음 한시간 동안 탐색할 큐에 넣어, 한번에 한개의 표면 치즈만 제거할 수 있도록 한다.

남은 치즈가 0개가 되면 소요된 시간을 리턴한다.

```java
sb.append(f()).append("\n").append(q.size());
System.out.println(sb);
```

 3.  소요된 시간과 현재 큐에 남아있는 값의 수, 즉 마지막으로 제거된 치즈의 위치들이 저장된 큐의 사이즈를 출력한다.

### 전체 코드

```java
import java.util.*;
import java.io.*;

public class boj_2636 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int R;
    static int C;
    static int cheese;
    static boolean[][] map;
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static Queue<int[]> q2 = new LinkedList<>();

    static int f(){
        int times = 0;
        while(cheese != 0){
            times++;
            while(!q.isEmpty()){
                int pos[] = q.poll();
                for(int i = 0 ; i < 4 ; i ++){
                    int nr = pos[0] + dr[i];
                    int nc = pos[1] + dc[i];

                    if(nr >= R || nr < 0 || nc >= C || nc < 0 || visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    if(map[nr][nc]){
                        q2.offer(new int[]{nr, nc});
                        cheese--;
                        continue;
                    }
                    q.offer(new int[]{nr, nc});
                }
            }
            while(!q2.isEmpty())    q.offer(q2.poll());
        }
        return times;
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new boolean[R][C];
        visited = new boolean[R][C];

        for(int r = 0 ; r < R ; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < C ; c++){
                if(map[r][c] = st.nextToken().equals("1")) cheese++;
                else if(r == 0 || c == 0) q.offer(new int[]{r, c});
            }
        }
        sb.append(f()).append("\n").append(q.size());
        System.out.println(sb);
    }
}
```