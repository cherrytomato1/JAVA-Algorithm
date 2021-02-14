## 문제 정의

1. N * M ( 8 ≤ N,M ≤ 50) 크기의 W와 B로 이루어진 배열을 입력받는다.
2. 해당 배열에서 8 * 8  배열을 떼어냈을 때, 체스판의 배열과 가장 차이가 적은 배열의 다른 좌표의 수를 출력하라

## 문제 풀이

1. 먼저 체스판의 배열을 미리 만들어 놓는다.
2. 입력받은 n과 m의 크기에 맞게 배열을 만든다.
3. n - 8, m - 8 까지 반복하며 만들 수 있는 모든 체스판과 만들어 놓은 체스판을 비교한다.
4. 각각 cnt1과 cnt2로 W부터 시작되는 체스판과 B부터 시작되는 체스판을 비교하여 더 낮은 값을 가진 체스판을 비교한 값으로 최소값을 구한다.

## 코드

```java
static final boolean[][] CHESS_MAP =
{
    {true, false, true, false, true, false, true, false},
    {false, true, false, true, false, true, false, true},
    {true, false, true, false, true, false, true, false},
    {false, true, false, true, false, true, false, true},
    {true, false, true, false, true, false, true, false},
    {false, true, false, true, false, true, false, true},
    {true, false, true, false, true, false, true, false},
    {false, true, false, true, false, true, false, true},
};
```

- boolean 배열로 체스판을 선언하여 미리 비교할 체스판을 저장해둔다.

```java
for(int r = 0 ; r < N ; r++){
    String str = br.readLine();
    for(int c = 0 ; c < M ; c++){
        arr[r][c] = str.charAt(c) == 'W';
    }
}
```

- 입력받은 값을 W면 true, B면 false로 boolean 배열에 저장한다.

```java
int res = Integer.MAX_VALUE;
for(int r = 0 ; r <= N - 8 ; r++){
    for(int c = 0 ; c <= M - 8 ; c++){
        int cnt = check(r, c);
        res = res < cnt ? res : cnt;
    }
}
return res;
```

- row는 0 부터 N - 8 까지, col은 0 부터 M - 8 까지의 좌표부터 시작하는 체스판을 만들 수 있으므로 해당 좌표의 체스판을 검사해 그 중 가장 작은 값을 갖는 값을 출력한다.

```java
static int check(int row, int col){
	  int cnt1 = 0;
	  int cnt2 = 0;
	
	  for(int r = row; r < row + 8 ; r++){
	      for(int c = col ; c < col + 8; c++){
	          if(arr[r][c] == CHESS_MAP[r - row][c - col])    cnt1++;
	          else                                            cnt2++;
	      }
	  }
	
	  return cnt1 < cnt2 ? cnt1 : cnt2;
}
```

- 각각의 시작 좌표에서 8*8 까지 미리 만들어둔 체스판과 비교한다.
- 원래의 체스판 모양과 같으면 W로 시작하는 체스판으로 만들 수 있고, 다르면 B로 시작하는 체스판으로 만들 수 있으므로 각각의 체스판에서 바꿔야하는 수를 센다.
- 둘 중 작은 값을 반환한다.

## 후기

 처음엔 체스판이 W로 시작하는 하나의 종류만 만들 수 있는 줄 알고 문제를 풀었다가 실패했다. 이 후 시작하는 좌표에 따라서 반대의 체스판을 검사하는 것으로 로직을 바꾸었지만 시작하는 좌표와 만들어질 수 있는 체스판은 관련이 없으므로 이 역시 틀린 풀이였다.

이번 주의 세 문제 중 가장 난이도가 낮은 문제였지만 푸는데 가장 오랜 시간이 걸렸다. 다음부터는 문제의 조건을 더 정확히 해석해야겠다.