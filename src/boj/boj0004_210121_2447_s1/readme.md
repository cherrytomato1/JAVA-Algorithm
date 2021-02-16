## 문제 정의

1. 입력 N은 3의 거듭제곱 즉, N = 3^k 이며 N*N의 크기를 갖는 패턴을 출력합니다.
2. 출력 패턴은 N * N 크기에 가운데만 비어 있는 재귀적인 꼴을 띄고 있습니다.

## 문제 풀이

1. N = 3 을 기저조건으로 하는 재귀함수를 선언합니다. 각 재귀함수는 N/3 을 인자로 하는 9개의 재귀함수를 호출합니다.
2. 각 재귀에서 가운데에 존재하는 패턴은 공백을 출력해야하므로 type 변수를 선언하여 공백을 그려야하는 것을 알려줍니다.
3. 각 블록이 위치하는 자리를 표시하기 위해 블록의 좌표를 인자로 념겨주고 각 좌표는 반복문의 인자를 통해서 정해줍니다.
4. 기저조건에 도달했을 경우 배열에 type과 좌표에 따라서 true나 false를 넣고 재귀를 종료합니다.

## 코드

```java
static boolean[][] arr;
    static int N ;

    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        arr = new boolean[N][N];
    }

f(N, 0, 0, true);
```

- N을 입력받고, boolean 형으로 값을 저장할 배열을 선언합니다. 그 후 N을 인자로 넘겨 재귀함수를 시작합니다.

```java
static void f(int num, int row, int col, boolean type){
    if(num == 3){
        draw(row, col, type);
        return ;
    }
```

- num은 재귀함수로 그릴 별 패턴의 크기이고, row 와 col 은 각 패턴이 위치하는 좌표를 의미합니다. type은 공백을 출력할 지, 별을 출력할지 결정해주는 인자입니다.
- num == 3 일 경우를 기저조건으로 하여 각 type과 좌표에 맞는 배열에 값을 넣기 위해 draw 메소드를 호출합니다.

```java
for(int r = 0 ; r < 3 ; r ++){
    for(int c = 0; c < 3 ; c ++){
        if(r == 1 && c == 1)    f(num/3, row + (num/3 * r), col + (num/3 * c), false);
        else                    f(num/3, row + (num/3 * r), col + (num/3 * c), type);
    }
}
```

- 매 재귀마다 총 9번의 재귀호출을 하며 각 row와 col 값에 다음으로 넘겨주는 num/3 에 맞는 좌표를 보내기 위해 반복 인자인 r과 c를 곱해주어 인자로 넘깁니다.
- 패턴의 가운데 좌표(r == c == 1)인 의 경우는 false로 보내주고, 아닌 경우는 현재 모양을 그대로 받아 넘겨줍니다.

```java
static void draw(int row, int col, boolean type){
    for(int r = 0 ; r < 3; r++){
        for(int c = 0 ; c < 3 ; c++){
            if(!type || (r == 1 && c == 1))     arr[r + row][c + col] = false;
            else                                arr[r + row][c + col] = true;
        }
    }
}
```

- 기저조건일 때 호출되는 draw 메소드는 출력해야하는 좌표와 type을 인자로 받습니다.
- type이 false 거나 출력 중인 좌표가 중앙(r == c == 1) 일 경우 false, 아닐 경우 true 를 저장합니다.

```java
static void print(){
    for(int r = 0 ; r < N ; r++){
        for(int c = 0 ; c < N ; c++) {
            sb.append(arr[r][c]? '*' : ' ');
        }
        sb.append("\n");
    }
    System.out.println(sb);
}
```

- 모든 재귀가 끝난 후 각 좌표의 값에 따라 '*' 또는 ' '를 출력합니다