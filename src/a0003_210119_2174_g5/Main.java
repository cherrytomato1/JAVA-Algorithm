package a0003_210119_2174_g5;

import java.util.Scanner;

class Robot{
    private static final int[] dr = {-1, 0, 1, 0};          //상 우 하 좌
    private static final int[] dy = {0, 1, 0, -1};          //상수로 설정, 회전을 해야하므로 시계방향으로 방향 배열 설정
    int row;
    int col;
    int dir;                                                //로봇 프로퍼티

    public Robot(int row, int col, int dir){                //로봇 기본값 생성자
        this.row = row;
        this.col = col;
        this.dir = dir;
    }
    private void front(){                                   //전진 기동, 현재좌표 = 현재좌표 + 다음좌표
        row = row + dr[dir];
        col = col + dy[dir];
    }
    private void left(){
        dir = (dir + 1) % 4;
    }            //방향 전환
    private void right(){
        dir = (dir + 3) % 4;
    }           //음수로 가면 제어가 어렵기에 -1 대신 +3
                                                            //기동 메소드는 객체 내의 값만 제어하므로 private
    public void order(char ord){                            //명령 제어용 메소드, 명령에 따라 메소드 호출
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

        int mapC = sc.nextInt();                //맵 크기 x, y 로 입력
        int mapR = sc.nextInt();

        int[][] map = new int[mapR][mapC];      //맵할당, 로봇이 있으면 해당 로봇의 번호를 저장

        int N = sc.nextInt();
        int M = sc.nextInt();                   //로봇 수, 명령 수 입력
        Robot[] rbt = new Robot[N + 1];         //로봇 이름이 1부터 시작이므로 +1, 로봇 번호를 출력해야 하니
                                                //인덱스와 로봇 번호를 맞춰야함

        for (int i = 1 ; i <= N ; i++) {
            int col = sc.nextInt() - 1;         //좌표를 +1해서 주기 때문에 -1 로 받음
            int row = sc.nextInt() - 1;         //x, y 순 좌표

            map[row][col] = i;                  //해당 좌표에 로봇이 있음을 표시
            rbt[i] = new Robot(row, col, rtnDir(sc.next().charAt(0))); //로봇 객체 생성, 위치 및 방향
        }

        for (int i = 0 ; i < M ; i++) {         //명령 수 만큼 반복
            int rbtNum = sc.nextInt();          //제어할 로봇 입력
            char order = sc.next().charAt(0);   //제어명령 입력
            int loop = sc.nextInt();            //명령 반복 수 입력

            for(int j = 0 ; j < loop ; j ++) {  //명령 반복
                int row = rbt[rbtNum].row;      //제어받는 로봇 현재 위치 받아옴
                int col = rbt[rbtNum].col;

                map[row][col] = 0;              //로봇이 이동 후 로봇 자리 표시 제거
                rbt[rbtNum].order(order);       //로봇 기동 명령 수행

                row = rbt[rbtNum].row;          //로봇이 기동할 새로운 좌표 확인
                col = rbt[rbtNum].col;

                if(row >= mapR || row < 0 || col >= mapC || col < 0) {
                    System.out.println("Robot " + rbtNum + " crashes into the wall");
                    return ;
                }                               //다른 로봇이 있으면 충돌

                if(map[row][col] != 0) {
                    System.out.println("Robot " + rbtNum + " crashes into robot " + map[row][col]);
                    return;
                }                               //나가면 충돌
                map[row][col] = rbtNum;         //충돌 없을 시 로봇의 새로운 자리 할당
            }
        }
        System.out.println("OK");               //모든 명령 후 문제 없을 경

    }
}
