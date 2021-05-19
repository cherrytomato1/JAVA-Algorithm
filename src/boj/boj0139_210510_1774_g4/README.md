## 문제 정의

1. 모든 우주신들을 연결하는 최소의 비용을 출력합니다.
2. 첫 줄에 우주신의 수 `N` 과 이미 연결된 우주신의 수 `M` 이 주어집니다.
3. 다음 `N` 개 줄에 각 우주신의 좌표가 주어지고 다음 `M` 개 줄에 이미 연결된 우주신들의 간선이 주어집니다.
4. 이미 연결된 우주신들의 간선은 비용이 `0` 이며 연결되지 않은 우주신들의 연결 비용은 두 좌표사이의 거리입니다.

## 문제 정의

1. 각 우주신의 좌표를 저장하고, 인접 행렬을 만듭니다.
2. 이미 연결된 우주신들간의 간선 비용을 `0`으로 초기화합니다.
3. 정점 중심이므로 prim 및 kruskal으로 최소 스패닝 트리를 구합니다.

## 후기

prim 으로 문제를 해결하다 자꾸 *틀렸습니다*가 나와서 kruskal 로 다시 풀었습니다. 그러다가 우선순위 큐 comparator 구현에 문제가 있음을 발견하고 prim으로 제출하고 다시 kruskal로도 푸는데... union-find를 오랜만에 짜다 보니 잘못 작성한 부분이 있어서 또 엄청난 *틀렸습니다*를 봤습니다

![image](https://user-images.githubusercontent.com/49678555/117751998-00c5f300-b251-11eb-820a-8f668f9c144d.png)


두 방법 모두로 풀다보니 연습이 많이 된 것 같습니다. 그리고 밀집 그래프에서는 인접행렬을 이용한 프림이 동작속도가 많이 빨랐습니다.