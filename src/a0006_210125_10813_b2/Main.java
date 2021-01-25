package a0006_210125_10813_b2;

import java.util.Scanner;

public class Main {
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int temp, a, b;

        arr = new int[N + 1];

        for(int i = 1 ; i <= N ; i++)
            arr[i] = i;

        for(int i = 0 ; i < M ; i++){
            a = sc.nextInt();
            b = sc.nextInt();

            temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }

        for(int i = 1 ; i <= N ; i++)
            System.out.print(arr[i] + " ");

    }
}
