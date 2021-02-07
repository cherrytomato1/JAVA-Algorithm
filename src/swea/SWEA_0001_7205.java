package swea;

import java.util.*;
import java.io.*;

public class SWEA_0001_7205 {
    static int N;
    static boolean[][] contry;
    static int[] colors;
    static int[] res;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int ans = Integer.MAX_VALUE;
    static void f(int idx, int cnt) {
        if(idx == N ) {
            for(int i = 0 ; i < N ; i ++) {
                for(int j = 0 ; j < N ; j++) {
                    if(contry[i][j] && res[i] == res[j])
                        return;
                }
            }
            ans = ans < cnt ? ans : cnt;
            return ;
        }

        for(int i = 1 ; i <= 4 ; i++) {
            res[idx] = i ;
            if(res[idx] != colors[idx])		f(idx + 1, cnt + 1);
            else f(idx + 1, cnt);
        }

    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1 ; tc <= T ; tc++) {
            N = Integer.parseInt(br.readLine());
            sb.append("#").append(tc).append(" ");

            contry = new boolean[N][N];
            colors = new int[N];
            res = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i++) {
                colors[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0 ; i < N ; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < N ; j++) {
                    contry[i][j] = Integer.parseInt(st.nextToken()) == 1;
                }
            }

            ans = Integer.MAX_VALUE;
            f(0, 0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
