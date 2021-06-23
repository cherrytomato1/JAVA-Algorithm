## 문제 정의

1. 행성에 사는 사람 `N` (1 ≤ N ≤ 1_000_000)명의 이름은 모두 10진수로 되어 있습니다.
2. 행성 주민들의 친밀도는 이름의 `XOR` 연산으로 구할 수 있습니다.
3. 모든 행성 주민의 친밀도 합을 출력합니다.

## 문제 풀이

1. 모든 주민을 `XOR` 하게되면 행성 주민 `N` 은 최대 `1_000_000` 명이므로 완전탐색으로 해결할 수 없습니다.
2. 모든 주민의 각 자릿수의 `XOR` 연산 참 개수 결과는 `1` 의 개수와 `0` 의 개수를 곱한 것과 같습니다.
3. 각 자리의 `XOR` 한 결과의 수에 자리의 수를 곱한 것 만큼 더해준 결과를 출력합니다.