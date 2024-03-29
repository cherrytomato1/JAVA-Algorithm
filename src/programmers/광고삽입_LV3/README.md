## 문제 정의

1. 전체 영상 수행시간과 광고 영상 시간이 주어집니다.
    - `HH:MM:SS`
2. 각 시청자들의 시청 시간 로그가 주어집니다. 로그의 개수 n ( 1 ≤ n ≤ 300_000)
    - `H1:M1:S1-H2:M2:S2`
3. 광고 영상을 어디에 삽입하여야 누적 시청시간이 가장 길지, 광고영상의 시작시간을 출력합니다.

## 문제 해결

1. 총 수행시간과 광고 영상의 길이를 초 단위로 바꾸어 배열을 생성합니다.
2. 총 영상 배열의 맨 앞부터 시작하여 누적 시청 시간을 기록합니다.
    1. 로그의 시작 시각과 끝 시각을 각각 우선순위 큐에 담습니다.
    2. 현재 탐색하고 있는 시간이 로그의 시작 시간이라면 해당 로그를 지우고 현재 시청수를 하나 더하여 배열에 더해줍니다.
    3. 현재 탐색하고 있는 시간이 로그의 끝 시간이라면 해당 로그를 지우고 현재 시청수를 하나 뺍니다.
3. 배열의 맨 뒤부터 누적합을 더하여 기록합니다. 이 중 가장 큰 값을 가진 인덱스를 결과로 변환하여 출력합니다.