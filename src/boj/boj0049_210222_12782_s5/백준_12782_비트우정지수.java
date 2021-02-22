package boj.boj0049_210222_12782_s5;

import java.io.*;
import java.util.*;

public class 백준_12782_비트우정지수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int solve() throws IOException{
        st = new StringTokenizer(br.readLine());
        char[] bit1 = st.nextToken().toCharArray();
        char[] bit2 = st.nextToken().toCharArray();
        int diff = 0;
        int bit1Cnt = 0;
        int bit2Cnt = 0;

        for(int i = 0, size = bit1.length ; i < size ; i++){
            if (bit1[i] == '1') bit1Cnt++;
            if (bit2[i] == '1') bit2Cnt++;
            if (bit1[i] != bit2[i])  diff++;
        }
        return (diff + Math.abs(bit1Cnt - bit2Cnt))/2;
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T ; tc++){
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }


}
