package boj.boj0037_210216_1026_g4;

import java.util.*;
import java.io.*;

public class boj_1026_가르침_비트마스크 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final char[] ACINT= {'a', 'c', 'i', 'n', 't'};
    static final int CHAR_MAX = 26;

    static int[] words;

    static int N;
    static int K;

    static int init() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int selected = 0;

        if(K < 5) return -1;

        for(char c : ACINT){
            selected |= 1 << (c - 'a');
        }
        words = new int[N];
        for(int i = 0 ; i < N ; i++){
            for(char c : br.readLine().toCharArray()){
                words[i] |= 1 << c - 'a';
            }
        }
        return selected;
    }

    static void comb(int idx, int idxStart, int flag){
        if(idx == K){
            check(flag);
            return;
        }

        for(int i = idxStart; i < CHAR_MAX; i++){
            if((flag & 1 << i) != 0) continue;
            comb(idx + 1, i + 1, flag | 1 << i);
        }
    }

    static int max ;
    static void check(int flag){
        int cnt = 0;
        for(int w : words){
            if((flag & w) == w) cnt++;
        }
        max = max > cnt ? max : cnt;
    }

    public static void main(String[] args) throws IOException{
        int selected = init();
        if(selected != -1)  comb(ACINT.length, 0, selected);
        System.out.println(max);
    }
}
