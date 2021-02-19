package swea.solution_D4_3244;

import java.util.*;
import java.io.*;

public class Solution_D4_3244_준환이의양팔저울 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int res;
    static int[] chu;
    static int sum;

    static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        chu = new int[N];
        res = 0;
        sum = 0;
        for(int i = 0 ; i < N; i++){
            chu[i] = Integer.parseInt(st.nextToken());
            sum += chu[i];
        }
    }

    static void dfs(int idx, int left, int right, int flag){
        if(idx == N){
            res++;
            return ;
        }

        if(sum - left <= left) {
            int temp = 1;
            for(int i = 1; i <= N - idx ; i++){
                temp *= i;
            }
            res += temp * Math.pow(2, N - idx);;
            return ;
        }


        for(int i = 0 ; i < N ; i++){
            if((flag & 1 << i) != 0)    continue;
            dfs(idx + 1, left + chu[i], right, flag | 1 << i);
            if(left >= right + chu[i])    dfs(idx + 1, left, right + chu[i], flag | 1 << i);
        }
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1 ; tc <= T; tc++){
            sb.append("#").append(tc).append(" ");
            init();
            dfs(0, 0, 0, 0 );
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}
