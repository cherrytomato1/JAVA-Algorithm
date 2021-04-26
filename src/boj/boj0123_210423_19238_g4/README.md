## 문제 정의

1. `N` * `N` ( 2≤ N ≤ 20) 의 맵에 벽과 길이 주어집니다. `M` 명의 손님의 출발지 좌표와 목적지 좌표가 입력됩니다. 이후 초기연료 `L` 이 주어집니다.
2. 맵 정보 이후에는 택시 출발 위치가 주어집니다.
3. 이후 `M` 개의 열에 손님 정보가 출력됩니다.
4. 시작 시 혹은 손님의 목적지 도착 시 최단경로가 가장 가까운 손님의 출발위치로 이동합니다. 같은 손님이 여럿이라면 행번호가 낮은 순, 행번호가 같다면 열 변호가 낮은 손님에게 이동합니다.
5. 한 칸에 하나의 연료가 소모되며 손님을 태운 후 목적지까지 가는데 소모한 연료 * 2가 목적지에 도착했을 때 충전됩니다.
6. 모든 손님을 배달했다면 남은 연료, 실패할경우 -1을출력합니다.

## 문제 풀이

1. 모든 손님의 시작 위치를 맵에 그리고, 시작 위치 및 도착 위치를 손님 객체 배열에 담습니다.
2. 벽은 `-1` 로 표시하며 1 이상의 모든 정수는 손님 번호입니다.
3. 시작 시 탐색하여 손님을 만날 떄까지 탐색합니다. 탐색 중 연료가 0 이되면 종료합니다.
4. 같은 탐색 깊이에서 만나는 모든 손님들을 우선순위 큐에 넣습니다. 이후 가장 우선순위 큐에서 가장 높은 우선 순위의 손님을 배달 목표로 정합니다.
5. 배달 목표 지점까지 탐색하며, 도착 시 손님 탐색 시작부터 도착까지 소모한 연료를 두배로 하여 더합니다.
6. 모든 손님을 배달할 떄까지 3~5를 반복합니다.