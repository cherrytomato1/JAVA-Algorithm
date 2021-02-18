## 문제 정의

1. 집에서부터 출발하여 N(1 ≤ 10)의 경로를 탐색하고 집으로 돌아오는 경우 중 가장 짧은 거리를 가진 경로의 이동 거리를 출력해라
2. 이 때 각 좌표간 거리는 |r1 - r2| + |c1 - c2|로 구할 수 있다.

## 문제 풀이

### 백트래킹

1. 각 경로에서 재귀적으로 이동하여 거리를 합산하고 모든 경로를 탐색했을 때의 거리를 구한다.
2. 재귀 호출할 때 이미 방문한 좌표를 마스킹처리한다.
3. 1번 이상 거리를 구하여 최소 거리가 정해졌다면, 최소 거리를 초과하는 값은 모두 가지치기한다.

### 순열

1. next Permutation 알고리즘으로 방문하는 집의 순서에 대한 순열을 모두 만들어 낸다.
2. 이때 각각 원소의 비교를 위해 compareTo 메소드를 객체에 만들어준다.
3. 각각의 방문 순서에 맞는 거리를 합하고, 처음과 마지막에 집까지 방문하는 거리도 포함한다.
4. 가장 낮은 거리합을 결과를 출력한다.

## 코드

```java
package swea.solution_D5_1247;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int row;
    int col;

    public Node(int col, int row){
        this.row = row;
        this.col = col;
    }
    @Override
    public int compareTo(Node o1){
          return this.row - o1.row == 0 ? this.col - o1.col : this.row - o1.row;
    }
}

public class Solution_D5_최적경로 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static Node house;
    static Node work;
    static Node[] cust;
    static int res;
    
    static void init() throws IOException{
        res = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        cust = new Node[N];

        st = new StringTokenizer(br.readLine());
        work = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        house = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        for(int i = 0 ; i < N ; i++){
            cust[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }
    
    static void dfs(int idx, int sum, int flag, Node preNode){
        if(idx == N)    {
            res = Math.min(res, sum + getDist(preNode, house));
            return;
        }
        if(sum >= res)      return;

        for(int i = 0 ; i < N ; i++){
            if((flag & 1 << i) != 0)    continue;
            dfs(idx + 1, sum + getDist(preNode, cust[i]), flag | 1 << i, cust[i]);
        }
    }

    static void perm(){
        Arrays.sort(cust);
        do{
            int sum = getDist(work, cust[0]);
            for(int i = 1; i < N ; i++){
                sum += getDist(cust[i - 1], cust[i]);
            }
            res = Math.min(res, sum + getDist(cust[N - 1], house));
        }while(np());
    }

    static boolean np(){
        int lastIdx = N - 1;
        int i = lastIdx;

        while(i > 0 && cust[i - 1].compareTo(cust[i]) >= 0) i--;
        if(i == 0)  return false;

        int j = lastIdx;
        while(cust[i - 1].compareTo(cust[j]) >= 0) j--;
        custSwap(i - 1, j);

        int k = lastIdx;
        while(k > i){
            custSwap(k--, i++);
        }
        return true;
    }

    static void custSwap(int idx1, int idx2){
        Node temp = cust[idx1];
        cust[idx1] = cust[idx2];
        cust[idx2] = temp;
    }

    static int getDist(Node n1, Node n2){
        return Math.abs(n1.row - n2.row) + Math.abs(n1.col - n2.col);
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T ; tc++){

            sb.append("#").append(tc).append(" ");
            init();
//            dfs(0, 0, 0, work);
            perm();
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}
```

- dfs 백트래킹과 순열로 구현을 모두 해보았다

## 후기

문제 접근 자체는 어렵지 않았으나, 집과 회사와 고객의 집의 입력 순서를 여러번 잘못받아 풀이에 오랜 시간이 걸렸다. 또한 순열 생성을 위해 next permutation 알고리즘을 사용할 때, 객체 간의 크기 비교를 위해 compareTo를 구현했다. 이 때 row만으로 비교하면 두 값이 같다고 판단하므로 compareTo에서 col 원소까지 비교했다.

next permuation에서는 값 비교를 compareTo로 수행한다.