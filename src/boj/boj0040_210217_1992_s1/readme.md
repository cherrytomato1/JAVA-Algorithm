## 문제 정의

1. 주어진 배열의 값이 모두 1이면 1, 1이 아니면 0으로 표현할 수 있습니다.
2. 이때 모두 1이거나 0이 아니라면 영상을 4분할하여 차례대로 괄호로 묶어 표시합니다.
3. 더 이상 분할하지 않아도 될 때까지 영상을 4분할 합니다.

## 문제 풀이

1. 주어진 배열이 모두 0이거나 모두 1인 것을 먼저 확인합니다.
2. 아닐 경우, 4방으로 쪼개어 각각의 좌표에서 1번을 반복합니다.
3. 영상을 쪼갰다면 4방의 결과를 ( dfs1, dfs2, dfs3, dfs4)에 담아서 스트링에 넣고, 아니라면 0 또는 1을 반환합니다.

## 코드

```java
map = new boolean[N][N];
  for(int i = 0 ; i < N ; i++){
      String str = br.readLine();
      for(int j = 0 ; j < N ; j++){
          map[i][j] = str.charAt(j) == '1';
      }
  }
```

- 주어진 배열을 boolean 배열에 저장합니다.

```java
static String f(int size, int row, int col){
    int check = check(size, row, col);
    if(check == 1)        return "1";
    else if(check == 0)   return "0";
    StringBuilder sb = new StringBuilder();

    size /= 2;

    sb.append("(");
    sb.append(f(size, row, col));
    sb.append(f(size, row, col + size));
    sb.append(f(size, row + size, col));
    sb.append(f(size, row + size, col + size));
    sb.append(")");

    return sb.toString();
}
```

- 입력 배열을 재귀함수에 넣습니다. 재귀함수는 check 메소드의 리턴값을 통해 종료합니다.
- check 메소드에 의해 종료되지 않았다면 각 배열의 사이즈를 절반으로 하여 4등분된 좌표와 함께 넘겨줍니다. 이 때 리턴 스트링에 네 개의 메소드의 리턴과 괄호를 넣습니다.

```java
boolean flag = map[row][col];
for(int r = row; r < row + size ; r++) {
    for (int c = col; c < col + size; c++) {
        if (map[r][c] != flag) return -1;
    }
}
return flag ? 1 : 0;
```

- check 메소드에서는 첫 좌표를 기준으로 범위 내의 모든 좌표가 같은 값을 가졌는지 확인합니다. 다른 좌표가 있다면 -1 리턴, 모두 같다면 첫 좌표의 값에 따라 1 혹은 0을 반환합니다.