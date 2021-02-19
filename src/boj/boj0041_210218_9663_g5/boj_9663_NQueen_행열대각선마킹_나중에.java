package boj.boj0041_210218_9663_g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9663_NQueen_행열대각선마킹_나중에 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int ans;
    static int[][] mask;

    private static void nQueen(int row, int col) {
        if(mask[row][col] != 0)     return;
        if(row == N) {
            ans++;
            return;
        }
        for(int c = 0 ; c < N ; c++){
            masking(row, col, true);
            nQueen(row + 1, c);
            masking(row, col, false);
        }
    }

    private static void masking(int row, int col, boolean flag){
        int val = flag ? 1 : -1;

        for(int i = row + 1, c1 = col + 1, c2 = col - 1; i < N ; i++, c1 ++, c2 --){
            mask[i][col] += val;
            if(c1 < N) mask[i][c1] += val;
            if(c2 >= 0) mask[i][c2] += val;
        }
    }

    private static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        mask = new int[N][N];
    }


    public static void main(String[] args) throws IOException{
        init();
        nQueen(0, 0);
        System.out.println(ans);
    }

}
