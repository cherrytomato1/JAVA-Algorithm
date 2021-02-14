package boj.boj0034_210214_1018_s5;

import java.util.*;
import java.io.*;

public class boj_1018_체스판다시칠하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    //w = true , b = false
    static final boolean[][] CHESS_MAP =
                    {
                        {true, false, true, false, true, false, true, false},
                        {false, true, false, true, false, true, false, true},
                        {true, false, true, false, true, false, true, false},
                        {false, true, false, true, false, true, false, true},
                        {true, false, true, false, true, false, true, false},
                        {false, true, false, true, false, true, false, true},
                        {true, false, true, false, true, false, true, false},
                        {false, true, false, true, false, true, false, true},
                    };

    static int N;
    static int M;
    static boolean[][] arr ;

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new boolean[N][M];

        for(int r = 0 ; r < N ; r++){
            String str = br.readLine();
            for(int c = 0 ; c < M ; c++){
                arr[r][c] = str.charAt(c) == 'W';
            }
        }
    }

    static int solve(){
        int res = Integer.MAX_VALUE;
        for(int r = 0 ; r <= N - 8 ; r++){
            for(int c = 0 ; c <= M - 8 ; c++){
                int cnt = check(r, c);
                res = res < cnt ? res : cnt;
            }
        }
        return res;
    }

    static int check(int row, int col){
        int cnt1 = 0;
        int cnt2 = 0;

        for(int r = row; r < row + 8 ; r++){
            for(int c = col ; c < col + 8; c++){
                if(arr[r][c] == CHESS_MAP[r - row][c - col])    cnt1++;
                else                                            cnt2++;
            }
        }

        return cnt1 < cnt2 ? cnt1 : cnt2;
    }

    public static void main(String[] args) throws IOException{
        input();
        System.out.println(solve());
    }
}
