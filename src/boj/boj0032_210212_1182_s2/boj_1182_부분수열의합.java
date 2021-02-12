package boj.boj0032_210212_1182_s2;

import java.io.*;
import java.util.*;

public class boj_1182_부분수열의합 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int S;
    static int cnt = 0;
    static int[] arr;

    static void subset(int idx, int sum){
        if(idx == N) {
            if (sum == S) cnt++;
            return;
        }

        subset(idx + 1, sum + arr[idx]);
        subset(idx + 1, sum);
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        subset(0, 0);
        if(S == 0) cnt--;
        System.out.println(cnt);
    }
}
