package boj.boj0041_210218_9663_g5;

import java.io.*;

public class boj_9663_NQueen_col값저장 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] col;
    static int ans;
    private static void nqueen(int row){
        if (!check(row)) return;

        if(row == N){
            ans ++;
            return;
        }

        for(int c = 1 ; c <= N; c++){
            col[row + 1] = c;
            nqueen(row + 1);
        }
    }

    private static boolean check(int row){
        for(int r = 1 ; r < row ; r ++){
            if(col[r] == col[row])                                  return false;
            if(Math.abs(col[row] - col[r]) == Math.abs(row - r))    return false;
        }
        return true;
    }

    private static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        col = new int[N + 1];
    }


    public static void main(String[] args) throws IOException{
        init();
        nqueen(0);
        System.out.println(ans);
    }

}
