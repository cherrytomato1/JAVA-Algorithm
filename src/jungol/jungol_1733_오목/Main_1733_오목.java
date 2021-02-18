package jungol.jungol_1733_오목;

import java.io.*;
import java.util.*;

public class Main_1733_오목 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 19;
    static final int[][] DIR = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}};

    static char[][] map = new char[MAX][MAX];

    static void init() throws IOException{
        for(int r = 0 ; r < MAX; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < MAX ; c++){
                map[r][c] = st.nextToken().charAt(0);
            }
        }
    }

    static int[] res;
    static int solve(){
        for(int r = 0 ; r < MAX ;r++){
            for(int c = 0 ; c < MAX ; c++){
                if(map[r][c] == '0')    continue;
                for(int i = 0 ; i < 4; i++){
                    if(!dfs(r, c, i, 0, map[r][c]))             continue;

                    res = new int[]{r + 1, c + 1};
                    return map[r][c] - '0';
                }
            }
        }
        return -1;
    }

    static boolean dfs(int row, int col, int dir, int level, char type){
        if(level == 0 ){
            int pr = row - DIR[dir][0];
            int pc = col - DIR[dir][1];

            if(pr >= 0 && pr < MAX && pc >= 0 && map[pr][pc] == map[row][col])    {
                return false;
            }
        }
        if(map[row][col] != type)   return false;

        int nr = row + DIR[dir][0];
        int nc = col + DIR[dir][1];


        if(level == 4){
            if(nr >= MAX | nc >= MAX | nr < 0 )     return true;
            return map[nr][nc] != type;
        }
        if(nr >= MAX | nc >= MAX | nr < 0 )     return false;

        return dfs(nr, nc, dir, level + 1, type);
    }

    public static void main(String[] args) throws IOException{
        init();
        int i = solve();
        if(i == -1) {
            System.out.println(0);
            return;
        }
        sb.append(i).append("\n").append(res[0]).append(" ").append(res[1]);
        System.out.print(sb);
    }
}
