## 문제 정의

1. `N` (2 ≤ N 20,000) 개의 헛간이 있을 때 `M` (1 ≤ M ≤ 50,000)열에 걸쳐 헛간의 연결정보가 주어집니다.
2. `1` 번 헛간으로부터 가장 먼 헛간 중 헛간 번호가 가장 낮은 값, `1` 번 헛간으로부터 거리와 같은 거리를 갖는 헛간의 수를 공백으로 구분하여 출력합니다.

## 문제 풀이

1. 같은 깊이에 있는 그래프에서 헛간 번호가 가장 낮은 헛간부터 탐색하기 위해 우선순위 큐 인접리스트로 그래프를 구현합니다.
2. 1번 헛간부터 탐색을 시작하여 거리를 인덱스로하는 2차원 배열에 가장 먼 거리의 가장 낮은 헛간 번호와 그와 같은 거리를 갖는 헛간의 수를 저장합니다.

## 후기

우선순위 큐로 탐색순서를 정렬하여 가장 먼 거리에 먼저 도착하는 좌표가 정답 좌표인줄 알았으나 부분 그래프에서 탐색 순서는 부모의 헛간 번호에 의존하므로 답을 풀이하는데 실패했습니다.

결국 가장 낮은 헛간 번호를 출력해야 하므로 헛간 번호순서로 탐색하기 위해 우선순위 큐를 사용했지만 탐색 순서와 관계없이 같은 거리에 있는 모든 헛간 중 가장 낮은 값을 출력해야 하므로 모든 헛간번호를 비교해야합니다.