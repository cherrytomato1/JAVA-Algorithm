## 문제 정의

1. 소괄호와 대괄호만으로 이루어진 문자열이 입력으로 주어진다.
2. 올바르지 않은 괄호 수식이라면 0을 출력.
3. 올바른 괄호 수식이라면 
    1. () → 2 로 계산
    2. [] → 3으로 계산
    3. () 안에 수가 있을 경우 → * 2
    4. [] 안에 수가 있을 경우 → * 3
4. 위와 같은 수식으로 계산된 값을 출력한다.

## 풀이

1. 가능한 괄호 수식인지 검사하면서 동시에 연산하려면 코드가 매우 복잡해지므로 가능한 수식인지 먼저 검사한다.
2. 스택으로 쌍이 맞는지 검사해 틀리다면 0을 출력하고 리턴.
3. 올바른 수식이라면 정수형으로 정의된 스택에 여는 괄호를 담는다.
4. 닫는 괄호가 나올 경우, 알맞은 닫는 괄호가 나올 때까지 스택을 팝한다.(이 때 틀린 수식의 예외처리는 하지 않는다.)
5. 이 때 팝 된 수들은 모두 더해서 괄호에 알맞은 값으로 곱해서 다시 스택에 푸시한다.
6. 팝 된 수가 없는 경우(바로 닫는 괄호가 나온 경우) 괄호에 따라 2 혹은 3을 스택에 푸시한다.
7. 수식이 모두 끝났다면 남아있는 모든 값을 더해서 출력한다.

## 코드

```java
package boj.boj0021_210207_2504_s2;

import java.util.*;
import java.io.*;

/**
 * 1. 먼저 가능한 괄호열인지 검사한다. -> 스택을 통해
 * 2. 가능한 경우라면 괄호를 스택에 넣어 연산한다.
 * 3. 닫히는 괄호일 때, 여는 괄호를 만날 때까지 pop한다
 * 4. 이때 만나는 숫자들은 모두 더해서 괄호에 맞는 값(2,3)을 곱하여 다시 스택에 넣는다.
 * 5. 만약 바로 닫는괄호가 나온다면 스택에 2 혹은 3을 넣어 준다.
 * 6. 모든 괄호가 끝났다면 스택에 남아있는 값들을 모두 더해준다.
 *
 */

public class boj_2504_s2{

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Deque<Character> stk = new ArrayDeque<>();
    private static Deque<Integer> intStk = new ArrayDeque<>();

    private static String str;
    private static int N;

    static boolean isAvailable(){
        for(int i = 0 ; i < N; i++){
            switch (str.charAt(i)){
                case ')' :
                    if(stk.isEmpty() || stk.pop() != '(') return false;
                    break;
                case ']' :
                    if(stk.isEmpty() || stk.pop() != '[') return false;
                    break;
                default :
                    stk.push(str.charAt(i));
            }
        }
        return stk.isEmpty();
    }

    static int calc(){
        for(int i = 0 ; i < N ; i++){
            int temp = 0 ;
            switch(str.charAt(i)){
                case ')' :
                    while(intStk.peek() != -1){
                        temp += intStk.pop();
                    }
                    intStk.pop();
                    if(temp == 0 )  intStk.push(2);
                    else            intStk.push(temp * 2);
                    break;

                case ']' :
                    while(intStk.peek() != -2){
                        temp += intStk.pop();
                    }
                    intStk.pop();
                    if(temp == 0 )  intStk.push(3);
                    else            intStk.push(temp * 3);
                    break;

                case '(' :
                    intStk.push(-1);
                    break;
                case '[' :
                    intStk.push( -2 );
                    break;
            }
        }
        int ans = 0 ;
        while(!intStk.isEmpty()) ans += intStk.pop();
        return ans;
    }

    public static void main(String[] args) throws IOException{
        str = br.readLine();
        N = str.length();

        if(!isAvailable()) {
            System.out.println(0);
            return ;
        }
        System.out.println(calc());
    }
}
```

## 후기

스택을 이용한 단순한 괄호 연산인 줄 알았으나, 올바른 괄호식인걸 고려해야 한다는 점, 괄호는 캐릭터형이고, 연산해서 출력해야 하는 값은 인트형인 점과 같은 신경써야 할 부분들이 있었다.

또한 한번에 올바른 수식인지 검사하며, 계산까지 마치려면 많은 경우의 수가 필요해서 풀이에 어려움이 있었다. 

그래서 알고리즘을 큰 두가지 분류로 구분하여 수행해서 풀었다.