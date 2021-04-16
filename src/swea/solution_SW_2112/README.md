## 문제 정의

1. `0`과 `1`로 이루어진 2차원의 막이 주어집니다.
2. 테스트를 통과하려면 모든 열에서 `0` 또는 `1` 로 이루어진 `K` 개의 연속된 값이 있어야합니다.
3. 이 때 모든 행에 대하여 모두 `0` 또는 모두 `1` 로 바꿀 수 있는 약을 투하할 수 있습니다.
4. 최소의 약을 사용하여 통과할 수 있는 약의수를 출력합니다.

## 문제 풀이

1. 모든 경우에 대해 완전탐색합니다.
    1. 첫 입력부터 세개 이상 배열되어 있는지 검사합니다.
    2. 아니라면 다음 행에 대하여 `a` 투입, `b` 투입, 투입 x 로 분기합니다.
2. 위 과정중 최소값을 넘는다면 분기하지 않습니다.
3. 이 후 만들어진 부분 집합으로 맵을 만들어 가능하다면 `cnt` 값을 갱신합니다

## 문제 풀이 2

1. 모든 경우에 완전탐색합니다
2. 이 때 맵을 만드는데 소모되는 시간이 많으므로 약을 투입한 인덱스 값에 대해 변경하여 분기합니다.
3. 만들어진 맵이 가능하다면 `cnt` 갱신

## 후기

배열을 복사하는 대신 해당 인덱스만 바꿔주어도 재귀형식이기 때문에 다른 곳에는 영향을 미치지 않습니다.