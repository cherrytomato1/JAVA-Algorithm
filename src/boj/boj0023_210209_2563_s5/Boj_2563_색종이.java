package boj.boj0023_210209_2563_s5;

import java.io.*;
import java.util.*;

public class Boj_2563_색종이 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;
//    static StringBuilder sb = new StringBuilder;
    static final int MAX = 100;
    static final int SIZE = 10;
    static boolean[][] map = new boolean[MAX][MAX];


    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T ; tc++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            for(int r = row ; r < row + SIZE ; r++){
                for(int c = col ; c < col + SIZE ; c++ ){
                    map[r][c] = true;
                }
            }
        }
        int ans = 0;
        for(int r = 0; r < MAX ; r++){
            for(int c = 0 ; c < MAX ; c++){
                if(map[r][c]) ans++;
            }
        }
        System.out.println(ans);
    }
}
