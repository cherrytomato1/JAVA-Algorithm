## 문제 정의

1. A * B 배열에  N 개의 로봇이 있다
2. 로봇의 초기 좌표가 주어지고 NWES 중 하나의 초기 방향을 갖는다
- 좌표는 왼쪽 아래에서 부터 시작한다
1. 로봇들에게 내릴 명령 M개가 있고 순차적으로 실행
    - 명령 L : 왼쪽으로 90도 회전
    - 명령 R : 오른쪽으로 90도 회전
    - 명령 F : 앞으로 한 칸 이동
2. 로봇이 충돌하거나 벽에 부딫히면 종료 및 다른 로봇에 충돌 시 메시지 출력 후 종료
    - Robot X crashes into the wall
    - Robot X crashes int robot Y

## 입력

1. 열 A, 행 B  - 1≤ A,B ≤ 100
2. 로봇의 수 N - 1≤ N ≤ 100
3. 명령 수 M - 1≤ M ≤ 100
4. M개 줄에 명령 x, y, z
    1. 로봇 번호 x
    2. 명령 y - L R F
    3. 각 명령의 반복 회수 z

## 문제 풀이

1. A,B 의 배열을 할당하고 로봇의 클래스 배열 할당 및 명령목록 메소드 작성
    - 좌표는 왼쪽 아래에서 시작하므로 유의해서 작성 → 방향확인을 반대로
2. 각 로봇의 초기상태를 멤버 변수에 입력한다, 맵에 로봇의 위치를 표기한다
3. 명령에 따라 로봇을 이동시킨다. 이동함에 따라 벽과 로봇을 확인한다.
4. 충돌 시 반복문을 빠져나오고 내용을 출력한다.
5. 일반적인 배열과 다르게 시작지점이 왼쪽 아래이며 col, row 순으로 입력값이 들어오므로 좌표값에 주의
    - 왼쪽 아래면 맵을 상하 반전을 한 꼴이 된다
    - 입력 순서에 맞게 row, col 을 할당해야 한다

## 소스코드

```jsx
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

public class myProblem.Elevator {
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
```

## 후기

문제 자체는 2차원 배열 탐색으로 그다지 어렵지 않으나, 왼쪽 아래에서 부터 1,1로 좌표가 시작한다는 점, col, row 순으로 맵 크기 및 로봇 좌표 입력이 들어오므로 할당할 때 주의해야한다. 나는 여기서 많은 시행착오를 거쳤다 ㅠㅠ 아래는 내가 진행하며 했던 실수다.

- 맵을 상하반전이 아니라 180도 회전으로 생각하고 동서남북의 방향 뿐 아니라, 좌회전 우회전도 설정했다.

     → 상하 반전이므로 북쪽과 남쪽의 방향만 바꿔주면 된다.

- col, row 순으로 맵 할당 및 로봇의 좌표가 들어와서 배열을 거꾸로 할당하고, 또 로봇의 좌표도 거꾸로 할당해서 두 개를 계속 바꿔가며 검증하느라 어레이인덱스바운드에러를 수도 없이 봤다 ㅠㅠ

    → 입력 순서만 반대로 들어오므로 받을 때도 순서를 바꿔 받으면 이해하기 쉽게 배열할 수 있다.

또 오늘 클래스와 객체에 대해 학습해서 클래스 및 접근제한자를 활용해서 알고리즘을 풀 수 있었다.