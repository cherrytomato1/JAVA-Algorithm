package boj.boj0048_210222_13300_b2;

import java.io.*;
import java.util.*;

public class 백준_13300_방배정 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int K;
    static int[][] students;
    static int rooms;

    static void solve() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        students = new int[2][6];
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken()) ;
            int grades = Integer.parseInt(st.nextToken()) - 1;

            if(students[gender][grades] == 0)   rooms++;
            if(++students[gender][grades] == K) students[gender][grades] -= K;
        }
    }

    public static void main(String[] args) throws IOException{
        solve();
        System.out.println(rooms);
    }
}
