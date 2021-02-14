## 문제 정의

1. 기타에는 줄과 각 줄에 프렛이 존재하며 줄은 6개, 프렛은 입력 P(2 ≤ P ≤ 300,000)이 존재한다.
2. 손가락을 튕길 때 각 줄에 있는 프렛 중 가장 높은 프렛의 음이 출력된다. 
3. N개 열에 멜로디를 출력할 줄과 프렛을 입력받는다.
4. 각 입력에 맞는 멜로디를 출력하기 위해 최소의 손가락 움직임 수를 출력한다.

## 문제 풀이

1. 각 줄 별로 스택을 선언하여, 명령이 들어올 경우 해당 열의 스택의 탑과 비교하여 탑이 더 높을 경우 팝한다.
2. 팝할 때마다 손가락이 움직인 회수를 카운트하며 스택이 비었거나, 탑이 더 낮을 경우에 해당 명령을 스택에 푸쉬하면서 카운트를 하나 늘린다.
3. fret과 top 이 같다면 카운트하지 않고 continue
4. 모든 명령이 끝나면 카운트를 출력

## 코드

```java
static Deque<Integer>[] dq = new ArrayDeque[6];
for(int i = 0 ; i < 6; i ++)    dq[i] = new ArrayDeque<>();
```

- 각 줄에 해당하는 스택을 만든다.

```java
loop1 :
for(int i = 0 ; i < N ; i ++){
    st = new StringTokenizer(br.readLine());
    int line = Integer.parseInt(st.nextToken()) - 1;
    int fret = Integer.parseInt(st.nextToken());
    while(!dq[line].isEmpty() && dq[line].peek() >= fret){
        if(fret == dq[line].pop())  {
            dq[line].push(fret);
            continue loop1;
        }
        cnt++;
    }
```

- 각 line과 fret을 입력으로 받는다
- line 에 해당하는 스택의 탑과 fret을 비교하여 탑이 크다면 pop 하고 cnt를 ++ 한다.
- 같다면 cnt를 세지 않고 continue

```java
dq[line].push(fret);
    cnt++;
```

- 스택의 탑이 fret보다 작다면 현재 fret 을 푸시하고 손가락 움직인 회수를 세준다