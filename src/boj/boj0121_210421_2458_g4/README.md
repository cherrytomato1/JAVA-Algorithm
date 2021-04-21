## 문제 정의

1. 학생 수 `N` ( 2 ≤ N ≤ 500) 이 주어집니다. 이 후 `M` 개 줄에 `M` 개의 키 비교 정보가 주어집니다.
2. `a b` 로 주어졌을 때 `a` 가 `b` 보다 작은 것을 의미합니다.
3. 한명의 학생이 모든 학생에 대해 자기보다 큰 지 작**은지** 알 수 있다면 그 학생은 자신의 키를 아는 것 입니다.
4. 자신의 키를 아는 학생의 수를 출력합니다.

## 문제 풀이

1. 모든 정점에 대한 인접행렬을 생성합니다. 간선정보에 맞게 인접행렬 정보를 작성합니다.
2. 플로이드 와샬을 통해 접근 가능한지에 대한 정보를 작성합니다.
3. 이후 모든 정점으로 접근할 수 있는 정점의 수를 세서 출력합니다.

## 후기

플로이드 와샬은 탐색 서순이 중요합니다 경출도 매우중요