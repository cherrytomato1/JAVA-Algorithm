package a0002_210119_1051_s3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int row = sc.nextInt();                             //직사각형의 세로 입력
        int col = sc.nextInt();                             //직사각형의 가로 입력

        int[][] arr = new int[row][col];                    //가로세로 크기만큼 배열 선언 및 할당
        int res = 1;                                        //결과로 가질 정사각형의 넓이, 초기값 넓이 1의 정사각형
        int val;                                            //비교를 위한 임시 값 저장공간

        for(int y = 0 ; y < row ; y ++ )
        {
            String str = sc.next();                         //열 단위로 입력받음
            for(int x = 0 ; x < col ; x ++)
                arr[y][x] = str.charAt(x) - '0';            //열 단위로 입력받은 값을 배열에 할당( 유니코드 값을 숫자로 )
        }

        for(int y = 0 ; y < row - 1 ; y ++)                 //직사각형 세로 탐색
            for(int x = 0 ; x < col - 1 ; x ++)             //직사각형 가로 탐색
                for(int diff = 0 ; diff + x < col && diff + y < row ; diff++)
                //하나의 좌표마다 가로로 끝까지 검사함, 검사 시 직사각형 인덱스 범위 초과 검사
                {
                    val = arr[y][x];                        //비교를 위해 좌표의 값 저장
                    if(val == arr[y][diff + x] &&  val == arr[y + diff][x] && val == arr[y + diff][diff + x])
                        // 같은 거리만큼 떨어져 있는 정사각형을 이루는 좌표의 값이 모두 같을 때
                        res = res > diff + 1 ? res : (diff + 1);
                        //max 동작, 거리 -> 길이를 위해 + 1
                }

        System.out.println(res * res); //길이 * 길이
    }
}
