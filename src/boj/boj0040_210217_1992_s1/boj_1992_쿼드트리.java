package boj.boj0040_210217_1992_s1;

import java.io.*;

public class boj_1992_쿼드트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static boolean[][] map;
    static int N;

    static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < N ; j++){
                map[i][j] = str.charAt(j) == '1';
            }
        }
    }

    static int check(int size, int row, int col){
        boolean flag = map[row][col];
        for(int r = row; r < row + size ; r++) {
            for (int c = col; c < col + size; c++) {
                if (map[r][c] != flag) return -1;
            }
        }
        return flag ? 1 : 0;
    }

    static String f(int size, int row, int col){
        int check = check(size, row, col);
        if(check == 1)        return "1";
        else if(check == 0)   return "0";
        StringBuilder sb = new StringBuilder();

        size /= 2;

        sb.append("(");
        sb.append(f(size, row, col));
        sb.append(f(size, row, col + size));
        sb.append(f(size, row + size, col));
        sb.append(f(size, row + size, col + size));
        sb.append(")");

        return sb.toString();
    }

    public static void main(String[] args) throws IOException{
        init();
        System.out.println(f(N,0,0));
    }
}
