package a0001_210116_1110_b1;
/*

//https://www.acmicpc.net/problem/1110

    1110. 더하기 사이클

    0<= N <= 99 인 입력 N에 대하여 N의 앞 뒤자리를 더하여 한자리씩 밀어내어 해당 작업을 반복했을 때
    몇 번 만에 작업이 끝나는 지 작업의 회수를 출력

    2자리 수 이므로 앞자리는 /=10 , 뒤의 자리는 %=10 하여 구한다. 그 두 수를 더하여 값을 계산함

*/
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
