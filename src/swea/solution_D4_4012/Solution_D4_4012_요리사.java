package swea.solution_D4_4012;

import java.io.*;
import java.util.*;

public class Solution_D4_4012_요리사 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[][] arr;
    static int N;
    static int[] flag;
    static int[][] foods;


    static void init() throws IOException{
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        flag = new int[N];

        foods = new int[2][N/2];

        for(int r = 0 ; r < N ; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < N ; c++){
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = N - 1; i >= N/2 ; i--){
            flag[i] = 1;
        }
    }

    static int solve(){
        int res = Integer.MAX_VALUE;
        do{
            int val = check();
            res = res < val ? res : val;
        } while(np());
        return res;
    }

    static int check(){
        int food1Val = 0;
        int food2Val = 0;
        for(int i = 0, j = 0, k = 0; i < N; i++){
            if(flag[i] == 1)    foods[1][j++] = i;
            else                foods[0][k++] = i;
        }
        for(int i = 0 ; i < N/2; i++){
            for(int j = 0 ; j < N/2 ; j++){
                food1Val += arr[foods[1][i]][foods[1][j]];
                food2Val += arr[foods[0][i]][foods[0][j]];
            }
        }
        return Math.abs(food1Val - food2Val);
    }

    static boolean np(){
        int i = N - 1;
        while(i > 0 && flag[i - 1] >= flag[i]) i--;
        if(i == 0)  return false;

        int j = N - 1;
        while(flag[i - 1] >= flag[j]) j--;
        flagSwap(i - 1, j);

        int k = N - 1;
        while(k > i){
            flagSwap(k--, i++);
        }
        return true;
    }

    static void flagSwap(int idx1, int idx2){
        int temp = flag[idx1];
        flag[idx1] = flag[idx2];
        flag[idx2] = temp;
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T ; tc ++){
            sb.append("#").append(tc).append(" ");
            init();

            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}
