package a0001_210116_1110_b1;


//https://www.acmicpc.net/problem/1110

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        int N = Integer.parseInt(str);
        int ans = N ;
        int f , b ;
        int cnt = 0 ;

        do
        {
            b = ans % 10 ;
            f = ans / 10 ;
            ans = (b * 10) + ((f + b) % 10);

            cnt ++;
        } while ( ans != N ) ;

        System.out.println(cnt);
    }
}
