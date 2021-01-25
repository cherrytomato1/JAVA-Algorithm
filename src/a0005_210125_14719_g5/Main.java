package a0005_210125_14719_g5;

import java.util.Scanner;

public class Main {
    static boolean[][] map;
    static int answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int row = sc.nextInt();
        int col = sc.nextInt();
        int input ;

        map = new boolean[row][col];

        for(int c = 0; c < col ; c++){
            input = sc.nextInt();
            for(int r = row - 1, j = 0; j < input ; r--, j++)
                map[r][c] = true;
        }


        for(int r = 0; r < row ; r++) {
            boolean flag = false;
            int cnt = 0;
            for(int c = 0 ; c < col ; c++) {
                if(map[r][c]) {
                    flag = true;
                    answer += cnt;
                    cnt = 0;
                }
                else if(!map[r][c] && flag)
                    cnt++;
            }
        }
        System.out.println(answer);

    }
}
