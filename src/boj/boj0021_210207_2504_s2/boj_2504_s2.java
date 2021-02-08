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
