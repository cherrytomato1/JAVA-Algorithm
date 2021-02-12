#백준 1182 부분수열의 합

## 문제 정의

1. 첫째 줄에 N(1 ≤ N ≤ 20)과 S(|S| ≤ 1,000,000) 입력
2. 다음 줄에 N 개의 정수 수열 입력, 그 합이 S 가 되는 부분집합의 수를 구하라.

## 문제 풀이

1. N개 데이터를 받는 정수 배열 선언
2. Subset을 구하는 재귀 알고리즘을 구현한다.
    - 서브셋은 해당 부분수열의 값을 사용했나 안했나로 분기하는 재귀로 구현한다.
    - 여기서는 부분수열의 내용을 출력할 필요가 없으므로 checked 배열 또는 비트마스킹을 사용하지 않고 합만 계산한다.
3. 모든 수열을 검사했을 때를 기저조건으로 설정
    - 모든 수열을 검사했고, S와 같을 경우 count ++;
    - 모든 수열을 검사했으면 리턴 S의 값과 관련없이 return;
4. S가 0일 경우와 S가 sum과 같더라도 뒤의 경우의 수를 더 살펴봐야한다.

## 코드

```java
static void subset(int idx, int sum){
		//... 기저조건
		subset(idx + 1, sum + arr[idx]);
		subset(idx + 1, sum);
}
```

- 부분 수열의 합을 구할 때 부분수열의 내용을 알필요는 없으므로 비트마스킹으로 체크하지 않는다.
- 수열의 해당 인덱스를 선택했는지, 안했는지를 구분하여 sum에 해당 인덱스를 더하거나 안 더한 값을 재귀로 호출한다.

```java
if(idx == N) {
		if (sum == S) cnt++;
		return;
}
```

- 기저조건을 idx가 N이 될 때로 설정한다. S의 값에 음수가 더해지거나, 양수가 더해질 수 있으므로 S의 값의 크기로 기저조건을 설정할 수 없다.
- 또한 중간에 S의 값을 가진 부분수열을 찾았더라도 해당 부분수열을 포함한 다른 부분수열도 S의 값을 가질 수 있으므로 기저조건으로 설정할 수 없다.
- 따라서 모든 부분수열을 검사 후에 해당 값이 S에 해당하는지에 따라 cnt 수를 세야 한다.

```java
if(S == 0) cnt--;
```

- S가 0일 경우 아무것도 선택하지 않은 공집합 또한 답이 될 수 있으므로 공집합을 빼준다.

### 전체 코드

```java
import java.io.*;
import java.util.*;

public class boj_1182_부분수열의합 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int S;
    static int cnt = 0;
    static int[] arr;

    static void subset(int idx, int sum){
        if(idx == N) {
            if (sum == S) cnt++;
            return;
        }

        subset(idx + 1, sum + arr[idx]);
        subset(idx + 1, sum);
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        subset(0, 0);
        if(S == 0) cnt--;
        System.out.println(cnt);
    }
}
```