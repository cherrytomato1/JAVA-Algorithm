## 문제 정의

1. 파이프로 이루어진 맵이 주어집니다.
2. 1부터 7 까지 이동할 수 있는 위치가 주어집니다.
3. 탈주범이 탈출한 시간이 주어졌을 때 갈 수 있는 범위 크기를 출력합니다.

## 문제 풀이

1. 각 파이프 모양에 맞는 방향 배열을 새로 정의해줍니다.
2. 해당 칸의 방향배열에 따라 탈주범이 갈 수 있는 장소를 `bfs` 로 탐색합니다
3. 갈 수 있는 모든 칸의 수를 출력합니다.