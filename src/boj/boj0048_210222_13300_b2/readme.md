## 문제 정의

1. 방을 배정해야 하는데 배정할 때 남학생, 여학생은 같은 방을 쓰면 안되고, 같은 학년끼리만 방을 배정해야합니다.
2. 첫 열에 한 방에 배정할 수 있는 학생 수 N ( 1≤ N ≤ 1,000) 제한인원 K(1≤ K ≤ 1,000)이 주어집니다.
3. 다음 열부터 N 개 열에 학생의 성별(0, 1)과 학년 (1 ~ 6) 이 주어집니다.
4. 방을 최소로 배정할 수 있는 수를 구하세요

## 문제 풀이

1. 모든 입력을 받고 학년, 학생별로 방을 만듭니다.
2. 각 방의 인원이 0일 때 해당 방에 학생이 입력되면 방의 수를 셉니다.
3. 각 방의 인원이 가득찼을 때 방의 인원을 비웁니다.

## 코드

```java
for(int i = 0 ; i < N; i++){
    st = new StringTokenizer(br.readLine());
    int gender = Integer.parseInt(st.nextToken()) ;
    int grades = Integer.parseInt(st.nextToken()) - 1;

    if(students[gender][grades] == 0)   rooms++;
    if(++students[gender][grades] == K) students[gender][grades] -= K;
}
```