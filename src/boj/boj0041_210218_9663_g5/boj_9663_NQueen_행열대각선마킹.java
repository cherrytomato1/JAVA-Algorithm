package boj.boj0041_210218_9663_g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9663_NQueen_행열대각선마킹 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;

    static int ans;
    private static void nQueen(int row, int rowFlag, int colFlag) {
//        if(!check())    return;
        if(row == N) {
            ans++;
            return;
        }
        for(int c = 0 ; c < N ; c++){
            nQueen(row + 1, rowFlag, colFlag |= 1 << c );
        }
    }

    private static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
    }


    public static void main(String[] args) throws IOException{
        init();
        System.out.println(ans);
    }

}
