package boj.boj0046_210221_2665_g4;

import java.io.*;
import java.util.*;

public class 백준_2665_미로만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int N ;
    static boolean[][] map;
    static int[][] visitedCnt;

    static void init() throws IOException{
        N = Integer.parseInt(br.readLine());

        map = new boolean[N][N];
        visitedCnt = new int[N][N];

        for(int r = 0 ; r < N ; r++){
            char[] in = br.readLine().toCharArray();
            for(int c = 0; c < N ; c++){
                map[r][c] = in[c] == '1';
            }
            Arrays.fill(visitedCnt[r], Integer.MAX_VALUE);
        }
    }
    static int res = Integer.MAX_VALUE;
    static boolean dfs(int row, int col, int cnt){
        visitedCnt[row][col] = cnt;
        for(int i = 0; i < 4; i++){
            int nr = row + DIR[i][0];
            int nc = col + DIR[i][1];

            if(nr >= N || nr < 0 || nc >= N || nc < 0)  continue;
            int nextCnt = map[nr][nc] ? cnt : cnt + 1;
            if(visitedCnt[nr][nc] <= nextCnt)           continue;
            if(nr == N - 1 && nc == N - 1){
                if(nextCnt == 0)    {
                    res = 0;
                    return true;
                }

                res = res < nextCnt ? res : nextCnt;
                return false;
            }

            if(dfs(nr, nc, nextCnt))     return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        init();
        dfs(0, 0, 0);
        System.out.println(res);
    }
}
