package boj.boj0050_210222_20364_s2;

import java.io.*;
import java.util.*;

public class 백준_20364_부동산다툼 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] tree;
    static int N;
    static int Q;

    static void init() throws IOException{
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new int[N + 1];
        for(int i = 0 ; i < Q ; i++){
            sb.append(solve(Integer.parseInt(br.readLine()))).append("\n");
        }
    }

    static int solve(int node){
        int cnt = 0;
        for(int i = node ; i != 1; i >>= 1){
            if(tree[i] != 0) cnt = i;
        }
        if(cnt == 0)    tree[node] = 1;
        return cnt;
    }

    public static void main(String[] args) throws IOException{
        init();
        System.out.println(sb);
    }
}
