## 문제 정의

1. 완전이진트리 꼴로 만들어진 땅 위에서 각각의 오리는 가고 싶어하는 땅이 있습니다. 하지만 다른 오리가 갖고 있는 땅은 지나갈 수 없습니다.
2. 땅의 수 N ( 2 ≤ N < 2^20), 오리의 수 Q (1 ≤ Q ≤ 200,000) 이 주어집니다. 다음 Q 개의 열에 오리가 가고 싶어하는 땅을 입력받습니다. 갈 수 있다면 0 , 못간다면 가로막는 첫 번째 땅을 출력합니다.

## 문제 풀이

1. 완전이진트리를 배열로 선언합니다.
2. 입력으로 받은 값 xi로부터 루트까지 xi>>1 하면서 탐색합니다.
3. 탐색을 하는 동안 이미 점유된 땅을 변수에 저장합니다.
4. 루트까지 변수에 저장된 값이 있으면 그 땅의 번호를 출력하고, 없다면 0을 출력하고 xi 노드에 점유되었음을 저장합니다.

## 코드

```java
tree = new int[N + 1];
for(int i = 0 ; i < Q ; i++){
    sb.append(solve(Integer.parseInt(br.readLine()))).append("\n");
}
```

- N의 크기만큼 트리 배열을 생성합니다.
- 각 정점에 대하여 탐색을 시작합니다. 그 후 반환값을 결과에 추가합니다.

```java
static int solve(int node){
	  int cnt = 0;
	  for(int i = node ; i != 1; i >>= 1){
	      if(tree[i] != 0) cnt = i;
	  }
	  if(cnt == 0)    tree[node] = 1;
	  return cnt;
}
```

- 입력 노드로부터 루트 노드에 도착할 때까지 부모 노드로 이동합니다. 이동 중 이미 점유된 노드를 발견하면 저장합니다.
- 점유된 노드가 없으면 입력 노드를 점유하고, 찾았다면 찾은 노드 중 가장 루트와 가까운 노드를 반환합니다.