package boj.boj0021_210207_2504_s2;


import java.util.*;
import java.io.*;

/**
 * 괄호를 스택에 담아 연산한다. 비어있지 않을 경우 0
 * 1. 여는 괄호 모양을 모두 스택에 담는다.
 * 2. 담는 괄호가 나오면 3가지로 나눈다
 *   1. 수가 나온다. 닫는 괄호가 나올 때까지 스택을 푸
 *   2. 알맞지 않은 괄호가 나온다. false 플래그를 키고 프로그램을 종료한다.
 *   3. 알맞은 괄호가 나온다. 지금까지 검사했던 모든 값을
 */



public class boj_2504_s2{

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static Deque<Integer> dq = new ArrayDeque<>();

    static int f(int val, int prev){
        if (prev == -3){
            return val * 2 ;
        }
        if (next == -1){
            return val * 3;
        }

        switch
    }


    public static void main(String[] args) throws IOException{
        String str = br.readLine();
        int ans = 0;
        char c;

        loop :
        for(int i = 0 ; i < str.length() ; i++){
            c = str.charAt(i);

        System.out.println(sb.append(dq.pop()).toString());
    }
}
