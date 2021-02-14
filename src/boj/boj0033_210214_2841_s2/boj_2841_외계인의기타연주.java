package boj.boj0033_210214_2841_s2;

import java.util.*;
import java.io.*;

public class boj_2841_외계인의기타연주 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    static Deque<Integer>[] dq = new ArrayDeque[6];
    static int P;
    static int N;

    static int solve() throws IOException{
        int cnt = 0 ;
        loop1 :
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken()) - 1;
            int fret = Integer.parseInt(st.nextToken());
            while(!dq[line].isEmpty() && dq[line].peek() >= fret){
                if(fret == dq[line].pop())  {
                    dq[line].push(fret);
                    continue loop1;
                }
                cnt++;
            }
            dq[line].push(fret);
            cnt++;

        }
        return cnt;
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < 6; i ++)    dq[i] = new ArrayDeque<>();

        System.out.println(solve());
    }
}

