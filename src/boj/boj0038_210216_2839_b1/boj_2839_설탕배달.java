package boj.boj0038_210216_2839_b1;

import java.io.*;
public class boj_2839_설탕배달 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int val = N;
        int three = 0;
        for(;val % 5 != 0 && val >= 0; three++)     val -= 3;
        if(val < 0)     System.out.println(-1);
        else            System.out.println(three + val/5);
    }
}
