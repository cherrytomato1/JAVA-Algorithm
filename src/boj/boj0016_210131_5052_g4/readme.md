## 문제 정의

1. 입력 n에 대하여 {0},{1},{2}, ... , {n} 이 n+1 개의 집합을 이룸 
2. 입력 0 a b, 혹은 1 a b. 0 a b 는 a 가 속한 집합과 b가 속한 집합을 합집합으로 묶는 연산, 1 a b 는 두 원소가 같은 집합인지 YES/NO 로 리턴

## 입출력

1. 초기 입력 n(1≤ n ≤ 1,000,000) m( 1≤ m ≤ 100,000) 
2. 다음 m개의 0 a b 혹은 1 a b : 1≤ a,b ≤ n
3. 1 a b 에 대하여 yes/no 출력

## 문제 풀이

1. n + 1 개의 배열을 선언합니다. 각 배열의 인덱스는 각 정점의 부모를 가르킵니다. 그 후 각 인덱스가 자신을 가르키도록 합니다. 
2. 합집합 연산이 일어날 때마다 더 작은 인덱스의 부모를 더 큰 인덱스의 부모에 일치시킵니다. 둘다 부모를 찾을 때까지 이동하면서 합칩니다. 그 후 더 큰 인덱스의 부모를 더 작은 인덱스의 값으로 합니다.
3. 같은 집합인지 묻는 입력에는 두 인덱스의 부모를 따라 자기 자신의 부모가 자기를 가르킬 때까지 이동합니다. 둘 다 부모를 찾았을 때 두 부모가 같은 값이면 YES 아니면 NO 출력합니다.

## 코드

```java
arr = new int[N + 1];
for(int i = 0 ; i <= N ; i++){
    arr[i] = i;
}
```

- 초기에 모든 정점의 부모는 자기 자신입니다.

```java
static void merge(int idx1, int idx2){
    if(idx1 > idx2) {
        int temp = idx1;
        idx1 = idx2;
        idx2 = temp;
    }
    idx1 = searchParent(idx1);
    idx2 = searchParent(idx2);

    if(idx1 != idx2)    arr[idx2] = idx1;
}
```

- 합연산 시 인덱스를 정렬하고, 두 정점의 부모를 찾습니다. 같지 않다면 더 작은 인덱스의 값을 큰 정점에 넣습니다.

```java
static int searchParent(int idx){
    if(idx == arr[idx])     return idx;
    else                    return arr[idx] = searchParent(arr[idx]);
}
```

- 해당 정점이 자신이 부모가 아니라면(루트가 아니라면) 해당 정점은 자신의 부모의 부모 값을 갖습니다. 재귀적으로 루트를 만날 때까지 반복합니다.

```java
if(searchParent(idx1) == searchParent(idx2))    sb.append("YES").append("\n");
else                                            sb.append("NO").append("\n");
```

- 두 정점의 부모가 같다면 YES 아니면 NO를 출력합니다

## 후기

처음엔 반복문으로 부모찾기를 구현하려 했으나 곧 시간초과를 확인했고, DP 형식으로 부모를 찾을 때마다 해당 정점의 부모를 초기화 해주는 과정이 필요하단 것을 알았습니다. 유니온 파인드(disjointset) 알고리즘이 정말 직관적이고 재밌다고 느꼈습니다.