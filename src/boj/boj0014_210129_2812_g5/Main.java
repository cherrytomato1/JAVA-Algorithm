package boj.boj0014_210129_2812_g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int K;
    static char[] arr;
    static Deque<Integer> dq = new ArrayDeque<>();

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = br.readLine().toCharArray();
    }
    static void solve(){
        int num;
        int count = N - K;

        for(int i = 0 ; i < arr.length; i++){
            num = arr[i] - '0';

            //탑과 비교 및 팝 할 수 있는 최대 회수
            while(!dq.isEmpty() && dq.getFirst() < num && K != 0){
                dq.pop();
                K--;
                count++;
            }
            /** 넣을 수 있는 자리가 남았는 지 검사 */
            if(count > 0 ) {
                dq.push(num);
                count --;
            }else if(!dq.isEmpty() && dq.getFirst() < num){
                dq.pop();
                dq.push(num);
            }
        }
        while(!dq.isEmpty()){
            System.out.print(dq.removeLast());
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        solve();
    }
}
