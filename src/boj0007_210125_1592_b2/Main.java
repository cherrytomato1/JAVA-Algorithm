package boj0007_210125_1592_b2;

import java.util.Scanner;

public class Main {
    static int arr[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int L = sc.nextInt();

        arr = new int[N];
        int cnt = 0;
        int ball = 0;

        while( true ){
            if(arr[ball] % 2 == 1)
                ball = (ball + L) % N;

            else
                ball = (ball - L + N) % N;
            arr[ball] ++;
            if(arr[ball] == M)
                break;
            cnt++;
        }
        System.out.println(cnt);
    }
}
