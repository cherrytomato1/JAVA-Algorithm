## 문제정의

1. 시그널은 `N` 의 길이를 갖는 `#` 또는 `.` 로 이루어진 문자열로 주어집니다.
2. `N`은 5의 배수로만 주어지며 각 시그널을 5개열로 나누어 놓게 된다면 수를 표현합니다.
3. 각 수 사이에는 한 줄의 공백이 주어진다고 했을 때 주어진 시그널을 숫자로 해석하여 출력합니다.

## 문제풀이

1. 먼저 시그널을 5개의 `String` 배열로 저장합니다.
2. 모든 문자를 나타내는 시그널은 첫번째 행과 첫번째 열의 값이 `#` 이므로 해당하는 경우에 문자를 해석합니다.
3. `1` 을 표시하는 시그널을 제외하고 모든 시그널은 3줄로 이루어져 있으므로 `1` 인 경우에 대한 예외를 처리합니다.
    1. `1` 인 경우는 다음과 같습니다.
        1. 모든 첫 번째 행이 `#` 이여야함
        2. 모든 두 번째 행이 `.` 또는 시그널의 범위 밖이어야함.
    2. 위와 같은 경우에는 `1` 로 해석하고 다른 숫자 해석을 하지 않습니다. 문자열에 대한 반복인자도 `2` 칸만 더합니다.
4. `1` 이 아닌경우 3 * 5 배열을 모두 검사하여 `#` 이 몇개인지에 따라 값을 출력합니다. 그 수는 다음과 같습니다.

|0|1|2|3|4|5|6|7|8|9|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|12|5|11|11|9|11|12|7|13|12|

 5. 중복되는 `#` 의 수가 없는 경우는 바로 출력하고, 중복되는 경우가 있다면 각각의 3 * 5 배열에서 구분되는 좌표를 검사합니다.

 6. 검사가 완료된 값은 `StringBuilder` 에 저장하고 출력합니다.

## 코드

```java
N = Integer.parseInt(br.readLine());
String str = br.readLine();

strArr = new String[N];
length = N/5;

for(int i = 0; i < 5 ; i++){
	int subIdx = length * i;
	strArr[i] = str.substring(subIdx, subIdx + length);
}
```

- 모든 문자열을 5개의 배열로 저장합니다

```java
static String solve(){
    StringBuilder res = new StringBuilder();
    for(int i = 0 ; i < length; i++){
        if(strArr[0].charAt(i) != '#')  continue;

        int cnt = check(i);
        if(cnt == 1)    i++;
        else            i += 2;
        res.append(cnt);
    }
    return res.toString();
}
```

- 5로 나누어진 길이만큼 검사하며, `#` 으로 시작하는 경우에 수 검사를 시작합니다.
- 결과가 `1` 인 경우 한칸만 건너뛰고 다른경우는 세칸을 건너뜁니다.

```java
boolean flag = true;
for(int i = 0 ; i < 5; i++){
	for(int j = 0 ; j < 2 && idx + j < length; j++) {
		if (strArr[i].charAt(idx + j) != (j == 0 ? '#' : '.')) {
			flag = false;
			break;
		}
	}
}
if(flag)    return 1;
```

- `1` 인 경우 첫번째 열이 모두 `#` 이면서 두번째 열은 경계 밖이거나 모두 `.` 이어야합니다.
- 해당경우는 바로 `1` 을 반환합니다.

```java
int cnt = 0;
for(int i = idx ; i < idx + 3 ; i++){
	for(int j = 0 ; j < 5; j++){
		if(strArr[j].charAt(i) == '#') cnt ++;
	}
}

switch(cnt){
	case 9 : return 4;
	case 7 : return 7;
	case 13 : return 8;
	default : return findNum(cnt, idx);
}
```

- `1` 이 아닌경우 `#` 의 개수에 맞는 값을 반환하고 겹치는 경우 값을 찾습니다.

```java
static int findNum(int num, int idx){
    if(num == 11){
        if(strArr[1].charAt(idx) == '#')    return 5;
        if(strArr[3].charAt(idx) == '#')    return 2;
        return 3;
    }else if(num == 12) {
        if (strArr[2].charAt(idx + 1) != '#') return 0;
        if (strArr[3].charAt(idx) == '#') return 6;
        return 9;
    }
    return -1;
}
```

- 값에 맞는 자리를 검사하여 수를 판별하여 반환합니다.

## 후기

오직 구현만 하는 문제는 오랜만이라 재밌게 풀었지만 디버깅에 많은 시간을 썼습니다. 구현 문제도 많이 풀어봐야할 것 같습니다.
