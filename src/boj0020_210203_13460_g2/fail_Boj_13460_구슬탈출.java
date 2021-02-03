package boj0020_210203_13460_g2;

import java.io.*;
import java.util.*;

public class fail_Boj_13460_구슬탈출 {

    static char[][] map;
    static boolean[][] visited;
    static int N;
    static int M;


    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    static int res = Integer.MAX_VALUE;

    static void move(int cnt, int rowR, int colR, int rowB, int colB){
        if(visited[rowR][colR] ) return;

//        System.out.println(" RED : " + rowR + ", " + colR);
//        System.out.println(" BLUE : " + rowB + ", " + colB);
        System.out.println(cnt);
        visited[rowR][colR] =  true;


        if(map[rowR][colR] == '0'){
            res = res < cnt ? res : cnt ;
        }

        int nr, nc;
        for(int i = 0 ; i < 4 ; i++){
            boolean rFlag = true;
            boolean bFlag = true;

            map[rowB][colB] = 'B';
            map[rowR][colR] = 'R';

            for(char[] temp : map){
                System.out.println(temp);
            }
            MoveLoop :
            while(rFlag || bFlag){
//                System.out.println(cnt);
//                System.out.println(" RED : " + rowR + ", " + colR + " - " + rFlag);
//                System.out.println(" BLUE : " + rowB + ", " + colB+ " - " + bFlag);
                if(rFlag) {
                    nr = rowR + dr[i];
                    nc = colR + dc[i];
//                    System.out.println(" nRED : " + nr + ", " + nc + " - " + map[nr][nc]);
                    switch (map[nr][nc]) {
                        case '.':
                            map[rowR][colR] = '.';
                            map[rowR = nr][colR = nc] = 'R';
                            break;
                        case '#': case 'B' : case 'R' :
                            rFlag = false;
                            break;
                        case 'O':
                            System.out.println("goal~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + (cnt));
                            map[rowR][colR] = '.';
                            map[rowB][colB] = '.';
                            res = res < cnt ? res : cnt;
                            return;
                    }

                }

                if(bFlag) {
                    nr = rowB + dr[i];
                    nc = colB + dc[i];
//                    System.out.println(map[nr][nc]);
                    switch (map[nr][nc]) {
                        case '.':
                            map[rowB][colB] = '.';
                            map[rowB = nr][colB = nc] = 'B';

                            break;
                        case '#': case 'B' : case 'R' :
                            bFlag = false;
                            break;
                        case 'O':
                            map[rowB][colB] = '.';
                            bFlag = true;
                            break MoveLoop;
                    }
                }
            }
            map[rowB][colB] = '.';
            map[rowR][colR] = '.';

            if(!bFlag)  move(cnt + 1, rowR, colR, rowB, colB);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];
        int rowR = 0, colR = 0, rowB = 0, colB = 0;

        for(int i = 0 ; i < N ; i ++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = str.charAt(j);
                switch(map[i][j]){
                    case 'R' :
                        rowR = i;
                        colR = j;
                        break;
                    case 'B' :
                        rowB = i;
                        colB = j;
                        break;
                }
            }
        }
        move(1, rowR, colR, rowB, colB);
        System.out.println(res);
//        for(char[] temp : map){
//            System.out.println(temp);
//        }
    }
}
