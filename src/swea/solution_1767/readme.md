# 문제 정의

1. `N * N` 의 배열에 코어(1 이상 12 이하 개)와 빈공간이 주어집니다. (7 ≤ N ≤ 12)
2. 코어는 4방으로 연결되며 연결 전선은 꺾이거나 교차할 수 없습니다.
3. 가장 자리에 있는 코어는 이미 연결된 것으로 봅니다.
4. 가장 많은코어에 연결했을 때의 전선 길이의 합을 출력하세요. 방법이 여러개라면 가장 짧은 전선 길이를 출력하세요.

# 문제 풀이

1. 먼저 입력받으면서 코어들의 위치 좌표를 넣어둡니다. 이 때 이미 연결된(가장 자리에 위치한) 코어는 저장하지 않습니다
2. `N * N` 으로부터, 첫번째 코어부터 4방향을 놓는 재귀로 시작합니다.
3. 4방향으로 진행하면서 연결 가능한 경우, 전선을 맵에 놓고 코어수 + 1해서 재귀합니다.
4. 연결 불가능한경우 코어 인덱스만 + 1 해서 재귀합니다.
5. 전선을 놓을 때 전선을 놓은 개수를 카운트해서 다음 재귀에 넘깁니다.
6. 그리고 재귀 시 모든 코어를 놓아도 최대의 코어를 놓을 수 없을 경우를 백트래킹합니다.
7. 모든 코어에 대해 탐색이 끝났을 경우 최대 코어 개수와 최소 전선길이를 갱신해줍니다.

## 코드

```java
for (int r = 0; r < N; r++) {
		st = new StringTokenizer(br.readLine());
		for (int c = 0; c < N; c++) {
				if(st.nextToken().equals("1")){
						map[r][c] = true;
						if(r != 0 && r != N -1 && c != 0 && c != N - 1) cores.add(new Node(r, c));
				}
		}
}
```

- `boolean` 타입으로 맵을 받으면서 가장자리에 있지 않은(아직 연결되지 않은) `core`는 검사해야할 리스트에 추가합니다.

```java
for(int i = 0 ; i < 4; i++){
		Node curr = cores.get(coreIdx);
		if(!check(curr, i)) {
				dfs(coreIdx + 1, len, coreCnt);
				continue;
		}
		int nlen = mark(curr, i, true);
		dfs(coreIdx + 1, len + nlen, coreCnt + 1);
		mark(curr, i, false);
}
```

- 해당 `core` 를 4방으로 탐색하면서 연결될 수 있는지 검사합니다.
- 연결 가능한 방향이라면 전선을 놓아주고 놓아준 전선의 개수를 받아옵니다.
- 연결 가능하다면 `coreCnt` 를 하나 더해주고 재귀로 넘기고, 아니라면 그냥 넘깁니다.
- 연결한 후 탐색을 마쳤다면 전선을 다시 지웁니다.

```java
if(coreCnt + cores.size() - coreIdx < coreMax) return;

if(coreIdx == cores.size()){
		if(coreMax < coreCnt)   {
				lenMin = len;
				coreMax = coreCnt;
		}else if(coreCnt == coreMax){
				lenMin = Math.min(lenMin, len);
		}
		return;
}
```

- 현재 연결된 코어의 수 + 남은 코어의 개수가 최대로 연결된 코어의 수보다 작다면 가지치기해줍니다.
- 모든 코어를 연결 했을 때 연결된 코어가 기존보다 많다면 길이와 코어의 수를 변경합니다.
- 같다면 둘 중 더 작은 길이를 저장합니다.

```java
static int mark(Node pos, int dir, boolean type){
		int row = pos.row;
		int col = pos.col;
		int cnt = 0;

		for(int i = 1 ; ; i++){
				int nr = row + DIR[dir][0] * i;
				int nc = col + DIR[dir][1] * i;
	
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] == type)  return cnt;

				visited[nr][nc] = type;
				if(!map[nr][nc])    cnt ++ ;
		}
}

static boolean check(Node core, int dir){
		int row = core.row;
		int col = core.col;

		for(int i = 1 ; ; i++){
				int nr = row + DIR[dir][0] * i;
				int nc = col + DIR[dir][1] * i;
	
				if(nr < 0 || nr >= N || nc < 0 || nc >= N)  return true ;
				if(visited[nr][nc] || map[nr][nc])   return false;
		}
}
```

- 탐색할 때 전선이나, 코어에 걸리지 않고 가장자리까지 도착한다면 `true` 를 반환합니다.
- 전선을 놓을 때 해당 위치가 코어가 아니라면 놓는 수를 세줍니다.