package boj.boj0004_210121_2447_s1;

import java.util.*;
import java.io.*;

public class boj_2447_별찍기{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static boolean[][] arr;
    static int N ;

    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        arr = new boolean[N][N];
    }

    static void draw(int row, int col, boolean type){
        for(int r = 0 ; r < 3; r++){
            for(int c = 0 ; c < 3 ; c++){
                if(!type || (r == 1 && c == 1))     arr[r + row][c + col] = false;
                else                                arr[r + row][c + col] = true;
            }
        }
    }

    static void f(int num, int row, int col, boolean type){
        if(num == 3){
            draw(row, col, type);
            return ;
        }
        for(int r = 0 ; r < 3 ; r ++){
            for(int c = 0; c < 3 ; c ++){
                if(r == 1 && c == 1)    f(num/3, row + (num/3 * r), col + (num/3 * c), false);
                else                    f(num/3, row + (num/3 * r), col + (num/3 * c), type);
            }
        }
    }

    static void print(){
        for(int r = 0 ; r < N ; r++){
            for(int c = 0 ; c < N ; c++) {
                sb.append(arr[r][c]? '*' : ' ');
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


    public static void main(String[] args) throws IOException{
        input();
        f(N, 0, 0, true);
        print();

    }


}