## 문제 정의

1. `P` 와 `A` 로 구성된 문자열 `S` 가 주어집니다 (1 ≤ S 의 길이 ≤ 1_000_000)
2. `PPAP` 는 `P` 로 다시 재작성할 수 있습니다.
3. 입력 문자열을 `P` 로 바꿀 수 있다면 `PPAP` , 아니라면 `NP` 를 출력합니다.

## 문제 풀이

1. 문제를 해결하기 위해서는 문자열을 `P` 로 바꾸어야하므로 모든 `A` 문자를 `PPAP` 로 바꿀 수 있어야합니다.
2. 따라서 `A` 가 스택에 담겨있고 `P` 가 입력된다면 `PPAP` 로 바꿀 수 있는지 확인합니다.
3. 입력 문자열을 차례대로 스택에 담되 다음과 같은 조건으로 분기합니다.
4. 스택에 `A` 가 포함되지 않았다면 현재 문자를 스택에 넣고 현재 문자가 `A` 라면 `A` 가 포함되었음을 표시합니다.
5. 스택에 `A` 가 포함되었고 현재 담을 문자가 `A` 라면 `PPAP` 로 바꿀 수 없으므로 `NP` 를 출력합니다.
6. 스택에 `A` 가 포함되었고 `P` 를 담으려한다면, 현재 `P` 를 포함해서 스택 값으로`PPAP` 를 만들 수 있는지 확인합니다.
7. 만들 수 없다면 `NP` 를 출력하고 만들 수 있다면 검사한 값들을 제거하여 `P` 만 담습니다.