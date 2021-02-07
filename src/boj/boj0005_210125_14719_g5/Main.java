/**
 * https://www.acmicpc.net/problem/14719
 * 백준 14719번 빗물
 */

package boj.boj0005_210125_14719_g5;

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


        /**
         * 1. 모든 row와 col 에서 반복한다. col에서 반복하다가 벽을 만나면 flag를 통해 벽이 있음을 알린다.
         * 2. flag가 있으면 한쪽 벽으로 막혀있고 보고, 빈 공간은 물이 찰 수 있는 공간으로 보아 flag == true 일때 그 오른쪽의 빈공간을 count
         * 3. 다음 벽을 만났을때 count 된 모든 빈 공간은 갇혀있다고 보고 answer에 +한다. 반대쪽 벽이 막혀있지 않으면 버린다
         * 4. 매 반복마다 flag와 count를  초기화 해주어 해당 row에 대한 값만 계산한다.
         */
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
