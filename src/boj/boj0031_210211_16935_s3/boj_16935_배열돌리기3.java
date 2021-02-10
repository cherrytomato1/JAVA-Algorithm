package boj.boj0031_210211_16935_s3;

import java.util.*;
import java.io.*;

public class boj_16935_배열돌리기3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;
    static StringBuffer sb = new StringBuffer();

    static int N;
    static int M ;
    static int R;
    static int[][] arr;

    static void flip(int dir) {
        if (dir == 0) {
            int[] temp;
            for (int i = 0, j = N - 1; i < N / 2; i++, j--) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        } else if (dir == 1) {
            for (int c = 0, c2 = M - 1; c < M / 2; c++, c2--) {
                for (int r = 0; r < N; r++) {
                    int temp = arr[r][c];
                    arr[r][c] = arr[r][c2];
                    arr[r][c2] = temp;
                }
            }
        }
    }

    static void turn(int dir){
        int[][] temp = new int[M][N];

        for(int r = 0; r < N ; r ++){
            for(int c = 0 ; c < M; c++){
                temp[c][r] = arr[r][c];
            }
        }

        int tempInt = N;
        N = M;
        M = tempInt;
        arr = temp;

        if(dir == 0)    flip(1);
        else if(dir == 1)  flip(0);
    }

    static void turn2(int dir){
        int[][] temp = new int[N][M];

        if(dir == 0) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (r < N / 2 && c < M / 2)         temp[r][c + M / 2] = arr[r][c];
                    else if (r < N / 2 && c >= M / 2)   temp[r + N / 2][c] = arr[r][c];
                    else if (r >= N / 2 && c >= M / 2)  temp[r][c - M / 2] = arr[r][c];
                    else if (r >= N / 2 && c < M / 2)   temp[r - N / 2][c] = arr[r][c];
                }
            }
        }

        else if(dir == 1) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (r < N / 2 && c < M / 2)         temp[r + N / 2][c] = arr[r][c];
                    else if (r < N / 2 && c >= M / 2)   temp[r][c - M / 2] = arr[r][c];
                    else if (r >= N / 2 && c >= M / 2)  temp[r - N / 2][c] = arr[r][c];
                    else if (r >= N / 2 && c < M / 2)   temp[r][c + M / 2] = arr[r][c];
                }
            }
        }
        arr = temp;
    }



    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for(int r = 0 ; r < N ; r ++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < M ; c++){
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < R ; i++){
            int c = st.nextToken().charAt(0);
            switch(c){
                case '1' :
                    flip(0);
                    break;
                case '2' :
                    flip(1);
                    break;
                case '3' :
                    turn(0);
                    break;
                case '4' :
                    turn(1);
                    break;
                case '5' :
                    turn2(0);
                    break;
                case '6' :
                    turn2(1);
                    break;
            }
        }
        for(int r= 0 ; r < N ; r++){
            for(int c = 0 ; c < M ; c++){
                sb.append(arr[r][c]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
