## 문제 정의

1. 입력 N은 3의 거듭제곱 즉, N = 3^k 이며 N*N의 크기를 갖는 패턴을 출력하라
2. 패턴은 재귀적인 꼴을 띄고 있으며 N=3의 패턴은 다음과 같다

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b25b753f-0427-4f4b-8648-2a9f4829b459/_2021-02-14__3.12.49.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b25b753f-0427-4f4b-8648-2a9f4829b459/_2021-02-14__3.12.49.png)

3. N의 커질 때 패턴은 가운데 빈공간의 패턴을 포함하며 N = 27일 때의 패턴은 다음과 같다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6aebbae0-8556-4e67-9063-096744d25669/_2021-01-22__9.47.15.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6aebbae0-8556-4e67-9063-096744d25669/_2021-01-22__9.47.15.png)

## 문제 풀이

1. 출력 그림은 3의 거듭제곱인 꼴로 재귀적인 모습을 하고있기 때문에 N = 3 을 기저조건으로 하는 재귀함수를 선언한다. 각 재귀함수는 N/3 을 인자로 하는 9개의 재귀함수를 호출한다.
2. 각 재귀에서 가운데에 존재하는 패턴은 공백을 출력해야하므로 type 변수를 선언하여 음수임을 표현한다.
3. 각 블록이 위치하는 자리를 표시하기 위해 블록의 좌표를 인자로 념겨준다. 이 때 각 좌표는 반복문의 인자를 통해서 정해준다.
4. N = 3일 경우 배열에 '*'이나 ' '를 넣고 종료

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

- N을 입력받고, boolean 형으로 값을 저장할 배열을 선언한다. 그 후 N을 인자로 넘겨 재귀함수를 시작한다.

```java
static void f(int num, int row, int col, boolean type){
    if(num == 3){
        draw(row, col, type);
        return ;
    }
```

- num은 재귀함수로 그릴 별 패턴의 크기이고, row 와 col 은 각 패턴이 위치하는 좌표를 의미한다. type은 공백을 출력할 지, 별을 출력할지 결정해주는 인자이다.
- num == 3 일 경우를 기저조건으로 하여, 각 type 에 맞는 별을 그릴 수 있도록 별 그리기 메소드를 호출한 후 함수를 종료한다.

```java
for(int r = 0 ; r < 3 ; r ++){
    for(int c = 0; c < 3 ; c ++){
        if(r == 1 && c == 1)    f(num/3, row + (num/3 * r), col + (num/3 * c), false);
        else                    f(num/3, row + (num/3 * r), col + (num/3 * c), type);
    }
}
```

- 매 함수마다 총 9번의 재귀호출을 하며 각 row와 col 값에 다음으로 넘겨주는 num/3 에 맞는 좌표를 보내기 위해 반복 인자인 r과 c를 곱해주어 인자로 넘긴다.
- 가운데 좌표인 r == c == 1의 경우는 모양을 ' '로 보내주고, 아닌 경우는 현재 모양을 그대로 받아 넘겨준다.

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

- 기저조건일 때 호출되는 draw 메소드는 출력해야하는 좌표와 type을 인자로 받는다.
- type이 false 거나 출력 중인 좌표가 중앙(r == c == 1) 일 경우 false, 아닐 경우 true 를 저장한다.

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

- 모든 재귀가 끝난 후 각 좌표의 값에 따라 '*' 또는 ' '를 출력한다.