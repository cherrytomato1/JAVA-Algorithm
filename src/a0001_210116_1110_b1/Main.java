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

        int N = Integer.parseInt(str);          //스캐너로 입력받은 값을 Int 형으로 변환
        int ans = N ;                           //결과 변수 선언, 초기값은 입력값과 같이하여 연산 수행
        int f , b ;                             //두 자리수의 십의 자리와 일의 자리를 저장하는 변수 선언
        int cnt = 0 ;                           //총 연산의 회수

        do
        {
            b = ans % 10 ;                      //일의 자리는 10으로 나눈 값의 나머지
            f = ans / 10 ;                      //십의 자리는 10으로 나눈 값의 몫
            ans = (b * 10) + ((f + b) % 10);    //그 다음 값은 일의 자리에 10을 곱해 십의 자리로 보내고, 앞선 결과값의 합에서 일의 자리를 더한다.

            cnt ++;                             //총 연산회수 증가
        } while ( ans != N ) ;                  //입력값과 같으면 연산 종

        System.out.println(cnt);
    }
}
