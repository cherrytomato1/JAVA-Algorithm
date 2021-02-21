package boj.boj0044_210218_14620_s2;

import java.util.*;
import java.io.*;

public class 백준_14620_꽃길 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    static final int[][] DIR ={{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int N;
    static int res = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visited;

    static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void find(int row, int col, int val, int cnt){
        if(!checker(row, col))  return ;
        val += marker(row, col, true);

        if(cnt == 3){
            res = res < val ? res : val;
            marker(row, col, false);
            return;
        }
        for(int i = 1 ; i < N - 1; i++){
            for(int j = 1 ; j < N - 1; j++){
                find(i, j, val, cnt + 1);
            }
        }
        marker(row, col, false);
    }

    static boolean checker(int row, int col){
        for(int i = 0 ; i < 5; i++){
            int nr = row + DIR[i][0];
            int nc = col + DIR[i][1];

            if(visited[nr][nc])   return false;
        }
        return true;
    }

    static int marker(int row, int col, boolean type){
        int val = 0;
        for(int i = 0 ; i < 5 ; i++){
            int nr = row + DIR[i][0];
            int nc = col + DIR[i][1];
            visited[nr][nc] = type;
            val += map[nr][nc];
        }
        return val;
    }

    static void solve(){
        for(int r = 1 ; r < N - 1; r++){
            for(int c = 1; c < N - 1; c++){
                find(r, c, 0, 1);
                marker(r, c, false);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        init();
        solve();
        System.out.println(res);
    }
}
