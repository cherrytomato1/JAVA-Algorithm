## 문제 정의

1. 입력 N을 받고 N 크기의 덱 선언 및 N의 값 배열에 저장하기(풍선 인덱스 별로 갖고있는 숫자!), 각 배열의 인덱스에 해당하는 값을 갖고 있는 풍선 덱에 넣기
2. 덱의 첫번째 엘리먼트를 현재 위치로 보고 시작 시 터트린다. 터트린 덱의 엘리먼트에 해당하는 배열의 인덱스가 갖고있는 값 만큼 이동시킨다.
3. 양수일 경우 앞부터 해당 위치에 도달할 때까지 pop 하고 뒤에 다시 삽입한다. 이때 첫번째 풍선을 터트리는 순간 한칸씩 왼쪽으로 밀리기 때문에 움직이는 횟수를 하나 줄인다.
4. 음수일 경우 뒤에서 pop하고 다시 앞으로 넣는다.
5. 도착할 경우 pop하고 출력, 그 값만큼 2번으로 반복한다.
6. 덱이 비었을 경우 종료.

## 코드

```java
static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

static Deque<Integer> dq = new ArrayDeque<>();
static int N;
static int[] M;
static StringTokenizer st;
```

- 이번에는 BufferedReader와 Deque 증 ArrayDeque를 써보았다.
- BufferedReader는 인자로 Reader Class를 받고 있다 버퍼형식으로 읽기 때문에 IO호출이 적다. 이 때 매개변수로 받는 리더를 리더로 쓰는 대신 버퍼로 한번에 많이 출력한다.

```java
N = Integer.parseInt(br.readLine());

M = new int[N + 1];
st = new StringTokenizer(br.readLine());
for(int i = 1 ; i <= N ; i++){
    M[i] = Integer.parseInt(st.nextToken());
    dq.add(i);
}
```

- 배열에는 풍선이 터질 때 나오는 번호를 저장하고 덱은 각 풍선의 번호를 저장

```java
        System.out.print((num = dq.removeFirst()) + " ");
if(dq.isEmpty()){
    break;
}
```

- 맨 앞( 0 번 인덱스)에 위치한 풍선을 터트리고 그 풍선의 번호를 출력한다. 그 후 풍선이 모두 터졌다면 반복문을 빠져나온다.

```java
if(M[num] < 0){
    while(M[num]++ != 0){
        dq.addFirst(dq.removeLast());
    }
} else if(M[num]-- > 0){
    while(M[num]-- != 0){
        dq.addLast(dq.removeFirst());
    }
}
```

- 풍선이 음수면 왼쪽으로 이동, 양수면 오른쪽으로 이동
- 이동은 인덱스 자체는 첫 번째자리로 보고, 덱의 위치를 직접 움직인다.
    - 방향이 왼쪽일 경우 맨 뒤의 수를 앞으로 옮겨온다.
    - 방향이 오른쪽일 경우 맨 앞의 수를 뒤로 옮겨온다.
- 이 때 오른쪽 이동의 경우 풍선의 맨 앞자리를 터트리면서 한 칸씩 땡겨주기 때문에 이동 회수를 하나 빼준다.