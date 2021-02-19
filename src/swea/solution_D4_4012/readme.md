## 문제 정의

1. 각각의 재료에 대한 시너지 값이 인접행렬로 주어진다.
2. 식재료를 반으로 나누어 두개의 요리를 할 때, 각각의 요리의 맛의 차이가 최소가 되는 조합을 구하여라.

## 문제 풀이

1. N에서 N/2만큼 선택하는 조합을 생성한다. - 재귀 혹은 Next permutation
2. 조합이 생성되면 행렬에서 선택된 조합에 따른 값들의 합을 계산한다.

    2 - 1. 조합으로 생성된 2개의 식재료 그룹에서 각각의 인접행렬에 대한 2중반복으로 맛의 합을 구한다.

    2 - 2. 두 그룹의 맛 값 차이의 절대값을 구한다.

3. 구해진 값들 중 가장 낮은 값을 결과로 출력한다.

## 코드

```java
static int solve(){
    int res = Integer.MAX_VALUE;
    do{
        int val = check();
        res = res < val ? res : val;
    } while(np());
    return res;
}
```

- np와 flag 배열을 통해 조합을 만들어줍니다. 그리고 만들어진 조합이 갖는 결과값 중에 가장 작은 값을 답으로 반환합니다

```java
static int check(){
    int food1Val = 0;
    int food2Val = 0;
    for(int i = 0, j = 0, k = 0; i < N; i++){
        if(flag[i] == 1)    foods[1][j++] = i;
        else                foods[0][k++] = i;
    }
    for(int i = 0 ; i < N/2; i++){
        for(int j = 0 ; j < N/2 ; j++){
            food1Val += arr[foods[1][i]][foods[1][j]];
            food2Val += arr[foods[0][i]][foods[0][j]];
        }
    }
    return Math.abs(food1Val - food2Val);
}
```

- 만들어진 조합과 포함되지 않은 재료의 조합의 재료 인덱스 값을 각각 배열에 담아줍니다.
- 담아진 인덱스값을 통해 인접행렬 탐색을 실시해서 각 음식의 맛을 계산합니다. 그 후 두 맛의 차이의 절댓값을 반환합니다.