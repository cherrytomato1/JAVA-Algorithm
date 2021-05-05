## 문제 정의

1. `a` 와 `z` 로만 이루어진 사전순으로 정렬된 문자열 배열에서 `K` 번째 문자열을 출력합니다. 해당하는 문자열이 없다면 `-1` 을 출력합니다.
2. 모든 문자열에서 `a` 의 수는 반드시 `N` (1 ≤ N ≤ 100) 개 이며 `z` 의 수는 `M` (1 ≤ M ≤ 100)개입니다

## 문제 풀이

### 동적 테이블 만들기

1. `N` 개의 `a` 와 `M` 개의 `z` 로 만들 수 있는 경우의 수는 `(N + M) Co M` 개 입니다.
2. 조합은 동적 테이블로 계산하여 구할 수 있으며 점화식은 다음과 같습니다
    - `D[N][M] = D[N][M - 1] + D[N - 1][M]`
    - 기저조건 : `D[a][0] == D[0][b] == 1` ( 1 ≤ a ≤ N, 1 ≤ b ≤ M)
3. 테이블 구성 후 `D[N][M] < K` 라면 -1을 출력합니다.
4. 이 때 `N == 100` , `M == 100` 등 아주 큰 입력이 들어오면 테이블의 값에 오버플로우가 발생할 수 있으므로 `K` 를 초과하는 값은 `K + 1` 로 설정합니다.

```java
a\z |	0 | 1 | 2 | 3 | 4
------------------------
	0	| 0 | 1 | 1 | 1 | 1
------------------------
	1 | 1 | 2 | 3 | 4 | 5 
------------------------
	2 | 1 | 3 | 6 | 10| 15
------------------------
	3 | 1 | 4 | 10| 20| 35
------------------------
	4 | 1 | 5 | 15| 35| 70
```

### 문자열 만들기

1. `D[N][M]` 부터 `K` 와 값을 비교하면서 문자열을 만듭니다.
2. `D[N - 1][M]` ≥ `K` 라면 `a` 를 앞에 넣고 `D[N - 1][M]` 테이블로 탐색을 계속합니다.
    - `D[N - 1][M]` 이 `a` 로 시작하는 경우의 수이므로 `K` 보다 크다면 `a` 를 붙여야 합니다.
3. 아니라면 `z` 로 시작해야하는 경우이므로 `z` 를 붙이고 `D[N][M - 1]` 에서 탐색을 계속합니다.
    - 이 때는 다음에 `a` 를 붙여야하는 값을 계산하기 위해 앞에 `a` 가 붙는 모든 경우의 수인 `D[N - 1][M]` 을 `K` 에서 빼주어 다음탐색에 넘깁니다.
4. `a` 혹은 `z` 가 0개가 된다면 남은 자리를 모두 `0` 개가 아닌 문자로 채웁니다.