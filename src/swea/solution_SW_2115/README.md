## 문제 정의

1. `N` * `N` (3 ≤ N ≤ 10) 크기의 벌집이 주어집니다.
2. 두 일꾼이 가로로 `M` 개 연속된 꿀을 채취할 수 있습니다. 이 때 각 일꾼은 `C` 이하의 꿀만 채취할 수 있으며 `M` 개의 꿀의 합이 `C` 초과라면 그 중 몇 개를 빼어서 `C` 이하의 꿀만 채취해야합니다.
3. 수익은 채취한 모든 꿀의 각각의 제곱의 합입니다. 최대로 채취할 수 있는 꿀을 출력합니다.

## 문제 풀이

1. 전처리
    1. 먼저 모든 벌집의 구간에 대해 `M` 개 연속되었을 때, `C` 미만인 경우에 대해 최댓값을 갱신해놓습니다.
    2. 부분집합으로 합이 `C` 미만인 값 중 최댓값을 각각의 배열에 저장해놓습니다.
2. 탐색
    1. 모든 전처리된 배열에 대하여 하나를 선택한 값과 두번째 벌이 선택한 값을 비교합니다.
    2. 두 번째 값을 선택할 때 행이 같으면서 열의 값 차이가 `M` 이하인 값은 선택하지 않습니다.