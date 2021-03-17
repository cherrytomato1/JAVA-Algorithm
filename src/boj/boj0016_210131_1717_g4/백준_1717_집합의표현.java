package boj.boj0016_210131_1717_g4;

import java.util.*;
import java.io.*;

public class 백준_1717_집합의표현 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] arr;
    static int N;
    static int M;

    static void init() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for(int i = 0 ; i <= N ; i++){
            arr[i] = i;
        }
    }

    static void merge(int idx1, int idx2){
        if(idx1 > idx2) {
            int temp = idx1;
            idx1 = idx2;
            idx2 = temp;
        }
        idx1 = searchParent(idx1);
        idx2 = searchParent(idx2);

        if(idx1 != idx2)    arr[idx2] = idx1;
    }

    static int searchParent(int idx){
        if(idx == arr[idx])     return idx;
        else                    return arr[idx] = searchParent(arr[idx]);
    }

    static void solve() throws IOException{
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int idx1 = Integer.parseInt(st.nextToken()) ;
            int idx2 = Integer.parseInt(st.nextToken()) ;
            if(cmd == 0)    merge(idx1, idx2);
            else {
                if(searchParent(idx1) == searchParent(idx2))    sb.append("YES").append("\n");
                else                                            sb.append("NO").append("\n");
            }
        }
    }

    public static void main(String[] args) throws IOException{
        init();
        solve();
        System.out.println(sb);
    }
}
