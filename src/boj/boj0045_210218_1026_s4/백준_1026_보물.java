package boj.boj0045_210218_1026_s4;

import java.io.*;
import java.util.*;

public class 백준_1026_보물 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static Integer[] A;
    static Integer[] B;

    static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        A = new Integer[N];
        B = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B, (o1, o2) -> o2 - o1);
    }

    static int solve(){
        int res = 0;
        for(int i = 0 ; i < N ; i++){
            res += A[i] * B[i];
        }
        return res;
    }

    public static void main(String[] args) throws IOException{
        init();
        System.out.println(solve());
    }


}
