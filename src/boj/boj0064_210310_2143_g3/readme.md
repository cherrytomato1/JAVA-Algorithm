## 문제 정의

1. `T` (-1,000,000,000 ≤ T ≤ 1,000,000,000) 와 각각 `n` , `m` (1 ≤ n, m ≤ 1,000)개의 원소를 가진배열 `A` , `B` 가 주어집니다. `A` 와 `B` 의 각 원소는 절댓값 1,000,000 이하입니다.
2. `A` 와 `B` 의 원소를 최소한 한 개씩 사용하여 만든 부분배열의 합이 `T` 인 경우의 수를 출력합니다.

## 문제 풀이

1. `A` 와 `B` 배열 각각 부분합을 구합니다.
    1. 각 배열을 입력받으면서 받았던 값들을 모두 합해 부분합을 만듭니다.
    2. 만들어진 부분합은 `HashMap` 의 `Key` 로 저장하고, 중복되는 부분값들은 `HashMap` 의 `Value` 를 더해주면서 셉니다.

2. `A` 의 각 부분합을 반복하며 `T` - 해당 부분합을 `Key` 로 갖고 있는 `B` 의 부분합의 `value` 와 `A` 의 부분합의 `value` 를 곱해주면 해당 부분합을 갖는 배열들로 만들 수 있는 `T` 의 수가 됩니다.

## 코드

```java
for(int i = 0 ; i < N; i++){
    arrA[i] = Integer.parseInt(st.nextToken());
    for(int j = i, sum = 0; j >= 0 ; j--){
        sum += arrA[j];
        A.put(sum, A.containsKey(sum) ?  A.get(sum) + 1 : 1);
    }
}
```

- `A` 와 `B` 모두 값을 입력받으면서 동시에 `HashMap` 에 누적합에 대한 `Key` 와 `Value` 를 저장합니다.

```java
static long solve(){
    long ret = 0;
    for(Integer aKey : A.keySet()){
        int find = T - aKey;
        ret += B.containsKey(find) ? (long)A.get(aKey) * B.get(find) : 0;
    }
    return ret;
}
```

- `keySet()` 메서드로 `A` 의 부분합 `HashMap` 의 모든 `Key` 에 대해서 반복합니다.
- `T` - `aKey` 의 `Key` 를 갖는 `B` 의 `HashMap` 의 `value` 와 `aKey` 의 `value` 를 곱하여 결과에 합산합니다.

## 후기

 문제 풀이에 정말 오랜 시간이 걸렸습니다. 부분합을 구하는 알고리즘을 짜는데 많은 고민을 했고 또 해당 부분합의 수를 세어주어야하는 부분을 구상하는데도 많은 시간이 걸렸습니다. 일단 구현은 했는데 정확히 알고 구현한 느낌이 아니라서 다시 공부해야할 것 같습니다.

또 각 부분합의 `value` 가 `int` 범위를 초과하는 경우가 있어 그 부분에 대한 예외를 찾는 시간도 오래 걸렸습니다. 풀이하면서 아직 공부할 게 많다고 느끼는 문제였습니다.