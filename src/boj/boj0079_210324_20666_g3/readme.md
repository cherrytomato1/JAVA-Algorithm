## 문제 정의

1. 첫째 줄에 몬스터의 수 `N` ( 1 ≤ N ≤ 100,000) 과 잡아야하는 몬스터 수 `M` ( 1 ≤ M ≤N) 이 주어집니다.
2. 둘째 줄에 `N` 개의 각 몬스터별 난이도 `C` 가 주어집니다. ( 1 ≤ C ≤ 1,000,000,000)
3. 셋째 줄에 팁의 개수 `p` 가 주어집니다. 
4. 넷째 줄부터 `p` 개 줄에 공백으로 구분하여 `a` , `b` , `c` 가 주어집니다. `a` 를 클리어 하지 않으면 `b` 의 난이도가 `c` 만큼 올라갑니다.

## 틀린 문제 풀이 1

1. 초기에 모든 몬스터의 난이도를 아무 몬스터도 잡지 않았을 때로 맞춥니다.
2. 최대 힙에 `M` 개의 난이도가 가장 낮은 몬스터를 넣습니다. 넣으면서 몬스터해당 몬스터를 클리어합니다.
3. 클리어된 몬스터는 팁 간선에 해당하는 몬스터의 난이도를 가중치만큼 낮춥니다. 그리고 난이도 낮아진 몬스터가 최대 힙의 `peek()` 값보다 작다면 힙에서 하나를 빼고 그 몬스터를 넣습니다.
4. 모든 몬스터를 힙과 비교하여 넣고 힙의 가장 큰 값을 결과로 출력합니다.

## 틀린 문제 풀이 2

1. 모든 몬스터들을 배열에 입력받습니다.
2. 팁에 입력된 값 만큼 해당하는 몬스터들의 초기 난이도를 더해줍니다.
3. 모든 몬스터를 최소 힙에 넣고 최소 힙에서 하나씩 빼면서 해당 몬스터가 처치되지 않았다면 몬스터를 처치합니다.
    1. 몬스터를 처리했다면 해당 몬스터의 난이도와 최대 난이도를 비교해서 더 큰 값으로 갱신합니다. 이후 몬스터 처치 플래그를 `true` 로 설정합니다.
    2. 해당 몬스터가 아이템을 준다면 팁에 기록된 간선에 포함되는 몬스터의 난이도를 갱신합니다.
    3. 갱신된 몬스터를 힙에 넣어줍니다.
4. `M` 마리의 몬스터를 처치할 때까지 3번을 반복합니다.

## 후기

6시간 동안 싸웠지만 졌습니다