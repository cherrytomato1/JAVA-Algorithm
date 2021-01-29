package boj0004_210121_2447_s1;

import java.util.Scanner;

public class Main {
    static char[][] map;
    static int cnt, blockRow, blockCol;
    static int N ;
    static void setBlock(){
        cnt = 0;
        blockCol += 3;
        if(blockCol >= N){
            blockCol = 0;
            blockRow += 3 ;
        }

    }
    static void f(int n){
        System.out.println(n);
        if(n == 1){
            map[cnt / 3 + blockRow][cnt % 3 + blockCol] = '*';
            System.out.println(blockRow/3+ ", " + blockCol/3 + "], [" + cnt/3 + ", " + cnt % 3 + " = *");
            cnt ++;
            if (cnt == 9 ) { setBlock(); }
            return ;
        }
        else if(n == -1) {
            map[cnt / 3 + blockRow][cnt % 3 + blockCol] = ' ';
            System.out.println((cnt / 3 + blockRow) + ", " + (cnt % 3 + blockCol) + " = 0");
            cnt ++;
            if (cnt == 9 ) { setBlock(); }
            return ;
        }

        for(int i = 0 ; i < 4; i ++) { f(n / 3); }
        if (n <= -1) { f(n / 3 ); }
        else { f( - ( n / 3)) ; }
        for(int i = 0 ; i < 4; i ++) { f(n / 3); }


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        map = new char[N][N];
        f(N);
        for(int r = 0; r < N; r ++) {
            for (int c = 0; c < N; c++)
                System.out.print(map[r][c]);
            System.out.println();
        }
    }
}
