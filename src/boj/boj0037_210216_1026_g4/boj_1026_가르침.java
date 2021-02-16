package boj.boj0037_210216_1026_g4;

import java.util.*;
import java.io.*;

public class boj_1026_가르침 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static char[] antic =   {'a', 'c', 'i', 'n', 't'};
    static char[] chars =   {'b', 'd', 'e', 'f', 'g',
                             'h', 'j', 'k', 'l', 'm',
                             'o', 'p', 'q', 'r', 's',
                             'u', 'v', 'w', 'x', 'y', 'z'};
    static short[] temp = new short[chars.length];
    static char[] res ;

    static String[] words;
    static int N;
    static int K;

    static boolean input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(K < 5){
            System.out.println(0);
            return false;
        }

        for(int i = chars.length - 1; i >= chars.length - (K - 5) ; i--){
            temp[i] = 1;
        }
        res = new char[K];
        for (int i = 0 ; i < antic.length ; i++) {
            res[i] = antic[i];
        }


        words = new String[N];
        for(int i = 0 ; i < N ; i++){
            words[i] = br.readLine();
        }
        return true;
    }

    static int solve(){
        int max = Integer.MIN_VALUE;
        do{
            int cnt = check();
            max = max > cnt ? max : cnt;
        } while(np());
        return max;
    }

    static int check(){
        int cnt = 0;
        for(int i = 0, j = 5 ; i < chars.length; i++){
            if(temp[i] == 1) res[j++] = chars[i];
        }
        String resStr = String.valueOf(res);
        for(int i = 0 ; i < N ; i++){
            boolean flag = true;
            for(int j = 0 ; j < words[i].length(); j++){
                if(!resStr.contains(String.valueOf(words[i].charAt(j)))) {
                    flag = false;
                    break;
                }
            }
            if(flag) cnt++;
        }
        return cnt;
    }

    static boolean np(){
        int len = temp.length;
        int i = len - 1;
        while(i > 0 && temp[i - 1] >= temp[i]) i--;
        if(i == 0)  return false;

        int j = len - 1;
        while(temp[i - 1] >= temp[j]) j--;
        tempSwap(i - 1, j);

        int k = len - 1;
        while(k > i){
            tempSwap(k--, i++);
        }

        return true;
    }

    static void tempSwap(int idx1, int idx2){
        short t = temp[idx1];
        temp[idx1] = temp[idx2];
        temp[idx2] = t;
    }

    public static void main(String[] args) throws IOException{
        if(!input())     return ;
        System.out.println(solve());
    }
}
