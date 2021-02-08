package boj.boj0012_210129_1913_s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int val;
    static int[][] map;
    static BufferedReader br;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int dir = 0;
    static int row;
    static int col;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        val = Integer.parseInt(br.readLine());

        map = new int[N][N];
        row = N/2;
        col = N/2;

        for(int i = 1 ; i <= N * N; i++){
            map[row][col] = i ;

            int countNot0 = 0;
            for(int j = 0 ; j < 4; j++){
                if(row + dr[j] >= N || row + dr[j] < 0 || col + dc[j] >=N || col + dc[j] < 0) {
                    continue;
                }
                if(map[row + dr[j]][col + dc[j]] != 0) countNot0++;
            }

            if(countNot0 == 1)  dir = (dir + 1) % 4;

            row = row + dr[dir];
            col = col + dc[dir];
        }

        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < N; j++){
                System.out.print(map[i][j] + " ");
                if(map[i][j] == val){
                    row = i + 1;
                    col = j + 1;
                }
            }
            System.out.println();
        }
        System.out.println(row + " " + col);

    }

}
