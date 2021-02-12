## 문제 정의

1. 초기에 N( 1≤ N ≤ 500,000) 이 주어지고 다음 열에 N 개의 각각의 탑 높이 T(1 ≤ T ≤ 100,000,000)이 주어진다.
2. 각각의 탑은 왼쪽으로 레이저를 쏴 자신과 높이가 같거나 큰 탑을 만나면 레이저가 맞는다.
3. 각각의 탑이 레이저에 맞는 수를 입력된 탑의 순서대로 출력

## 설계

1. N 만큼의 배열 할당 및 각각 인덱스에 탑의 높이 입력
2. 맨 끝의 탑으로부터 반복하여  높이 스택의 top을 검사하여 top이 탑보다 작거나 같다면 pop 하고 해당하는 index도 pop하여 정답 배열에 저장
3. 2번 과정을 이 과정을 스택이 비거나, top이 탑보다 클 때까지 반복한다. 반복이 끝나면 해당 탑의 높이를 스택에 push 하고 인덱스도 다른 스택에 push
4. 모든 탑을 검사한 후 cnt 값을 차례로 출력한다.

## 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] tower;
    static int[] ans;
    static Deque<Integer> height = new ArrayDeque<>();
    static Deque<Integer> index = new ArrayDeque<>();

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        tower = new int[N];
        ans = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            tower[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        for(int i = N - 1 ; i >= 0 ; i--){
            while(!height.isEmpty() && height.getFirst() <= tower[i]){
                ans[index.pop()] = i + 1;
                height.pop();
            }
            height.push(tower[i]);
            index.push(i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i++){
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
```