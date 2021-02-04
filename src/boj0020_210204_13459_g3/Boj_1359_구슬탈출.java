package boj0020_210204_13459_g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1359_구슬탈출 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st ;

    private static int[][][][] visitedCounter;
    private static char[][] map;
    private static int N, M;

    static final int MAX_CNT = 11;
    /**                       상  하  좌  우 */
    static final int[] dr = { -1, 1, 0, 0};
    static final int[] dc = { 0, 0, -1, 1};

    static int Ans = Integer.MAX_VALUE;
    private static void f(int rowRed, int colRed, int rowBlue, int colBlue, int cnt){
        if(cnt == MAX_CNT || cnt - MAX_CNT >= visitedCounter[rowRed][colRed][rowBlue][colBlue] ) {
            return ;
        }
        if(map[rowRed][colRed] == 'O'){
            Ans = Ans < cnt ? Ans : cnt;
            return ;
        }
        visitedCounter[rowRed][colRed][rowBlue][colBlue] = cnt - MAX_CNT;

        int nrRed, ncRed, nrBlue, ncBlue;
        dirLoop :
        for(int dir = 0 ; dir < 4; dir++){
            for(int i = 1; ; i++){
                nrRed = rowRed + dr[dir] * i;
                ncRed = colRed + dc[dir] * i;
                if(map[nrRed][ncRed] == '#') {
                    nrRed -= dr[dir];
                    ncRed -= dc[dir];
                    break ;
                }
                if(map[nrRed][ncRed] == 'O') break;
            }

            for(int i = 1; ; i++){
                nrBlue = rowBlue + dr[dir] * i;
                ncBlue = colBlue + dc[dir] * i;

                if(map[nrBlue][ncBlue] == '#') {
                    nrBlue -= dr[dir];
                    ncBlue -= dc[dir];
                    break;
                }
                if(map[nrBlue][ncBlue] == 'O') continue dirLoop;
            }

            if(nrRed == nrBlue && ncRed == ncBlue){
                switch(dir){
                    case 0 :
                        if (rowRed > rowBlue)   nrRed++;
                        else                    nrBlue++;
                        break;
                    case 1 :
                        if (rowRed < rowBlue)   nrRed--;
                        else                    nrBlue--;
                        break;
                    case 2 :
                        if (colRed > colBlue)   ncRed++;
                        else                    ncBlue++;
                        break;
                    case 3 :
                        if (colRed < colBlue)   ncRed--;
                        else                    ncBlue--;
                }
            }
            f(nrRed, ncRed, nrBlue, ncBlue, cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visitedCounter = new int[N][M][N][M];
        map = new char[N][M];

        int rowRed = 0, colRed = 0, rowBlue = 0, colBlue = 0;

        String str = null;
        for(int i = 0 ; i < N ; i++){
            str = br.readLine();
            for(int j = 0; j < M ; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'R') {
                    rowRed = i;
                    colRed = j;
                    map[i][j] = '.';
                } else if(map[i][j] == 'B') {
                    rowBlue = i;
                    colBlue = j;
                    map[i][j] = '.';
                }
            }
        }
        f(rowRed, colRed, rowBlue, colBlue, 0);
        System.out.println(Ans == Integer.MAX_VALUE ? 0 : 1 );
    }
}
