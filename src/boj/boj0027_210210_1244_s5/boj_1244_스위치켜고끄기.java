package boj.boj0027_210210_1244_s5;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1244_스위치켜고끄기 {
    static int N;
    static int M;
    static boolean[] arr;
    static StringTokenizer st;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void solve() throws IOException{
        M = Integer.parseInt(br.readLine());

        for( int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int gen = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            for(int j = num ; gen == 1 && j <= N ; j += num){
                arr[j] = !arr[j];
            }
            for(int j = num, k = num ; gen == 2 && j <= N && k > 0 ; j++, k--){
                if(arr[j] == arr[k]) arr[j] = arr[k] = !arr[j];
                else break;
            }
        }
        for(int i = 1 ; i <= N; i++){
            System.out.print((arr[i] ? 1 : 0) + " ");
            if(i % 20 == 0) System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i <= N ; i++){
            arr[i] = st.nextToken().equals("1");
        }
        solve();
    }
}
