package boj.boj0043_210218_1987_g4;

import java.io.*;
import java.util.*;

public class Boj_1987_알파벳 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int[][] DIR = {{-1, 0}, {1 ,0 }, {0, -1}, {0, 1}};

    static char[][] map;
    static int[][] visitedFlag;

    static int R;
    static int C;

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visitedFlag = new int[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }
    }

    static int res = Integer.MIN_VALUE;
    static void dfs(int row, int col, int flag, int cnt){
        res = res > cnt ? res : cnt;
        for(int i = 0 ; i < 4; i++){
            int nr = row + DIR[i][0];
            int nc = col + DIR[i][1];

            if(nr >= R | nr < 0 | nc >= C | nc < 0) continue;

            int nextFlag = 1 << map[nr][nc] - 'A';
            if(visitedFlag[nr][nc] == (flag | nextFlag) || (flag & nextFlag) != 0)     continue;

            visitedFlag[nr][nc] = flag | nextFlag;
            dfs(nr, nc, flag | nextFlag, cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException{
        init();
        dfs(0,0, 1 << map[0][0] - 'A', 1);

        System.out.println(res);
    }

}
