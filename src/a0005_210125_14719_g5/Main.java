package a0005_210125_14719_g5;

import java.util.Scanner;

public class Main {
    static boolean[][] map;                     //벽의 위치를 표시할 맵
    static int answer;                          //제출 답
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int row = sc.nextInt();                 //맵 크기 받기
        int col = sc.nextInt();
        int input ;

        map = new boolean[row][col];            //맵 할당

        for(int c = 0; c < col ; c++){          //매 col 당 벽돌이 몇 개 쌓이는지
            input = sc.nextInt();               //입력받기
            for(int r = row - 1, j = 0; j < input ; r--, j++) // 벽돌이 아래에서부터 쌓이므로 아래에서부터 입력값의 개수까지 반복
                map[r][c] = true;               //벽돌이 있으면 true
        }


        /**
         * 1. 모든 row와 col 에서 반복한다. col에서 반복하다가 벽을 만나면 flag를 통해 벽이 있음을 알린다.
         * 2. flag가 있으면 한쪽 벽으로 막혀있고 보고, 빈 공간은 물이 찰 수 있는 공간으로 보아 flag == true 일때 그 오른쪽의 빈공간을 count
         * 3. 다음 벽을 만났을때 count 된 모든 빈 공간은 갇혀있다고 보고 answer에 +한다. 반대쪽 벽이 막혀있지 않으면 버린다
         * 4. 매 반복마다 flag와 count를  초기화 해주어 해당 row에 대한 값만 계산한다.
         */
        for(int r = 0; r < row ; r++) {         //모든 row에서 반복
            boolean flag = false;               //flag 및 cnt 초기화
            int cnt = 0;
            for(int c = 0 ; c < col ; c++) {    //매 col에서 반복
                if(map[r][c]) {                 //벽이 있으면
                    flag = true;                //한쪽벽이 막혀있음을 flag
                    answer += cnt;              //지금까지 빈 공간의 수를 모두 더한다(반대쪽이 막혀있지 않다면 cnt는 0이다
                    cnt = 0;                    //더한 후 cnt를 초기화한다.
                }
                else if(!map[r][c] && flag)     //flag가 true이고(한쪽 벽이 막혀있고), 빈 공간이라면 cnt++
                    cnt++;
            }
        }
        System.out.println(answer);

    }
}
