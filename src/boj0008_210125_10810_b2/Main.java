package boj0008_210125_10810_b2;

import java.util.Scanner;

public class Main {
    static int arr[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int i, j, k ;

        arr = new int[N+1];

        for(int x = 0 ; x < M ; x++){
            i = sc.nextInt();
            j = sc.nextInt();
            k = sc.nextInt();

            for(int y = i ; y <= j ; y ++)
                arr[y] = k;
        }

        for (int x = 1 ; x <= N ; x++)
            System.out.print(arr[x] + " ");
    }
}
