package boj0009_210126_10811_b2;

import java.util.Scanner;

/**
 *
 *  1. 입력 N = 바구니 수 , M = tc 수
 *  2. 매 tc 마다 i, j , i부터 j 까지 뒤집기
 *
 *
 *  1. 순서대로 배열에 숫자 넣기
 *  2. tc 만큼 반복
 *  3. i , j 받고 반복문 거꾸로 돌려서
 *     i++ , j--
 */

public class Main {
    static int arr[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        arr = new int[N + 1];

        for(int i = 1; i <= N; i++)
            arr[i] = i;

        for(int tc = 0 ; tc < M ; tc++) {
            int i = sc.nextInt();
            int j = sc.nextInt();

            for (int x = i, y = j; x <= (i + j - 1) / 2; x++, y--) {
                int temp = arr[x];
                arr[x] = arr[y];
                arr[y] = temp;
            }
        }

        for(int i = 1; i <= N; i++)
            System.out.print(arr[i] + " ");
    }
}
