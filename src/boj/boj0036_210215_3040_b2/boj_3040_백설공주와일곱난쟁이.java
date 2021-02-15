package boj.boj0036_210215_3040_b2;

import java.io.*;
import java.util.*;

public class boj_3040_백설공주와일곱난쟁이 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static final int SRC_LEN = 9;
    static final int RES_LEN = 7;

    static int[] src = new int[SRC_LEN];
    static int[] res = new int[SRC_LEN];

    static void input() throws IOException{
        for(int i = SRC_LEN - 1  ; i >= SRC_LEN - RES_LEN ; i--){
            res[i] = 1;
        }

        for(int i = 0 ; i < SRC_LEN ; i++){
            src[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(src);
    }

    static boolean check(){
        int sum = 0;
        for(int i = 0; i < SRC_LEN ; i++){
            if(res[i] == 1) sum += src[i];
        }
        if(sum == 100){
            for(int i = 0; i < SRC_LEN ; i++){
                if(res[i] == 1) sb.append(src[i]).append("\n");
            }
            return true;
        }
        return false;
    }

    static void solve(){
        do{
            if(check()) {
                System.out.print(sb);
                return ;
            }
        }while(np());
    }

    static boolean np(){
        int i = res.length - 1;

        while(i > 0 && res[i - 1] >= res[i]) i--;
        if(i == 0) return false;

        int j = res.length - 1;
        while(res[i - 1] >= res[j]) j--;
        idxSwap(res, i - 1, j);

        int k = res.length - 1;
        while( k > i) {
            idxSwap(res, i++, k--);
        }
        return true;
    }

    static void idxSwap(int[] arr, int idx1, int idx2){
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public static void main(String[] args) throws IOException{
        input();
        solve();

    }
}
