package boj.boj0010_210127_5052_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_5052_전화번호목록 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N ;
    static String[] arr;

    static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        arr = new String[N];

        for(int i = 0 ; i < N ; i++){
            arr[i] = br.readLine().trim();
        }
        Arrays.sort(arr);
    }

    static boolean solve(){
        for(int i = 1 ; i < N ; i++){
            int len = Math.min(arr[i].length(), arr[i-1].length());
            if(arr[i].substring(0, len).equals(arr[i - 1].substring(0, len)))       return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1 ; tc <= T ; tc++){
            init();
            if(!solve())    sb.append("NO").append("\n");
            else            sb.append("YES").append("\n");
        }
        System.out.print(sb);
    }
}
