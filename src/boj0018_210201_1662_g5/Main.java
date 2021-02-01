package boj0018_210201_1662_g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;


public class Main {
    static String S;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    static Deque<Character> dq = new ArrayDeque<>();

    static int f(int idx){

        if(idx == S.length()){
            return 0;
        }
        if(S.charAt(idx) == ')'){
            int cnt = 0;
            while(dq.getFirst() != '('){
                dq.pop();
                cnt++;
            }
            dq.pop();
            return (dq.pop() - '0') * cnt + f(idx + 1);
        }

        dq.push(S.charAt(idx));
        return f(idx + 1);
    }


    public static void main(String[] args) throws IOException {
        S = br.readLine();
        System.out.println(f(0));
    }
}
