## 문제 정의

1. 각각의 추를 왼쪽 혹은 오른쪽에 올린다. 이 때 왼쪽의 무게보다 오른쪽의 무게가 더 높을 순 없다.
2. 조건에 만족하는 추를 놓는 경우의 수를 출력하라

## 문제 풀이

### 순열과 백트래킹

1. 무게 추 N 개에 대한 순열을 재귀로 만든다.
2. 다음 재귀를 부를 때 왼쪽 저울에 놓는 것과 오른쪽 저울에 놓는 것 두 가지로 분기한다.
3. 각 재귀에서 왼쪽 무게 < 오른쪽 무게일 경우 가지치기
4. 모든 추를 놓기에 성공한다면 가짓수 + 1
5. 남은 추의 무게 합 <= 왼쪽 추 무게합 - 오른쪽 추 무게합일 때에 대한 기저조건을 추가한다.
6. 1번 조건을 만족할 경우 남은 추를 왼쪽에 넣거나 오른쪽에 넣는 것과 상관 없이 넣을 수 있으므로 남은 추를 넣는 경우의 수를 합해주면 된다.
7. 결과 값 + 남은추 ! * 2^남은추

## 코드

```java
static void dfs(int idx, int left, int right, int flag){
    if(idx == N){
        res++;
        return ;
    }

    if(sum - left <= left) {
        int temp = 1;
        for(int i = 1; i <= N - idx ; i++){
            temp *= i;
        }
        res += temp * Math.pow(2, N - idx);;
        return ;
    }

    for(int i = 0 ; i < N ; i++){
        if((flag & 1 << i) != 0)    continue;
        dfs(idx + 1, left + chu[i], right, flag | 1 << i);
        if(left >= right + chu[i])    dfs(idx + 1, left, right + chu[i], flag | 1 << i);
    }
}
```

- 입력 배열에 대한 순열을 정의할 때, 왼쪽 저울에 놓는 것과 오른쪽 저울에 놓는 것 두 가지로 분기하여 각각 그에 맞는 무게를 더하여 재귀 메서드를 호출합니다.
- 이 때 right > left는 할 수 없으므로 호출 조건에 추가합니다.
- 또한 남은 저울 - 왼쪽 저울이 현재 왼쪽 저울 값보다 작다면 더이상 탐색하지 않습니다. 대신 그 경우의 수를 직접 res 에 더해줍니다.
- idx == N 일 경우 재귀를 종료합니다.