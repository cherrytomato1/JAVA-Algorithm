## 문제 정의

1. 스위치 개수 N이 주어지고 다음 열에 해당하는 N개의 0 또는 1의 스위치가 주어진다
2. 학생의수 M이 주어지고 다음 M 개 열에 학생의 성별과 스위치가 주어진다
3. 남학생일 경우 주어진 스위치의 배수인 모든 스위치의 상태를 바꾼다
4. 여학생의 경우 주어진 스위치의 양쪽을 검사해서 대칭인만큼 스위치의 상태를 바꾼다. 대칭이 없다면 주어진 스위치만 변경된다.

## 코드

```java
package boj0017_210201_1244_s4;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static boolean[] arr;
    static StringTokenizer st;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void solve() throws IOException{
        M = Integer.parseInt(br.readLine());

        for( int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int gen = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            for(int j = num ; gen == 1 && j <= N ; j += num){
                arr[j] = !arr[j];
            }
            for(int j = num, k = num ; gen == 2 && j <= N && k > 0 ; j++, k--){
                if(arr[j] == arr[k]) arr[j] = arr[k] = !arr[j];
                else break;
            }
        }
        for(int i = 1 ; i <= N; i++){
            System.out.print((arr[i] ? 1 : 0) + " ");
            if(i % 20 == 0) System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i <= N ; i++){
            arr[i] = st.nextToken().equals("1");
        }
        solve();
    }
}
```

## 후기

이런 간단한 문제를 푸는데 3시간이나 걸렸다,.. 다시말하면 디버깅하는데 2시간 50분이 걸렸다... 알고리즘을 짜고 구현하는데는 10분 남짓 소모되었지만 저,... 입력을 받을 때 남자 여자 하나라고 생각한 것의 연장선이었는지, 설정할 인덱스를 받는 부분에서도 한글자만 받아버렸다.. 그부분이 틀린지 모르고 2시간 40분동안 다른곳만 디버깅했다..