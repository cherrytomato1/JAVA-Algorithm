## 문제 정의

1. 기타에는 줄과 각 줄에 프렛이 각각 줄은 6개, 프렛은 입력 P(2 ≤ P ≤ 300,000)이 있습니다.
2. 손가락을 튕길 때 각 줄에 있는 프렛 중 가장 높은 프렛의 음이 출력됩니다.
3. N개 열에 멜로디를 출력할 줄과 프렛을 입력 받습니다.
4. 각 입력에 맞는 멜로디를 출력하기 위해 최소의 손가락 움직임 수를 출력합니다.

## 문제 풀이

1. 각 줄 별로 스택을 선언하여, 명령이 들어올 경우 해당 줄의 스택의 탑과 비교하여 탑이 더 높을 경우 팝 합니다.
2. 팝할 때마다 손가락이 움직인 회수를 카운트하며 스택이 비었거나, 탑이 더 낮을 경우에 해당 명령을 스택에 푸쉬하면서 카운트를 하나 세줍니다.
3. fret과 top 이 같다면 카운트하지 않고 continue 합니다.
4. 모든 명령이 끝나면 카운트를 출력합니다.

## 코드

```java
static Deque<Integer>[] dq = new ArrayDeque[6];
for(int i = 0 ; i < 6; i ++)    dq[i] = new ArrayDeque<>();
```

- 각 줄에 해당하는 스택을 만듭니다.

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
		dq[line].push(fret);
    cnt++;
}
```

- N개의 line과 fret을 입력으로 받습니다.
- line에 해당하는 스택의 탑과 fret을 비교하여 탑이 크거나 같다면 팝하고 cnt를 셉니다. 이 과정을 탑이 더 작거나 스택이 비었을 때까지 반복합니다.
- 팝 한값이 fret과 같다면 cnt를 세지 않고 다시 스택에 담은 뒤continue 합니다
- 위 반복이 끝나면 현재 프렛을 스택에 넣고 cnt를 세줍니다.