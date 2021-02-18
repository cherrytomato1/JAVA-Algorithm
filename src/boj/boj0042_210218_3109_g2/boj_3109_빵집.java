package boj.boj0042_210218_3109_g2;

import java.io.*;
import java.util.*;

public class boj_3109_빵집 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R;
    static int C;
    static boolean[][] map;

    static final int[] rowDir = {-1, 0, 1};

    static void init() throws IOException{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new boolean[R][C];

        for(int i = 0 ; i < R ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < C ; j++){
                map[i][j] = str.charAt(j) == 'x';
            }
        }
    }

    static int ans;
    static boolean dfs(int row, int col){
        if( row < 0 || row >= R || map[row][col])   return false;
        map[row][col] = true;
        if(col == C - 1){
            ans++;
            return true;
        }
        for(int i = 0 ; i < 3; i++){
            if(dfs(row + rowDir[i], col + 1))   return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        init();
        for(int i = 0 ; i < R ; i++){
            dfs(i, 0);
        }
        System.out.println(ans);
    }
}
