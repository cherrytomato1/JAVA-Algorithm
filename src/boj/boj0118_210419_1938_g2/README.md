## 문제 정의

1. `N` * `N` (1 ≤ N ≤ 50) 맵에 통나무 `B`, 목표지점 `E` , 길 `0` , 벽 `1` 이 주어집니다.
2. 3칸으로 이루어진 `B` 통나무를 목표지점 3칸 `E` 에 배치할 수 있는 최소 이동 횟수를 출력합니다.
3. 통나무는 상, 하, 좌, 우 이동 및 90도 회전만 가능합니다. 회전 시 중앙 좌표 인접 8칸에 경계나 벽이 존재해서는 안됩니다.

## 문제 정의

1. 통나무와 목표지점의 중앙 좌표 및 회전 여부를 객체에 담습니다. (가로방향 기준)
2. 회전 상태와 주위의 벽 및 경계에 대한 예외처리를 해서 `BFS` 탐색을 합니다.
3. 방문 배열을 `int` 로 정의해 좌표 및 방향별로 비트마스킹 방문 체크를 합니다.
4. 목표 지점에 도착하면 탐색을 종료합니다. 도착하지 못했는데 큐가 비었다면 `0` 을 출력합니다.

## 코드

### 좌표 받기

```java
//통나무와 목표지점 좌표 저장 및 생성
if (in[j] == 'B' && startCnt < 2) {
	tempStart[startCnt][ROW] = i;
	tempStart[startCnt][COL] = j;
	if(++startCnt == 2)   makeLog(tempStart, true);
} else if (in[j] == 'E' && targetCnt < 2) {
	tempTarget[targetCnt][ROW] = i;
	tempTarget[targetCnt][COL] = j;
	if(++targetCnt == 2)   makeLog(tempTarget, false);
}

private static void makeLog(int[][] currPos, boolean isStart) {
	//row가 다 같으면 가로
	boolean isTurned = currPos[0][ROW] == currPos[1][ROW];
	//가운데 좌표만 저장
	if(isStart)   start = new Log(currPos[1][ROW], currPos[1][COL], isTurned);
	else        target = new Log(currPos[1][ROW], currPos[1][COL], isTurned);
}
```

- 통나무 입력이 들어오면 시작위치 배열에 저장하고, 목적지 입력이 들어오면 목적지 배열에 저장합니다.
- 어떤식으로 통나무 및 도착위치가 도착하더라도 `1` 번 인덱스가 중앙이므로 `1` 번 인덱스까지만 입력을 받습니다.
- 두 좌표의 `row` 가 같다면 가로, 아니면 세로이므로 그에 맞게 초기 방향을 설정합니다.

### 탐색

```java
Log curr = q.poll();
for (int j = 0; j < 5 ; j++) {
	//방향에 맞게 이동시킴(방향 전환 포함)
	Log next = move(curr, j);
	//이동 못한경우
	if(next == null)        continue;
	//도착~!!!~!
	if(next.equals(target)) return ret;
	//방문체크
	visited[next.row][next.col] |= 1 << next.isTurnedToInteger();
	q.offer(next);
}
```

- 4방향 및 방향전환을 모두 반복문에서 수행합니다
- `move` 메서드에서 `null` 을 반환하면 이동 불가능한 경우고, 반환된 `next` 객체가 목적지와 일치한다면 종료합니다.
- 방문배열에 방향에 맞는 비트를 마스킹해서 방문처리한 후 큐에 다시 넣습니다.

### 움직이기

```java
private static Log move(Log curr, int dir) {
	//현재 통나무 위치 복사
	Log ret = new Log(curr.row, curr.col, curr.isTurned);
	//방향 돌리기
	if (dir == 4) {
		//방향바꾸기
		ret.isTurned = !ret.isTurned;
		//방향바꾸기 가능하면 반환
		if(!isTurnAble(ret))   return null;
		return ret;
	}
	//방향에 맞게 새로운 좌표 할당
	ret.row += DIR[ROW][dir];
	ret.col += DIR[COL][dir];
	//움직이기 가능한지 검사 및 가능하면 새로운좌표, 못하면 null
	if(!isMovable(ret, dir)) return null;
	return ret;
}
```

- `dir` 이 `4` 면 방향전환을 수행합니다. 방향값을 바꾸고 가능한지 `isTurnAble()` 메서드에서 검사합니다.
- 이동의 경우 방향배열을 참조하여 다음 좌표를 설정하여 가능한지 검사합니다.
- 두 경우 모두 가능하다면 새로운 좌표, 불가능하다면 `null` 을 반환합니다.

### 이동, 방향 돌리기 검사

```java
private static boolean isTurnAble(Log curr) {
	//방향돌리기 검사, 중앙 좌표로부터 인접 8칸 모두 검사
	for (int i = -1; i < 2 ; i++) {
		for (int j = -1; j < 2 ; j++) {
			int tr = curr.row + i;
			int tc = curr.col + j;
			//새로 할당한 좌표가 가능한지 검사, 중앙일 경우 방문체크 검사
			if(!isValid(new Log(tr, tc, curr.isTurned), i == 0 && j == 0)) return false;
		}
	}
	return true;
}

private static boolean isMovable(Log curr, int dir) {
	//가로일 경우 col +-1 검사, 세로면 row +-1 검사
	for (int i = -1; i < 2 ; i++) {
		int row = curr.row + (curr.isTurned ? 0 : i);
		int col = curr.col + (curr.isTurned ? i : 0) ;
		//새로 할당한 좌표가 가능한지 검사, 중앙일 경우 방문체크 검사
		if(!isValid(new Log(row, col, curr.isTurned), i == 0))  return false;
	}
	return true;
}
```

- 방향돌리기는 인접한 8방을 모두 검사해서 유효 좌표인지 검사합니다.
- 이동은 가로인지 세로인지 판별하여 3칸에 대한 유효좌표 검사를 합니다.
- 이때 `i == 0 && j == 0` **이거나 `i == 0` 이면 중앙이므로 방문체크도 동시에 합니다.

### 유효좌표 검사

```java
private static boolean isValid(Log curr, boolean isCenter) {
	int r = curr.row;
	int c = curr.col;
	//중앙 비트마스크 검사용 변수
	int t = curr.isTurnedToInteger();

	//벽 경계 검사
	if (r >= N || r < 0 || c >= N || c < 0 || map[r][c])    return false;
	//중앙좌표가 아니면 true, 중앙좌표면 방문체크
	return !isCenter || (visited[r][c] & 1 << t) == 0;
}
```

- 비트마스크 하기위해 `t` 변수에 방향값을 정수로 받아옵니다.
- 중앙이 아니라면 경계 및 벽만 검사하여 반환합니다. 중앙좌표라면 방문체크를 포함한 결과를 반환합니다.

## 후기

싸피하면서 푼 알고리즘 문제 중 제일 긴 코드를 작성한 것 같습니다. 푸는데 굉장히 오랜 시간이 걸렸지만 디버깅 하는데 시간이 많이 걸린 게 아니라 입맛에 맞게 코드를 고치느라 많은 시간이 지났습니다.

알고리즘을 푸는 것이 아닌 프로젝트를 하는 느낌이어서 정말 재밌었고 뿌듯해서 주석도 잔뜩 썼습니다. 그렇지만 문제 풀 때는 너무 깊게 생각하지말고 빨리 푸는데에 집중해야할 것 같다고 생각했습니다.