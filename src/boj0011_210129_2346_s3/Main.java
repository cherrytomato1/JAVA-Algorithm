package boj0011_210129_2346_s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static Deque<Integer> dq = new ArrayDeque<>();
    static int N;
    static int[] M;
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        M = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N ; i++){
            M[i] = Integer.parseInt(st.nextToken());
            dq.add(i);
        }
    }

    static void solve(){
        int num;
        boolean dir;
        while(true){
            System.out.print((num = dq.removeFirst()) + " ");
            if(dq.isEmpty()){
                break;
            }

            if(M[num] < 0){
                while(M[num]++ != 0){
                    dq.addFirst(dq.removeLast());
                }
            } else if(M[num]-- > 0){
                while(M[num]-- != 0){
                    dq.addLast(dq.removeFirst());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
