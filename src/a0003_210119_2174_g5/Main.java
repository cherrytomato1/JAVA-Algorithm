package a0003_210119_2174_g5;

import java.util.Scanner;

class Robot{
    static int[] dr = {-1, 0, 1, 0};        //상 우 하 좌
    static int[] dy = {0, 1, 0, -1};
    int row;
    int col;
    int dir;

    public Robot(int row, int col){
        this.row = row;
        this.col = col;
    }
    void front(){
        row = row + dr[dir];
        col = col + dy[dir];
    }
    void left(){
        dir = (dir + 1) % 4;
    }
    void right(){
        dir = (dir + 3) % 4;
    }
    void order(char ord){
        switch (ord){
            case 'F' :
                front();
                break;
            case 'L' :
                left();
                break;
            case 'R' :
                right();
                break;
        }
    }


}

public class Main {
    static int rtnDir(char dir){
        switch (dir){
            case 'N' : return 2;
            case 'W' : return 3;
            case 'S' : return 0;
            case 'E' : return 1;
            default : return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int mapR = sc.nextInt();
        int mapC = sc.nextInt();

        int[][] map = new int[mapR][mapC];

        int N = sc.nextInt();
        int M = sc.nextInt();
        Robot[] rbt = new Robot[N + 1];

        System.out.println(mapR+ "! " + mapC);
        for (int i = 1 ; i <= N ; i++) {
            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;

//            System.out.println(row + ", " + col);
            map[row][col] = i;

            rbt[i] = new Robot(col, row);
            rbt[i].dir = rtnDir(sc.next().charAt(0));
        }

        for (int i = 0 ; i < M ; i++) {
            int rbtNum = sc.nextInt();
            char order = sc.next().charAt(0);
            int loop = sc.nextInt();

            for(int j = 0 ; j < loop ; j ++) {
                int row = rbt[rbtNum].row;
                int col = rbt[rbtNum].col;

                System.out.println(col+"!! " + row +rbtNum);
                map[row][col] = 0;
                rbt[rbtNum].order(order);

                row = rbt[rbtNum].row;
                col = rbt[rbtNum].col;

                System.out.println(col+", " + row);
                if(row >= mapR || row < 0 || col >= mapC || col < 0) {
                    System.out.println("Robot " + rbtNum + " crashes into the wall");
                    return ;
                }

                if(map[row][col] != 0) {
                    System.out.println("Robot " + rbtNum + " crashes into robot " + map[row][col]);
                    return;
                }
                map[row][col] = rbtNum;
            }
        }
        System.out.println("OK");

    }
}
