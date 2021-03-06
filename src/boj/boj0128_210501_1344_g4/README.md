## 문제 정의

1. 90분의 축구 경기의 각 5분동안 A팀과 B팀이 득점할 확률이 주어집니다.
2. 각 팀 중 한팀이라도 소수의 점수를 득점할 확률을 구합니다.

## 문제 해결

1. 5분씩 나누어서 각각 A팀이 득점한 경우, 득점하지 못한경우, B 팀이 득점한 경우, B팀이 득점하지 않은 경우에 대해서 dp 테이블을 구성합니다.
2. `d[i][j][k]` 가 될 수 있는 확률 ⇒ `d[i - 1][j - 1][k] * a * (1 - b)` → 한 라운드 전에 `a`팀이 한 골 덜 넣었을 때 `a`팀이 골 넣을 확률 + `b` 팀이 골 못넣을 확률 + `d[i - 1][j][k - 1] ...`
3. 점화식

```
d[i][j][k] = 
	   d[i - 1][j][k] * (1 -a) * (1 - b)
   + d[i - 1][j - 1][k] * a * (1 - b))
   + d[i - 1][j][k - 1] * (1 - a) * b)
   + d[i - 1][j - 1][k - 1] * a * b))
```

4.  이후 소수 인덱스를 갖는 테이블의 모든 값의 합을 출력합니다.